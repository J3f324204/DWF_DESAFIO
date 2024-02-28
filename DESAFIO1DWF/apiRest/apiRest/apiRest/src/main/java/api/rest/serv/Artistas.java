package api.rest.serv;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import api.rest.models.Artista;
import api.rest.models.ArtistasDAO;;
@Path("artistas")
public class Artistas {

 @GET
 @Produces(MediaType.APPLICATION_JSON)
 public Response getArtistas() throws SQLException{
	 ArtistasDAO artistasDAO = new ArtistasDAO();
	 List<Artista> artista = artistasDAO.findAll();

 return Response.status(200).entity(artista).build();
 }
 @GET
 @Path("{id}")
 @Produces(MediaType.APPLICATION_JSON)
 public Response getByArtistaId(@PathParam("id") int id) throws
SQLException{
	 ArtistasDAO artistasDAO = new ArtistasDAO();
	 Artista artista = artistasDAO.findById(id);

 if(artista == null){
 return Response.status(404).build();
 }

 return Response.status(200).entity(artista).build();
 }
}

