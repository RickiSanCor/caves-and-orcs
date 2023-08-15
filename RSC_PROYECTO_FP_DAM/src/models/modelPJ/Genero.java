package models.modelPJ;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Genero {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombreGenero;
	
	@OneToMany(mappedBy = "genero", fetch = FetchType.LAZY)
    private List<Personaje> personajes;
	
	
	
	public Genero(int id, String nombreGenero) {
		super();
		this.id = id;
		this.nombreGenero = nombreGenero;
	}
	
	
	public Genero() {
		super();
	}
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreGenero() {
		return nombreGenero;
	}
	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}

	
	
	
	
	
}
