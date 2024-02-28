package api.rest.serv;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
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
import api.rest.models.ArtistasDAO;
import api.rest.models.Disco;
import api.rest.models.DiscosDAO;

@Path("discos")
public class Discos {

	 DiscosDAO discosDAO = new DiscosDAO();
	 ArtistasDAO artistasDAO = new ArtistasDAO();

	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response getDiscos() throws SQLException{

	 List<Disco> disco = discosDAO.findAll();
	 return Response.status(200).entity(disco).build();
	 }

	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("{id}")
	 public Response getDiscosById(@PathParam("id") int id) throws
	SQLException{

	 Disco disco = discosDAO.findById(id);
	 if(disco == null){
	 return Response.status(404)
	 .header("Access-Control-Allow-Origin", "*")
	 .entity("Disco no encontrado").build();
	 }
	 return Response.status(200)
	 .header("Access-Control-Allow-Origin", "*")
	 .entity(disco).build();
	 }

	 @POST
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response insertDisco(
	 @FormParam("id_disco") int id_disco,
	 @FormParam("nombre_disco") String nombre_disco,
	 @FormParam("id_artista") int id_artista,
	 @FormParam("numero_canciones") int numero_canciones,
	 @FormParam("precio") int precio
	 ) throws SQLException{

	 Disco disc = new Disco();

	 if(artistasDAO.findById(id_artista)==null){
	 return Response.status(400)
	 .header("Access-Control-Allow-Origin", "*")
	 .entity("Artista no corresponde a ninguno registrado").build();
	 }

	 disc.setId(id_disco);
	 disc.setName(nombre_disco);
	 disc.setId_artista(id_artista);
	 disc.setNcanciones(numero_canciones);
	 disc.setPrecio(precio);
	 discosDAO.insert(disc);

	 return Response.status(201)
	 .header("Access-Control-Allow-Origin", "*")
	 .entity(disc)
	 .build();
	 }

	 //@DELETE
	 @DELETE
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("delete/{id}")
	 public Response eliminarDisco(
	 @PathParam("id") int id
	 ) throws SQLException{
	 Disco disco = discosDAO.findById(id);
	 if(disco == null){
	 return Response.status(400)
	 .entity("Disco no corresponde a ningun artista")
	 .header("Access-Control-Allow-Origin", "*")
	 .build();
	 }

	 discosDAO.delete(id);

	 return Response.status(200)
	 .header("Access-Control-Allow-Origin", "*")
	 .entity(disco)
	 .build();
	 }


	 @PUT
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("{id}/{nombre_disco}&{id_artista}&{numero_canciones}&{precio}")
	 public Response updateConcepto(
	     @QueryParam("id") int id,
	     @QueryParam("nombre_disco") String nombre_disco,
	     @QueryParam("id_artista") int id_artista,
	     @QueryParam("numero_canciones") int numero_canciones,
	     @QueryParam("precio") float precio
	 ) throws SQLException{

	 Disco disco = discosDAO.findById(id);

	 if(disco == null){
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

	 disco.setId(id);
	 disco.setName(nombre_disco);
	 disco.setId_artista(id_artista);
	 disco.setNcanciones(numero_canciones);
	 disco.setPrecio(precio);
	 discosDAO.update(disco);

	 return Response.status(200)
	 .header("Access-Control-Allow-Origin", "*")
	 .entity(disco)
	 .build();
	 }
}
