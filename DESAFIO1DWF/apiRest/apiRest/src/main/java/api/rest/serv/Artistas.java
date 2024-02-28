package api.rest.serv;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import api.rest.models.Artista;
import api.rest.models.ArtistasDAO;
import api.rest.models.Disco;

@Path("artistas")
public class Artistas {
	ArtistasDAO artistasDAO = new ArtistasDAO();
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
 
 @POST
 @Produces(MediaType.APPLICATION_JSON)
 public Response insertDisco(
 @FormParam("id_artista") int id_artista,
 @FormParam("nombre_artista") String nombre_artista,
 @FormParam("descripcion") String descripcion
 ) throws SQLException{

 Artista artista = new Artista();

 if(artistasDAO.findById(id_artista)==null){
 return Response.status(400)
 .header("Access-Control-Allow-Origin", "*")
 .entity("Artista no corresponde a ninguno registrado").build();
 }

 artista.setId(id_artista);
 artista.setName(nombre_artista);
 artista.setDesc(descripcion);
 artistasDAO.insert(artista);

 return Response.status(201)
 .header("Access-Control-Allow-Origin", "*")
 .entity(artista)
 .build();
 }
 
 @DELETE
 @Produces(MediaType.APPLICATION_JSON)
 @Path("delete/{id}")
 public Response eliminarArtista(
 @PathParam("id") int id
 ) throws SQLException{
 Artista artista = artistasDAO.findById(id);
 if(artista == null){
 return Response.status(400)
 .entity("Disco no corresponde a ningun artista")
 .header("Access-Control-Allow-Origin", "*")
 .build();
 }

 artistasDAO.delete(id);

 return Response.status(200)
 .header("Access-Control-Allow-Origin", "*")
 .entity(artista)
 .build();
 }
 
 @PUT
 @Produces(MediaType.APPLICATION_JSON)
 @Path("{id}/{id_artista}&{nombre_artista}&{numero_canciones}&{descripcion}")
 public Response updateConcepto(
	 @QueryParam("id_artista") int id_artista,
	 @QueryParam("nombre_artista") String nombre_artista,
	 @QueryParam("descripcion") String descripcion
 ) throws SQLException{

 Artista artista = artistasDAO.findById(id_artista);

 if(artista == null){
 return Response.status(404)
 .header("Access-Control-Allow-Origin", "*")
 .entity("Disco no corresponde a ninguna existencia")
 .build();
 }

 if(artistasDAO.findById(id_artista)==null){
 return Response.status(400)
 .header("Access-Control-Allow-Origin", "*")
 .entity("Artista no corresponde a ninguno registrado")
 .build();
 }

 artista.setId(id_artista);
 artista.setName(nombre_artista);
 artista.setName(descripcion);
 artistasDAO.update(artista);

 return Response.status(200)
 .header("Access-Control-Allow-Origin", "*")
 .entity(artista)
 .build();
 }

}

