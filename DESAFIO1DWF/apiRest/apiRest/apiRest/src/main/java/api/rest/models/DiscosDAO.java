package api.rest.models;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DiscosDAO extends AppConnection{
	

	 public void insert(Disco disco) throws SQLException{
	 connect();
	 pstmt = conn.prepareStatement("insert into discos (id_disco, nombre_disco, id_artista, numero_canciones, precio) values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
	 pstmt.setInt(1, disco.getId());
	 pstmt.setString(2, disco.getName());
	 pstmt.setInt(3, disco.getId_artista());
	 pstmt.setInt(4, disco.getNcanciones());
	 pstmt.setFloat(5, disco.getPrecio());
	 pstmt.executeUpdate();

	 ResultSet keys= pstmt.getGeneratedKeys();
	 keys.next();
	 int id = keys.getInt(1);

	 disco.setId(id);
	 close();
	 }

	 public void update(Disco disco) throws SQLException{
	 connect();
	 pstmt = conn.prepareStatement("update discos set nombre_disco = ?, id_artista = ?, numero_canciones = ?, precio = ? where id_disco = ?");
	 pstmt.setString(1, disco.getName());
	 pstmt.setInt(2, disco.getId_artista());
	 pstmt.setInt(3, disco.getNcanciones());
	 pstmt.setFloat(4, disco.getPrecio());
	 pstmt.executeUpdate();
	 close();
	 }

	 public void delete(int id) throws SQLException{
	 connect();
	 pstmt = conn.prepareStatement("delete from discos where id_disco = ?");
	 pstmt.setInt(1, id);
	 pstmt.execute();
	 close();
	 }

	 public ArrayList<Disco> findAll() throws SQLException{
	 connect();
	 stmt = conn.createStatement();
	 resultSet = stmt.executeQuery("select id_disco, nombre_disco, id_artista, numero_canciones, precio from discos");
	 ArrayList<Disco> disco = new ArrayList();

	 while(resultSet.next()){
	 Disco tmp = new Disco();
	 tmp.setId(resultSet.getInt(1));
	 tmp.setName(resultSet.getString(2));
	 tmp.setId_artista(resultSet.getInt(3));
	 tmp.setNcanciones(resultSet.getInt(4));
	 tmp.setPrecio(resultSet.getInt(5));
	 disco.add(tmp);
	 }

	 close();

	 return disco;
	 }

	 public Disco findById(int id) throws SQLException{

	 Disco disco = null;

	 connect();
	 pstmt = conn.prepareStatement("select id_disco, nombre_disco, id_artista, numero_canciones, precio from discos where id_disco = ?");
	 pstmt.setInt(1, id);

	 resultSet = pstmt.executeQuery();

	 while(resultSet.next()){
	 disco = new Disco();
	 disco.setId(resultSet.getInt(1));
	 disco.setName(resultSet.getString(2));
	 disco.setId_artista(resultSet.getInt(3));
	 disco.setNcanciones(resultSet.getInt(4));
	 disco.setPrecio(resultSet.getInt(5));
	 }

	 close();
	 return disco;
	 }
}
