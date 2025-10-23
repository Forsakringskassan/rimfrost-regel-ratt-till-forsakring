package presentation;

import jakarta.enterprise.context.ApplicationScoped;
import logic.dto.ImmutableLogicFolkbokfordRequest;
import logic.dto.LogicFolkbokfordRequest;
import logic.dto.LogicFolkbokfordResponse;
import presentation.dto.*;
import se.fk.rimfrost.api.vahregelrtfspec.VahRtfResponsePayload;

import java.util.UUID;

@ApplicationScoped
public class PresentationMapper
{
   public LogicFolkbokfordRequest toLogic(PresentationVahRtfRequest presentation)
   {
      return ImmutableLogicFolkbokfordRequest.builder()
            .personnummer(presentation.personnummer())
            .build();
   }

   public PresentationVahRtfResponse toPresentation(LogicFolkbokfordResponse logic)
   {
      return ImmutablePresentationVahRtfResponse.builder()
            .isBokford(logic.isBokford())
            .build();

   }

   public PresentationVahRtfRequest fromExternalApi(String externalRequest)
   {
      return ImmutablePresentationVahRtfRequest.builder()
            .personnummer(externalRequest)
            .build();
   }

   public VahRtfResponsePayload toExternalApi(PresentationVahRtfResponse presentationResponse, String processId)
   {
      VahRtfResponsePayload to = new VahRtfResponsePayload();
      to.setProcessId(processId);
      to.setBokford(presentationResponse.isBokford());
      return to;
   }
}
