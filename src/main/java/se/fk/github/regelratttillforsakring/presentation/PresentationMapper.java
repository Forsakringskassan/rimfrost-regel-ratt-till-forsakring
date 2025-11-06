package se.fk.github.regelratttillforsakring.presentation;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.regelratttillforsakring.logic.dto.ImmutableLogicRtfRequest;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRtfRequest;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRtfResponse;
import se.fk.github.regelratttillforsakring.presentation.dto.*;
import se.fk.rimfrost.api.vahregelrtfspec.VahRtfRequestMessagePayload;
import se.fk.rimfrost.api.vahregelrtfspec.VahRtfResponseMessageData;
import se.fk.rimfrost.api.vahregelrtfspec.VahRtfResponseMessagePayload;

@ApplicationScoped
public class PresentationMapper
{
   public LogicRtfRequest toLogic(PresentationRtfRequest presentation)
   {
      return ImmutableLogicRtfRequest.builder()
            .personnummer(presentation.personnummer())
            .build();
   }

   public PresentationRtfResponse toPresentation(LogicRtfResponse logic)
   {
      return ImmutablePresentationRtfResponse.builder()
            .rattTillForsakring(logic.rattTillForsakring())
            .build();

   }

   public PresentationRtfRequest fromExternalApi(String externalRequest)
   {
      return ImmutablePresentationRtfRequest.builder()
            .personnummer(externalRequest)
            .build();
   }

   public VahRtfResponseMessagePayload toExternalApi(PresentationRtfResponse presentationResponse,
         VahRtfRequestMessagePayload request)
   {
      VahRtfResponseMessageData data = new VahRtfResponseMessageData();
      data.setProcessId(request.getData().getProcessId());
      data.setRattTillForsakring(presentationResponse.rattTillForsakring());
      data.setPersonNummer(request.getData().getPersonNummer());

      VahRtfResponseMessagePayload response = new VahRtfResponseMessagePayload();
      response.setId(request.getId());
      response.setSource(request.getSource());
      response.setType("vah-rtf-responses");
      response.setKogitorootprocid(request.getKogitorootprocid());
      response.setKogitorootprociid(request.getKogitorootprociid());
      response.setKogitoparentprociid(request.getKogitoparentprociid());
      response.setKogitoprocid(request.getKogitoprocid());
      response.setKogitoprocinstanceid(request.getKogitoprocinstanceid());
      response.setKogitoprocrefid(request.getKogitoprocrefid());
      response.setKogitoprocist(request.getKogitoprocist());
      response.setKogitoproctype(request.getKogitoproctype());
      response.setKogitoprocversion(request.getKogitoprocversion());
      response.setData(data);

      return response;
   }
}
