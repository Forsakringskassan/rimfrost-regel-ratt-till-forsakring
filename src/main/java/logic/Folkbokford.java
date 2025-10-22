package logic;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import logic.dto.LogicFolkbokfordResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "rtf")
@Path("/population_registration")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface Folkbokford
{

   @GET
   @Path("/{social_security_nr}")
   LogicFolkbokfordResponse isFolkbokf(@PathParam("social_security_nr") String social_security_nr);
}
