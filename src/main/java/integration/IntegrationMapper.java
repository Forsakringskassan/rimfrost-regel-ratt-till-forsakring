package integration;

import integration.dto.ImmutableIntegrationFolkbokfordResponse;
import integration.dto.IntegrationFolkbokfordRequest;
import integration.dto.IntegrationFolkbokfordResponse;
import jakarta.enterprise.context.ApplicationScoped;
import se.fk.rimfrost.api.folkbokforing.jaxrsspec.controllers.generatedsource.model.PopulationRegistrationSocialSecurityNrGet200Response;

@ApplicationScoped
public class IntegrationMapper
{
   public String toExternalApi(IntegrationFolkbokfordRequest integrationRequest)
   {
      return integrationRequest.personnummer();
   }

   public IntegrationFolkbokfordResponse fromExternalApi(PopulationRegistrationSocialSecurityNrGet200Response externalResponse)
   {
      return ImmutableIntegrationFolkbokfordResponse.builder()
            .isBokford(externalResponse.getResult())
            .build();
   }
}
