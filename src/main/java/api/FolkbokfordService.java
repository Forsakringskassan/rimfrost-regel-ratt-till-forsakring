package api;

import api.Folkbokford;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import java.net.URI;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public class FolkbokfordService
{

   @POST
   @Path("/{pnr}")
   public static boolean isBokf(@PathParam("pnr") String pnr)
   {
      try
      {
         Folkbokford client = RestClientBuilder.newBuilder() // Create API Client
               .baseUri(URI.create("http://localhost:8080"))
               .build(Folkbokford.class);

         return client.isFolkbokf(pnr).getResult(); // API Call
      }
      catch (Exception e)
      {
         throw new RuntimeException("ERROR for Pnr :" + pnr + ". " + e.getMessage());
      }
   }
}
