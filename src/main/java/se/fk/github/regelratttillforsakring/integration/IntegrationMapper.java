package se.fk.github.regelratttillforsakring.integration;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.regelratttillforsakring.integration.dto.ImmutableIntegrationRtfResponse;
import se.fk.github.regelratttillforsakring.integration.dto.IntegrationRtfRequest;
import se.fk.github.regelratttillforsakring.integration.dto.IntegrationRtfResponse;
import se.fk.rimfrost.api.arbetsgivare.jaxrsspec.controllers.generatedsource.model.GetArbetsgivare200Response;
import se.fk.rimfrost.api.folkbokforing.jaxrsspec.controllers.generatedsource.model.FolkbokforingPersnrGet200Response;

@ApplicationScoped
public class IntegrationMapper
{
   public String toExternalApi(IntegrationRtfRequest integrationRequest)
   {
      return integrationRequest.personnummer();
   }

   public IntegrationRtfResponse fromExternalApi(FolkbokforingPersnrGet200Response folkbokforingResponse,
         GetArbetsgivare200Response arbetsgivareResponse)
   {
      return ImmutableIntegrationRtfResponse.builder()
            .isBokford(folkbokforingResponse.getResult())
            .hasArbetsgivare(arbetsgivareResponse.getResult())
            .build();
   }
}
