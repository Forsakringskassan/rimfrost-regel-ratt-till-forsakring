package presentation;

import jakarta.enterprise.context.ApplicationScoped;
import logic.dto.ImmutableLogicFolkbokfordRequest;
import logic.dto.LogicFolkbokfordRequest;
import logic.dto.LogicFolkbokfordResponse;
import presentation.dto.*;

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

   public VahRtfResponse toExternalApi(PresentationVahRtfResponse presentationResponse, UUID processId)
   {
      return ImmutableVahRtfResponse.builder()
            .processId(processId)
            .result(presentationResponse.isBokford())
            .build();
   }
}
