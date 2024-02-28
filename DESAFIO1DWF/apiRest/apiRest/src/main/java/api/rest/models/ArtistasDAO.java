package api.rest.models;
import java.sql.SQLException;
import java.util.ArrayList;
public class ArtistasDAO extends AppConnection{


 public void insert(Artista artista) throws SQLException{
 connect();
 pstmt = conn.prepareStatement("insert into artistas (id_artista, nombre_artista, descripcion) values(?,?,?)");
 pstmt.setInt(1, artista.getId());
 pstmt.setString(2, artista.getName());
 pstmt.setString(3, artista.getDesc());
 pstmt.execute();
 close();
 }

 /**
 * Update all fields from categoria table using its id
 * @param categoria
 * @throws SQLException
 */
 public void update(Artista artista) throws SQLException{
 connect();
 pstmt = conn.prepareStatement("update artistas set nombre_artista = ?, descripcion = ? where id_artista = ?");
 pstmt.setString(1, artista.getName());
 pstmt.setString(2, artista.getDesc());
 pstmt.execute();
 close();
 }

 /**
 * Deletes a categoria by id
 * @param id
 */
 public void delete(int id) throws SQLException{
 connect();
 pstmt = conn.prepareStatement("delete from artistas where id_artista = ?");
 pstmt.setInt(1, id);
 pstmt.execute();
 close();
 }

 /**
 * Returns the list of categorias from table.
 * @return
 * @throws SQLException
 */
 public ArrayList<Artista> findAll() throws SQLException{
 connect();
 stmt = conn.createStatement();
 resultSet = stmt.executeQuery("select id_artista, nombre_artista, descripcion from artistas");
 ArrayList<Artista> artistas = new ArrayList();

 while(resultSet.next()){
 Artista tmp = new Artista();
 tmp.setId(resultSet.getInt(1));
 tmp.setName(resultSet.getString(2));
 tmp.setDesc(resultSet.getString(3));

 artistas.add(tmp);
 }

 close();

 return artistas;
 }

 /**
 * Find a categoria and returns it using the concepto id
 * @param id
 * @return
 * @throws SQLException
 */
 public Artista findById(int id) throws SQLException{

 Artista artista = null;

 connect();
 pstmt = conn.prepareStatement("select id_artista, nombre_artista, descripcion from artistas where id_artista = ?");
 pstmt.setInt(1, id);

 resultSet = pstmt.executeQuery();

 while(resultSet.next()){
	 artista = new Artista();
	 artista.setId(resultSet.getInt(1));
	 artista.setName(resultSet.getString(2));
	 artista.setDesc(resultSet.getString(3));
 }

 close();
 return artista;
 }
}
