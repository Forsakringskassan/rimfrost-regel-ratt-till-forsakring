package se.fk.github.regelratttillforsakring.tests.presentation;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import se.fk.github.regelratttillforsakring.common.KafkaTestBase;
import se.fk.rimfrost.api.vahregelrtfspec.KogitoProcType;
import se.fk.rimfrost.api.vahregelrtfspec.VahRtfRequestMessageData;
import se.fk.rimfrost.api.vahregelrtfspec.VahRtfRequestMessagePayload;
import se.fk.rimfrost.api.vahregelrtfspec.VahRtfResponseMessagePayload;

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

      var dummyData = new VahRtfRequestMessageData();
      dummyData.setProcessId(processId.toString());
      dummyData.setPersonNummer(personnummer);

      var request = new VahRtfRequestMessagePayload();
      request.setData(dummyData);

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
               VahRtfResponseMessagePayload response = inMemorySink.received().getFirst().getPayload();
               assertThat(response.getData().getProcessId()).isEqualTo(processId.toString());
               assertThat(response.getData().getPersonNummer()).isEqualTo(personnummer);
            });
   }

   @Test
   void testReceiveMultipleRequests()
   {
      UUID processId = UUID.randomUUID();
      UUID processId2 = UUID.randomUUID();
      String personnummer = "12345678-1234";
      String personnummer2 = "12345678-3456";

      var dummyData = new VahRtfRequestMessageData();
      dummyData.setProcessId(processId.toString());
      dummyData.setPersonNummer(personnummer);

      var dummyData2 = new VahRtfRequestMessageData();
      dummyData2.setProcessId(processId2.toString());
      dummyData2.setPersonNummer(personnummer2);

      var request = new VahRtfRequestMessagePayload();
      request.setData(dummyData);
      request.setId("123");
      request.setSource("234");
      request.setType("vah-rtf-responses");
      request.setKogitorootprocid("345");
      request.setKogitorootprociid("456");
      request.setKogitoparentprociid("567");
      request.setKogitoprocid("678");
      request.setKogitoprocinstanceid("789");
      request.setKogitoprocrefid("890");
      request.setKogitoprocist("901");
      request.setKogitoproctype(KogitoProcType.BPMN);
      request.setKogitoprocversion("1.1");

      var request2 = new VahRtfRequestMessagePayload();
      request2.setData(dummyData2);
      request2.setId("123");
      request2.setSource("234");
      request2.setType("vah-rtf-responses");
      request2.setKogitorootprocid("345");
      request2.setKogitorootprociid("456");
      request2.setKogitoparentprociid("567");
      request2.setKogitoprocid("678");
      request2.setKogitoprocinstanceid("789");
      request2.setKogitoprocrefid("890");
      request2.setKogitoprocist("901");
      request2.setKogitoproctype(KogitoProcType.BPMN);
      request2.setKogitoprocversion("1.1");

      inMemorySource.send(request);
      inMemorySource.send(request2);

      await()
            .atMost(Duration.ofSeconds(5))
            .untilAsserted(() -> {
               assertThat(processor.getReceivedMessagesCount())
                     .as("Two messages should have been received")
                     .isEqualTo(2);
            });
   }
}
