package se.fk.github.regelratttillforsakring.integration;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.fk.github.jaxrsclientfactory.JaxrsClientFactory;
import se.fk.github.jaxrsclientfactory.JaxrsClientOptionsBuilders;
import se.fk.github.regelratttillforsakring.integration.dto.IntegrationFolkbokfordRequest;
import se.fk.github.regelratttillforsakring.integration.dto.IntegrationFolkbokfordResponse;
import se.fk.rimfrost.api.folkbokforing.jaxrsspec.controllers.generatedsource.FolkbokforingControllerApi;

@ApplicationScoped
public class FolkbokfordIntegrationService
{

   @ConfigProperty(name = "folkbokford.api.base-url")
   String folkbokfordBaseUrl;

   @Inject
   IntegrationMapper integrationMapper;

   private static final Logger LOGGER = LoggerFactory.getLogger(FolkbokfordIntegrationService.class);

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
      LOGGER.info("Checking Folkbokford");
      var request = integrationMapper.toExternalApi(externalRequest);
      LOGGER.info("Mapping to ExternalApi format done, sending request to: " + folkbokfordBaseUrl);
      var response = folkbokfordClient.folkbokforingPersnrGet(request);
      LOGGER.info("Response from API was: " + response);
      return integrationMapper.fromExternalApi(response);
   }
}
