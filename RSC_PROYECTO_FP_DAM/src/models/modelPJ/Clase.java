package models.modelPJ;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Clase {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombreClase;
	private int fuerza;
	private int agilidad;
	private int vigor;
	private int magia;
	private int labia;
	
	@OneToMany(mappedBy = "clase", fetch = FetchType.LAZY)
    private List<Personaje> personajes;
	
	
	public Clase() {
		super();
	}

	
	public Clase(int id, String nombreClase, int fuerza, int agilidad, int vigor, int magia, int labia) {
		super();
		this.id = id;
		this.nombreClase = nombreClase;
		this.fuerza = fuerza;
		this.agilidad = agilidad;
		this.vigor = vigor;
		this.magia = magia;
		this.labia = labia;
	}
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreClase() {
		return nombreClase;
	}
	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}
	public int getFuerza() {
		return fuerza;
	}
	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}
	public int getAgilidad() {
		return agilidad;
	}
	public void setAgilidad(int agilidad) {
		this.agilidad = agilidad;
	}
	public int getVigor() {
		return vigor;
	}
	public void setVigor(int vigor) {
		this.vigor = vigor;
	}
	public int getMagia() {
		return magia;
	}
	public void setMagia(int magia) {
		this.magia = magia;
	}
	public int getLabia() {
		return labia;
	}
	public void setLabia(int labia) {
		this.labia = labia;
	}
	

}
