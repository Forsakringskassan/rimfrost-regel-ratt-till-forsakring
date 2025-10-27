package integration;

import integration.dto.IntegrationFolkbokfordRequest;
import integration.dto.IntegrationFolkbokfordResponse;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import se.fk.github.jaxrsclientfactory.JaxrsClientFactory;
import se.fk.github.jaxrsclientfactory.JaxrsClientOptionsBuilders;
import se.fk.rimfrost.api.folkbokforing.jaxrsspec.controllers.generatedsource.FolkbokforingControllerApi;

@ApplicationScoped
public class FolkbokfordIntegrationService
{

   @ConfigProperty(name = "folkbokford.api.base-url")
   String folkbokfordBaseUrl;

   @Inject
   IntegrationMapper integrationMapper;

   private FolkbokforingControllerApi folkbokfordClient;

   @PostConstruct
   void init()
   {
      this.folkbokfordClient = new JaxrsClientFactory()
            .create(JaxrsClientOptionsBuilders.createClient(folkbokfordBaseUrl, FolkbokforingControllerApi.class)
                  .build());
   }

   public IntegrationFolkbokfordResponse checkFolkbokford(IntegrationFolkbokfordRequest externalRequest)
   {
      var request = integrationMapper.toExternalApi(externalRequest);
      var response = folkbokfordClient.folkbokforingPersnrGet(request);
      return integrationMapper.fromExternalApi(response);
   }
}
