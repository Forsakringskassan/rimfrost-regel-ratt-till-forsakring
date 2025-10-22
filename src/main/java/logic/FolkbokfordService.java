package logic;

import integration.IntegrationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import logic.dto.LogicFolkbokfordRequest;
import logic.dto.LogicFolkbokfordResponse;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import java.net.URI;

@ApplicationScoped
public class FolkbokfordService
{

   @Inject
   IntegrationService folkbokfordIntegration;

   @Inject
   LogicMapper logicMapper;

   public LogicFolkbokfordResponse checkFolkbokford(LogicFolkbokfordRequest request)
   {
      var integrationRequest = logicMapper.toIntegration(request);
      var integrationResponse = folkbokfordIntegration.checkFolkbokford(integrationRequest);
      return logicMapper.toLogic(integrationResponse);
   }
}
