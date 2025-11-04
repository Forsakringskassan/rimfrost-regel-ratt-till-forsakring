package se.fk.github.regelratttillforsakring.presentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import se.fk.github.regelratttillforsakring.logic.dto.ImmutableLogicRtfRequest;
import se.fk.github.regelratttillforsakring.logic.dto.ImmutableLogicRtfResponse;
import se.fk.github.regelratttillforsakring.presentation.dto.ImmutablePresentationRtfRequest;
import se.fk.github.regelratttillforsakring.presentation.dto.ImmutablePresentationRtfResponse;
import se.fk.github.regelratttillforsakring.presentation.dto.ImmutableVahRtfResponse;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PresentationMapperTest
{

   private PresentationMapper mapper;
   private final String personnummer = "12345678-1234";
   private final UUID processId = UUID.randomUUID();

   @BeforeEach
   void setUp()
   {
      mapper = new PresentationMapper();
   }

   @Test
   void toLogic_mapsCorrectly()
   {
      var presentationRequest = ImmutablePresentationRtfRequest.builder()
            .personnummer(personnummer)
            .build();

      var logicReq = mapper.toLogic(presentationRequest);

      assertEquals(personnummer, logicReq.personnummer());
      assertEquals(ImmutableLogicRtfRequest.class, logicReq.getClass());
   }

   @ParameterizedTest
   @CsvSource(
   {
         "true, true",
         "true, false",
         "false, true",
         "false, false"
   })
   void toPresentation_mapsCorrectly(boolean isFolkbokford, boolean hasArbetsgivare)
   {
      var logicResponse = ImmutableLogicRtfResponse.builder()
            .hasArbetsgivare(hasArbetsgivare)
            .isBokford(isFolkbokford)
            .build();

      var presentationResponse = mapper.toPresentation(logicResponse);

      assertEquals(hasArbetsgivare, presentationResponse.hasArbetsgivare());
      assertEquals(isFolkbokford, presentationResponse.isBokford());
      assertEquals(ImmutablePresentationRtfResponse.class, presentationResponse.getClass());
   }

   @Test
   void fromExternalApi_mapsCorrectly()
   {
      var presentationRequest = mapper.fromExternalApi(personnummer);

      assertEquals(personnummer, presentationRequest.personnummer());
      assertEquals(ImmutablePresentationRtfRequest.class, presentationRequest.getClass());
   }

   @ParameterizedTest
   @CsvSource(
   {
         "true, true",
         "true, false",
         "false, true",
         "false, false"
   })
   void toExternalApi_mapsCorrectly(boolean isFolkbokford, boolean hasArbetsgivare)
   {
      var presentationResponse = ImmutablePresentationRtfResponse.builder()
            .hasArbetsgivare(hasArbetsgivare)
            .isBokford(isFolkbokford)
            .build();

      var toExternalResponse = mapper.toExternalApi(presentationResponse, processId);

      assertEquals(hasArbetsgivare, toExternalResponse.hasArbetsgivare());
      assertEquals(isFolkbokford, toExternalResponse.isBokford());
      assertEquals(processId, toExternalResponse.processId());
      assertEquals(ImmutableVahRtfResponse.class, toExternalResponse.getClass());
   }
}
