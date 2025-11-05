package se.fk.github.regelratttillforsakring.tests.presentation;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import se.fk.github.regelratttillforsakring.common.KafkaTestBase;
import se.fk.github.regelratttillforsakring.presentation.dto.ImmutableVahRtfRequest;
import se.fk.github.regelratttillforsakring.presentation.dto.VahRtfResponse;

import java.time.Duration;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@QuarkusTest
public class VahRtfProcessorTest extends KafkaTestBase
{

   @Test
   void testReceiveVahRtfRequest_withOkPnr()
   {
      UUID processId = UUID.randomUUID();
      String personnummer = "12345678-1234";
      var request = ImmutableVahRtfRequest.builder()
            .processId(processId)
            .pnr(personnummer)
            .build();

      inMemorySource.send(request);

      await()
            .atMost(Duration.ofSeconds(5))
            .untilAsserted(() -> {
               assertThat(processor.getReceivedMessagesCount())
                     .as("One message should have been received")
                     .isEqualTo(1);
            });

      await()
            .atMost(Duration.ofSeconds(5))
            .untilAsserted(() -> {
               assertThat(inMemorySink.received())
                     .as("One message should have been received")
                     .hasSize(1);
               VahRtfResponse response = inMemorySink.received().getFirst().getPayload();
               assertThat(response.processId()).isEqualTo(processId);
               assertThat(response.hasArbetsgivare()).isTrue();
               assertThat(response.isBokford()).isTrue();
            });
   }

   @Test
   void testRecieveMultipleRequests()
   {
      UUID processId = UUID.randomUUID();
      UUID processId2 = UUID.randomUUID();
      String personnummer = "12345678-1234";
      String personnummer2 = "12345678-3456";
      var request = ImmutableVahRtfRequest.builder()
            .processId(processId)
            .pnr(personnummer)
            .build();
      var request2 = ImmutableVahRtfRequest.builder()
            .processId(processId2)
            .pnr(personnummer2)
            .build();

      inMemorySource.send(request);
      inMemorySource.send(request2);

      await()
            .atMost(Duration.ofSeconds(5))
            .untilAsserted(() -> {
               assertThat(processor.getReceivedMessagesCount())
                     .as("Two messages should have been recieved")
                     .isEqualTo(2);
            });
   }
}
