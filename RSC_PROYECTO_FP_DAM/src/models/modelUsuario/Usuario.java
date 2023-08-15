package models.modelUsuario;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import models.modelPJ.Personaje;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String nombre;
	private String apellidos;
	private String email;
	private String rol;
	private String idioma = "es";

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<Personaje> personajes;

//constructores

	public Usuario() {
	}

	public Usuario(String username, String password, String nombre, String apellidos, String email, String rol) {
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.rol = rol;
	}

	public Usuario(String username, String password, String nombre, String apellidos, String email, String rol,
			String idioma) {
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.rol = rol;
		this.idioma = idioma;
	}

	public Usuario(int id, String username, String password, String nombre, String apellidos, String email, String rol,
			String idioma) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.rol = rol;
		this.idioma = idioma;

	}

//getters y setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public List<Personaje> getPersonajes() {
		return this.personajes;
	}

	public void setPersonajes(List<Personaje> personajes) {

		if (this.personajes == null) {
			this.personajes.clear();
		}
		for (Personaje personaje : personajes) {
			this.personajes.add(personaje);
		}

	}

}
