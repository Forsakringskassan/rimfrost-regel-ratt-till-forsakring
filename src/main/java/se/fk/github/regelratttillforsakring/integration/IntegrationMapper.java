package se.fk.github.regelratttillforsakring.integration;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.regelratttillforsakring.integration.dto.ImmutableIntegrationFolkbokfordResponse;
import se.fk.github.regelratttillforsakring.integration.dto.IntegrationFolkbokfordRequest;
import se.fk.github.regelratttillforsakring.integration.dto.IntegrationFolkbokfordResponse;
import se.fk.rimfrost.api.arbetsgivare.jaxrsspec.controllers.generatedsource.model.GetArbetsgivare200Response;
import se.fk.rimfrost.api.folkbokforing.jaxrsspec.controllers.generatedsource.model.FolkbokforingPersnrGet200Response;

@ApplicationScoped
public class IntegrationMapper
{
   public String toExternalApi(IntegrationFolkbokfordRequest integrationRequest)
   {
      return integrationRequest.personnummer();
   }

   public IntegrationFolkbokfordResponse fromExternalApi(FolkbokforingPersnrGet200Response folkbokforingResponse,
         GetArbetsgivare200Response arbetsgivareResponse)
   {
      return ImmutableIntegrationFolkbokfordResponse.builder()
            .isBokford(folkbokforingResponse.getResult())
            .hasArbetsgivare(arbetsgivareResponse.getResult())
            .build();
   }
}
