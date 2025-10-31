package se.fk.github.regelratttillforsakring.integration;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import se.fk.github.jaxrsclientfactory.JaxrsClientFactory;
import se.fk.github.jaxrsclientfactory.JaxrsClientOptionsBuilders;
import se.fk.github.regelratttillforsakring.integration.dto.IntegrationRtfRequest;
import se.fk.github.regelratttillforsakring.integration.dto.IntegrationRtfResponse;
import se.fk.rimfrost.api.folkbokforing.jaxrsspec.controllers.generatedsource.FolkbokforingControllerApi;
import se.fk.rimfrost.api.arbetsgivare.jaxrsspec.controllers.generatedsource.ArbetsgivareControllerApi;

@ApplicationScoped
public class RtfIntegrationService
{

   @ConfigProperty(name = "folkbokford.api.base-url")
   String folkbokfordBaseUrl;

   @ConfigProperty(name = "arbetsgivare.api.base-url")
   String arbetsgivareApiBaseUrl;

   @Inject
   IntegrationMapper integrationMapper;

   private FolkbokforingControllerApi folkbokfordClient;
   private ArbetsgivareControllerApi arbetsgivareClient;

   @PostConstruct
   void init()
   {
      this.folkbokfordClient = new JaxrsClientFactory()
            .create(JaxrsClientOptionsBuilders.createClient(folkbokfordBaseUrl, FolkbokforingControllerApi.class)
                  .build());
      this.arbetsgivareClient = new JaxrsClientFactory()
            .create(JaxrsClientOptionsBuilders.createClient(arbetsgivareApiBaseUrl, ArbetsgivareControllerApi.class)
                  .build());
   }

   public IntegrationRtfResponse checkRattTillForsakring(IntegrationRtfRequest externalRequest)
   {
      var request = integrationMapper.toExternalApi(externalRequest);
      var folkbokforingResponse = folkbokfordClient.folkbokforingPersnrGet(request);
      var arbetsgivareResponse = arbetsgivareClient.getArbetsgivare(request);
      return integrationMapper.fromExternalApi(folkbokforingResponse, arbetsgivareResponse);
   }
}
