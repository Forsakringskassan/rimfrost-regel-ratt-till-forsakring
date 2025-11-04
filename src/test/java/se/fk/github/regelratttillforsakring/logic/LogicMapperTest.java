package se.fk.github.regelratttillforsakring.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import se.fk.github.regelratttillforsakring.integration.dto.ImmutableArbetsgivareApiResponse;
import se.fk.github.regelratttillforsakring.integration.dto.ImmutableFolkbokfordApiRequest;
import se.fk.github.regelratttillforsakring.integration.dto.ImmutableFolkbokfordApiResponse;
import se.fk.github.regelratttillforsakring.logic.dto.ImmutableLogicRtfRequest;
import se.fk.github.regelratttillforsakring.logic.dto.ImmutableLogicRtfResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LogicMapperTest
{

   private LogicMapper mapper;
   private final String personnummer = "12345678-1234";

   @BeforeEach
   void setUp()
   {
      mapper = new LogicMapper();
   }

   @Test
   void toFolkbokfordIntegration_mapsPersonnummer()
   {
      var logicReq = ImmutableLogicRtfRequest.builder()
            .personnummer(personnummer)
            .build();

      var fbReq = mapper.toFolkbokfordIntegration(logicReq);

      assertEquals(personnummer, fbReq.personnummer());
      assertEquals(ImmutableFolkbokfordApiRequest.class, fbReq.getClass());
   }

   @Test
   void toArbetsgivareIntegration_mapsPersonnummer()
   {
      var logicReq = ImmutableLogicRtfRequest.builder()
            .personnummer(personnummer)
            .build();

      var abReq = mapper.toArbetsgivareIntegration(logicReq);

      assertEquals(personnummer, abReq.personnummer());
   }

   @ParameterizedTest
   @CsvSource(
   {
         "true, false",
         "true, true",
         "false, true",
         "false, false"
   })
   void toLogic_mapsCorrectly(boolean isFolkbokford, boolean hasArbetsgivare)
   {
      var fbResp = ImmutableFolkbokfordApiResponse.builder()
            .isFolkbokford(isFolkbokford)
            .build();

      var abResp = ImmutableArbetsgivareApiResponse.builder()
            .hasArbetsgivare(hasArbetsgivare)
            .build();

      var logicResp = mapper.toLogic(fbResp, abResp);

      assertEquals(isFolkbokford, logicResp.isBokford());
      assertEquals(hasArbetsgivare, logicResp.hasArbetsgivare());
      assertEquals(ImmutableLogicRtfResponse.class, logicResp.getClass());
   }

}
