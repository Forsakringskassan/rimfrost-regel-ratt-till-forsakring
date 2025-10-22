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

//@Path("/test")
//@Produces(MediaType.APPLICATION_JSON)
//public class FolkbokfordService
//{
//
//   @POST
//   @Path("/{pnr}")
//   public static boolean isBokf(@PathParam("pnr") String pnr)
//   {
//      try
//      {
//         Folkbokford client = RestClientBuilder.newBuilder() // Create API Client
//               .baseUri(URI.create("http://localhost:8080"))
//               .build(Folkbokford.class);
//
//         return client.isFolkbokf(pnr).getResult(); // API Call
//      }
//      catch (Exception e)
//      {
//         throw new RuntimeException("ERROR for Pnr :" + pnr + ". " + e.getMessage());
//      }
//   }
//}
