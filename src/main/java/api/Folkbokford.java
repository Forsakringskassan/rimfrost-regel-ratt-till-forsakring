package api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "rtf")
@Path("/population_registration")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface Folkbokford
{

   @GET
   @Path("/{social_security_nr}")
   FolkbokfordResponse isFolkbokf(@PathParam("social_security_nr") String social_security_nr);
}
