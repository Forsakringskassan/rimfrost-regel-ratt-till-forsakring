package se.fk.github.regelratttillforsakring.integration;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import se.fk.github.jaxrsclientfactory.JaxrsClientFactory;
import se.fk.github.jaxrsclientfactory.JaxrsClientOptionsBuilders;
import se.fk.github.regelratttillforsakring.integration.dto.ArbetsgivareApiRequest;
import se.fk.github.regelratttillforsakring.integration.dto.ArbetsgivareApiResponse;
import se.fk.rimfrost.api.arbetsgivare.jaxrsspec.controllers.generatedsource.ArbetsgivareControllerApi;

@ApplicationScoped
public class ArbetsgivareApiService
{

   @ConfigProperty(name = "arbetsgivare.api.base-url")
   String arbetsgivareApiBaseUrl;

   @Inject
   IntegrationMapper integrationMapper;

   private ArbetsgivareControllerApi arbetsgivareClient;

   @PostConstruct
   void init()
   {
      this.arbetsgivareClient = new JaxrsClientFactory()
            .create(JaxrsClientOptionsBuilders.createClient(arbetsgivareApiBaseUrl, ArbetsgivareControllerApi.class)
                  .build());
   }

   public ArbetsgivareApiResponse checkArbetsgivare(ArbetsgivareApiRequest arbetsgivareRequest)
   {
      var request = integrationMapper.toArbetsgivareApi(arbetsgivareRequest);
      var arbetsgivareResponse = arbetsgivareClient.getArbetsgivare(request);
      return integrationMapper.fromArbetsgivareApi(arbetsgivareResponse);
   }
}
