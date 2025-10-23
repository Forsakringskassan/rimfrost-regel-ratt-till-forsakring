package test;

import common.TestDataFactory;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import presentation.VahRtfProcessor;
import presentation.dto.*;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class FolkbokfordIntegratonTest
{
   @Inject
   VahRtfProcessor vahRtfProcessor;

   @Test
   void testProcess_whenPersonIsBokford_shouldReturnTrueResult()
   {
      UUID processId = UUID.randomUUID();
      VahRtfRequest request = ImmutableVahRtfRequest.builder()
            .processId(processId)
            .pnr(TestDataFactory.PNR1)
            .build();

      VahRtfResponse response = vahRtfProcessor.process(request);

      assertThat(response).isNotNull();
      assertThat(response.processId()).isEqualTo(processId);
      assertThat(response.result()).isTrue();
   }

   @Test
   void testProcess_whenPersonIsNotBokford_shouldReturnFalseResult()
   {
      UUID processId = UUID.randomUUID();
      VahRtfRequest request = ImmutableVahRtfRequest.builder()
            .processId(processId)
              .pnr(TestDataFactory.PNR2)
            .build();

      VahRtfResponse response = vahRtfProcessor.process(request);

      assertThat(response).isNotNull();
      assertThat(response.processId()).isEqualTo(processId);
      assertThat(response.result()).isFalse();
   }
}
