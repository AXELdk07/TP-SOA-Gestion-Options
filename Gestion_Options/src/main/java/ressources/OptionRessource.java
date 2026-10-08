package ressources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Option;
import metiers.OptionBusiness;

@Path("/options")
public class OptionRessource {
     OptionBusiness optionBusiness = new OptionBusiness();
@GET
@Produces (MediaType.APPLICATION_JSON)
public Response getAllOptions() {

    return Response.status(Response.Status.OK).entity(optionBusiness.getListeOptions()).build();

    
}
@GET
@Path ("/{code}")

@Produces (MediaType.APPLICATION_JSON)
public Object getOptionByCode(@PathParam("code") int code) {
    return optionBusiness.getOptionByCode(code);
}

@Path("/create")
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Option createOption(Option option) {
    optionBusiness.addOption(option);
    return option;
}

}
