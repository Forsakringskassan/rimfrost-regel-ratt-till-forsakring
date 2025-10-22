package presentation;

import jakarta.enterprise.context.ApplicationScoped;
import logic.dto.ImmutableLogicFolkbokfordRequest;
import logic.dto.LogicFolkbokfordRequest;
import logic.dto.LogicFolkbokfordResponse;
import presentation.dto.ImmutablePresentationVahRtfRequest;
import presentation.dto.ImmutablePresentationVahRtfResponse;
import presentation.dto.PresentationVahRtfRequest;
import presentation.dto.PresentationVahRtfResponse;
import se.fk.rimfrost.api.folkbokforing.jaxrsspec.controllers.generatedsource.model.PopulationRegistrationSocialSecurityNrGet200Response;

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

   public PopulationRegistrationSocialSecurityNrGet200Response toExternalApi(PresentationVahRtfResponse presentationResponse)
   {
      PopulationRegistrationSocialSecurityNrGet200Response apiResponse = new PopulationRegistrationSocialSecurityNrGet200Response();
      apiResponse.setResult(presentationResponse.isBokford());
      return apiResponse;
   }
}
