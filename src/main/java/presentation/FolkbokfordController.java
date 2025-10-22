package presentation;

import jakarta.inject.Inject;
import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.Path;
import logic.FolkbokfordLogicService;
import jakarta.enterprise.context.ApplicationScoped;
import se.fk.rimfrost.api.folkbokforing.jaxrsspec.controllers.generatedsource.PopulationRegistrationControllerApi;
import se.fk.rimfrost.api.folkbokforing.jaxrsspec.controllers.generatedsource.model.PopulationRegistrationSocialSecurityNrGet200Response;

@ApplicationScoped
@Path("/api/folkbokford")
public class FolkbokfordController implements PopulationRegistrationControllerApi
{
   @Inject
   FolkbokfordLogicService folkbokfordService;

   @Inject
   PresentationMapper presentationMapper;

   @Override
   public PopulationRegistrationSocialSecurityNrGet200Response populationRegistrationSocialSecurityNrGet(
         @Pattern(regexp = "^\\d{8}-\\d{4}$") String socialSecurityNr)
   {
      var presentationRequest = presentationMapper.fromExternalApi(socialSecurityNr);
      var logicRequest = presentationMapper.toLogic(presentationRequest);
      var logicResponse = folkbokfordService.checkFolkbokford(logicRequest);
      var presentationResponse = presentationMapper.toPresentation(logicResponse);
      return presentationMapper.toExternalApi(presentationResponse);
   }
}
