package presentation;

import jakarta.enterprise.context.ApplicationScoped;
import logic.dto.ImmutableLogicFolkbokfordRequest;
import logic.dto.LogicFolkbokfordRequest;
import logic.dto.LogicFolkbokfordResponse;
import presentation.dto.*;

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

   public VahRtfResponse toExternalApi(PresentationVahRtfResponse presentationResponse)
   {
      VahRtfResponse apiResponse = new VahRtfResponse();
      apiResponse.setResult(presentationResponse.isBokford());
      return apiResponse;
   }
}
