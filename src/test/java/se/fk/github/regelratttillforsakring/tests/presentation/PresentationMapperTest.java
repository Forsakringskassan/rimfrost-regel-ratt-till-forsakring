package se.fk.github.regelratttillforsakring.tests.presentation;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import se.fk.github.regelratttillforsakring.logic.dto.ImmutableLogicRtfRequest;
import se.fk.github.regelratttillforsakring.logic.dto.ImmutableLogicRtfResponse;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRattTillForsakring;
import se.fk.github.regelratttillforsakring.presentation.PresentationMapper;
import se.fk.github.regelratttillforsakring.presentation.dto.*;
import se.fk.rimfrost.api.vahregelrtfspec.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
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
   @EnumSource(LogicRattTillForsakring.class)
   void toPresentation_mapsCorrectly(LogicRattTillForsakring c)
   {
      var logicResponse = ImmutableLogicRtfResponse.builder()
            .rattTillForsakring(c)
            .build();

      var presentationResponse = mapper.toPresentation(logicResponse);

      assertEquals(expected(c), presentationResponse.rattTillForsakring());
      assertEquals(ImmutablePresentationRtfResponse.class, presentationResponse.getClass());
   }

   private static RattTillForsakring expected(LogicRattTillForsakring c){
       return switch(c){
           case JA -> RattTillForsakring.JA;
           case NEJ -> RattTillForsakring.NEJ;
           case UTREDNING -> RattTillForsakring.UTREDNING;
       };
   }

   @Test
   void fromExternalApi_mapsCorrectly()
   {
      var presentationRequest = mapper.fromExternalApi(personnummer);

      assertEquals(personnummer, presentationRequest.personnummer());
      assertEquals(ImmutablePresentationRtfRequest.class, presentationRequest.getClass());
   }

   @ParameterizedTest
   @EnumSource(RattTillForsakring.class)
   void toExternalApi_mapsCorrectly(RattTillForsakring r)
   {
      var presentationResponse = ImmutablePresentationRtfResponse.builder()
            .rattTillForsakring(r)
            .build();

      var dummyData = new VahRtfRequestMessageData();

      dummyData.setProcessId(processId.toString());
      dummyData.setPersonNummer(personnummer);

      var dummyRequest = new VahRtfRequestMessagePayload();
      dummyRequest.setData(dummyData);
      dummyRequest.setId("123");
      dummyRequest.setSource("234");
      dummyRequest.setType("vah-rtf-responses");
      dummyRequest.setKogitorootprocid("345");
      dummyRequest.setKogitorootprociid("456");
      dummyRequest.setKogitoparentprociid("567");
      dummyRequest.setKogitoprocid("678");
      dummyRequest.setKogitoprocinstanceid("789");
      dummyRequest.setKogitoprocrefid("890");
      dummyRequest.setKogitoprocist("901");
      dummyRequest.setKogitoproctype(KogitoProcType.BPMN);
      dummyRequest.setKogitoprocversion("1.1");

      var toExternalResponse = mapper.toExternalApi(presentationResponse, dummyRequest);

      assertEquals(r, toExternalResponse.getData().getRattTillForsakring());
      assertEquals(VahRtfResponseMessagePayload.class, toExternalResponse.getClass());
   }
}
