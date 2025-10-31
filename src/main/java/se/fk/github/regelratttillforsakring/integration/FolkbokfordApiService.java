package se.fk.github.regelratttillforsakring.integration;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import se.fk.github.jaxrsclientfactory.JaxrsClientFactory;
import se.fk.github.jaxrsclientfactory.JaxrsClientOptionsBuilders;
import se.fk.github.regelratttillforsakring.integration.dto.FolkbokfordApiRequest;
import se.fk.github.regelratttillforsakring.integration.dto.FolkbokfordApiResponse;
import se.fk.rimfrost.api.folkbokforing.jaxrsspec.controllers.generatedsource.FolkbokforingControllerApi;

@ApplicationScoped
public class FolkbokfordApiService
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

   public FolkbokfordApiResponse checkFolkbokford(FolkbokfordApiRequest folkbokfordRequest)
   {
      var request = integrationMapper.toFolkbokfordApi(folkbokfordRequest);
      var folkbokforingResponse = folkbokfordClient.folkbokforingPersnrGet(request);
      return integrationMapper.fromFolkbokfordApi(folkbokforingResponse);
   }
}
