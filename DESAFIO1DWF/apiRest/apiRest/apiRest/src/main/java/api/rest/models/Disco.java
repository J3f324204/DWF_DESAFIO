package api.rest.models;

public class Disco {
	 private int id;
	 private String name;
	 private int id_artista;
	 private int ncanciones;
	 private float precio;
	 private Artista artista;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId_artista() {
		return id_artista;
	}
	public void setId_artista(int id_artista) {
		this.id_artista = id_artista;
	}
	public int getNcanciones() {
		return ncanciones;
	}
	public void setNcanciones(int ncanciones) {
		this.ncanciones = ncanciones;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
}
