package models.modelPJ;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import models.modelUsuario.Usuario;

@Entity
public class Personaje {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private int salud;
	private int victorias = 0;
	private int derrotas = 0;
	private int puntos = 0;

	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "genero_id", nullable = false)
	 private Genero genero;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "raza_id", nullable = false)
	 private Raza raza;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "clase_id", nullable = false)
	 private Clase clase;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "atributos_id", nullable = false)
	 private Atributos atributos;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "usuario_id")
	 private Usuario usuario;

	public Personaje(int id, String nombre, int salud, Genero genero, Raza raza, Clase clase, Atributos atributos,
			Usuario usuario) {
		
		super();
		this.id = id;
		this.nombre = nombre;
		this.salud = salud;
		this.genero = genero;
		this.raza = raza;
		this.clase = clase;
		this.atributos = atributos;
		this.usuario = usuario;
	}
	
	public Personaje(int id, String nombre, int salud, Atributos atributos, Clase clase, Genero genero, Raza raza,
			Usuario usuario) {
		
		super();
		this.id = id;
		this.nombre = nombre;
		this.salud = salud;
		this.atributos = atributos;
		this.clase = clase;
		this.genero = genero;
		this.raza = raza;
		this.usuario = usuario;
	}
	
	public Personaje(String nombre, int salud, Genero genero, Raza raza, Clase clase, Atributos atributos,
			Usuario usuario) {
		
		super();
		this.nombre = nombre;
		this.salud = salud;
		this.genero = genero;
		this.raza = raza;
		this.clase = clase;
		this.atributos = atributos;
		this.usuario = usuario;
	}
	
	public Personaje(String nombre, int salud, Genero genero, Raza raza, Clase clase, Atributos atributos) {
		
		super();
		this.nombre = nombre;
		this.salud = salud;
		this.genero = genero;
		this.raza = raza;
		this.clase = clase;
		this.atributos = atributos;
	}

	public Personaje() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getSalud() {
		return salud;
	}

	public void setSalud(int salud) {
		this.salud = salud;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Raza getRaza() {
		return raza;
	}

	public void setRaza(Raza raza) {
		this.raza = raza;
	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	public Atributos getAtributos() {
		return atributos;
	}

	public void setAtributos(Atributos atributos) {
		this.atributos = atributos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getVictorias() {
		return victorias;
	}

	public void setVictorias(int victorias) {
		this.victorias = victorias;
	}

	public int getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	public void aumentarVictorias() {
		this.victorias++;
	}
	
	public void aumentarDerrotas() {
		this.derrotas++;
	}
	
	public void calcularPuntos() {
		this.puntos = this.victorias - this.derrotas;
	}
	
	public void calcularSalud() {
		if (this.atributos.getVigor() <= 0) {
			this.salud = 0;
		} else {
			this.salud = 3 + (this.atributos.getVigor() * 2);
		}
	}

}
