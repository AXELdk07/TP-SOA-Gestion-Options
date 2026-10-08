package ressources;

import entities.Etudiant;
import metiers.EtudiantBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/etudiants")
public class StudentRessource {

    private EtudiantBusiness etudiantBusiness = new EtudiantBusiness();

    // GET /rest/etudiants : Liste de tous les étudiants
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEtudiants() {
        List<Etudiant> etudiants = etudiantBusiness.getAllEtudiants();
        return Response.status(Response.Status.OK).entity(etudiants).build();
    }

    // GET /rest/etudiants/{identifiant} : Rechercher un étudiant par son identifiant
    @GET
    @Path("/{identifiant}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantByIdentifiant(@PathParam("identifiant") String identifiant) {
        Etudiant etudiant = etudiantBusiness.getEtudiantByIdentifiant(identifiant);
        if (etudiant != null) {
            return Response.status(Response.Status.OK).entity(etudiant).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Étudiant introuvable").build();
        }
    }

    // POST /rest/etudiants : Ajouter un étudiant
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEtudiant(Etudiant etudiant) {
        if (etudiant == null || etudiant.getOption() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Informations invalides ou option manquante").build();
        }
        boolean success = etudiantBusiness.addEtudiant(etudiant);
        if (success) {
            return Response.status(Response.Status.CREATED).entity(etudiant).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("L'option spécifiée n'existe pas").build();
        }
    }

    // PUT /rest/etudiants/{identifiant} : Modifier un étudiant
    @PUT
    @Path("/{identifiant}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEtudiant(@PathParam("identifiant") String identifiant, Etudiant etudiant) {
        boolean updated = etudiantBusiness.updateEtudiant(identifiant, etudiant);
        if (updated) {
            return Response.status(Response.Status.OK).entity(etudiant).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // DELETE /rest/etudiants/{identifiant} : Supprimer un étudiant
    @DELETE
    @Path("/{identifiant}")
    public Response deleteEtudiant(@PathParam("identifiant") String identifiant) {
        boolean deleted = etudiantBusiness.deleteEtudiant(identifiant);
        if (deleted) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
