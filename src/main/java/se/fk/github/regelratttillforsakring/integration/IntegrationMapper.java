package se.fk.github.regelratttillforsakring.integration;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.regelratttillforsakring.integration.dto.*;
import se.fk.rimfrost.api.folkbokforing.jaxrsspec.controllers.generatedsource.model.FolkbokforingPersnrGet200Response;
import se.fk.rimfrost.api.arbetsgivare.jaxrsspec.controllers.generatedsource.model.GetArbetsgivare200Response;

@ApplicationScoped
public class IntegrationMapper
{
   public String toFolkbokfordApi(FolkbokfordApiRequest folkbokfordRequest)
   {
      return folkbokfordRequest.personnummer();
   }

   public String toArbetsgivareApi(ArbetsgivareApiRequest arbetsgivareRequest)
   {
      return arbetsgivareRequest.personnummer();
   }

   public FolkbokfordApiResponse fromFolkbokfordApi(FolkbokforingPersnrGet200Response folkbokforingResponse)
   {
      return ImmutableFolkbokfordApiResponse.builder()
            .isFolkbokford(folkbokforingResponse.getResult())
            .build();
   }

   public ArbetsgivareApiResponse fromArbetsgivareApi(GetArbetsgivare200Response arbetsgivareResponse)
   {
      return ImmutableArbetsgivareApiResponse.builder()
            .hasArbetsgivare(arbetsgivareResponse.getResult())
            .build();
   }
}
