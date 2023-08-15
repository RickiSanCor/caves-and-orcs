package services.servicePJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

import daos.daoPJ.DAOAtributos;
import daos.daoPJ.DAOClase;
import daos.daoPJ.DAOGenero;
import daos.daoPJ.DAOPersonaje;
import daos.daoPJ.DAORaza;
import daos.daoUsuario.DAOUsuario;
import services.serviceUsuario.ServiceUserAuxiliar;
import models.modelUsuario.Usuario;
import models.modelPJ.Atributos;
import models.modelPJ.Clase;
import models.modelPJ.Genero;
import models.modelPJ.Personaje;
import models.modelPJ.Raza;

public class ServicePJ {

	private static DAOGenero daoGenero = new DAOGenero();
	private static DAOClase daoClase = new DAOClase();
	private static DAORaza daoRaza = new DAORaza();
	private static DAOAtributos daoAtributos = new DAOAtributos();
	private static DAOPersonaje daoPersonaje = new DAOPersonaje();
	private static DAOUsuario daoUsuario = new DAOUsuario();

	public static Personaje retrievePersonajeByID(int id) {
		Personaje personaje = daoPersonaje.retrievePersonaje(id);
		return personaje;
	}

	public static void borrarPersonaje(Personaje pjParaBorrar, Usuario usuario) {
		daoAtributos.deleteAtributos(pjParaBorrar.getAtributos().getId());
		daoPersonaje.deletePersonaje(pjParaBorrar.getId());
		usuario.setPersonajes(retrievePJsByUser(usuario));
	}

	public static void editarPersonajeBD(Personaje pjEditar) {

		daoPersonaje.updatePersonaje(pjEditar);
	}

	public static Personaje disminuirAtributos(Personaje pjEditar) {

		int fuerza = pjEditar.getAtributos().getFuerza();
		int agilidad = pjEditar.getAtributos().getAgilidad();
		int vigor = pjEditar.getAtributos().getVigor();
		int magia = pjEditar.getAtributos().getMagia();
		int labia = pjEditar.getAtributos().getLabia();
		int[] atributos = { fuerza, agilidad, vigor, magia, labia };

		for (int i = 0; i < atributos.length; i++) {
			if (atributos[i] > 0) {
				int item = atributos[i] - 1;
				atributos[i] = item;
			}
		}
		pjEditar.getAtributos().setFuerza(atributos[0]);
		pjEditar.getAtributos().setAgilidad(atributos[1]);
		pjEditar.getAtributos().setVigor(atributos[2]);
		pjEditar.getAtributos().setMagia(atributos[3]);
		pjEditar.getAtributos().setLabia(atributos[4]);

		return pjEditar;
	}

	public static void editarAtributosPersonaje(Personaje pjEditar) {

		Atributos atributos = pjEditar.getAtributos();
		atributos.setFuerza(pjEditar.getAtributos().getFuerza());
		atributos.setAgilidad(pjEditar.getAtributos().getAgilidad());
		atributos.setVigor(pjEditar.getAtributos().getVigor());
		atributos.setMagia(pjEditar.getAtributos().getMagia());
		atributos.setLabia(pjEditar.getAtributos().getLabia());
	}

	public static void editarAtributosPersonajeEnBD(Personaje pjEditar) {

		daoAtributos.updateAtributos(pjEditar.getAtributos());
	}

	public static Personaje createNewPartialPJ(ArrayList<String> parametrosPJ, int[] atributosPJ, Usuario usuario) {

		Atributos atributos = new Atributos(atributosPJ[0], atributosPJ[1], atributosPJ[2], atributosPJ[3],
				atributosPJ[4]);

//		 Salud = 3 + (vigor * 2) 
		int salud = 3 + (atributosPJ[2] * 2);

		Personaje personaje = new Personaje(parametrosPJ.get(0), salud, retrieveGeneroByName(parametrosPJ.get(1)),
				retrieveRazaByName(parametrosPJ.get(2)), retrieveClaseByName(parametrosPJ.get(3)), atributos);

		return personaje;
	}

	public static Personaje createNewPJ(ArrayList<String> parametrosPJ, int[] atributosPJ, Usuario usuario) {

		Atributos atributos = new Atributos(atributosPJ[0], atributosPJ[1], atributosPJ[2], atributosPJ[3],
				atributosPJ[4]);

		daoAtributos.createAtributos(atributos);

//		 Salud = 3 + (vigor * 2) 
		int salud = 3 + (atributosPJ[2] * 2);

		Personaje personaje = new Personaje(parametrosPJ.get(0), salud, retrieveGeneroByName(parametrosPJ.get(1)),
				retrieveRazaByName(parametrosPJ.get(2)), retrieveClaseByName(parametrosPJ.get(3)), atributos);

		daoPersonaje.createPersonaje(personaje);
		if (usuario != null) {
			usuario.setPersonajes(retrievePJsByUser(usuario));
		}

		return personaje;
	}

	public static void guardarPersonaje(Personaje personaje, Usuario usuario) {

		personaje.setUsuario(usuario);
		daoPersonaje.updatePersonaje(personaje);
		usuario.setPersonajes(retrievePJsByUser(usuario));

	}

	public static String comprobarBuscarPJ(String cadenaBuscada, Usuario usuario) {

		String mensajeDanger = "";

		if (cadenaBuscada.equals("")) {
			mensajeDanger = "buscarVacio";
		} else {
			ArrayList<Personaje> listaBuscada = buscarPJContieneConIdioma(cadenaBuscada, usuario);
			if (listaBuscada.size() == 0) {
				mensajeDanger = "noHayPersonaje";
			}
		}
		return mensajeDanger;
	}

	public static ArrayList<Personaje> buscarPJContieneConIdioma(String cadena, Usuario usuario) {
		/*
		 * busca personajes a través de varios parámetros independientemente del idioma
		 */
		String idioma = usuario.getIdioma();
		ResourceBundle rb = ServiceUserAuxiliar.getResourceBundle(idioma);

		ArrayList<Personaje> listaPersonajes = retrievePJsByUser(usuario);
		ArrayList<Personaje> listaBuscada = new ArrayList<Personaje>();

		for (Personaje personaje : listaPersonajes) {

			String genero = rb.getString(personaje.getGenero().getNombreGenero().toLowerCase());
			String raza = rb.getString(personaje.getRaza().getNombreRaza().toLowerCase());
			String clase = rb.getString(personaje.getClase().getNombreClase().toLowerCase());

			if (personaje.getNombre().toLowerCase().contains(cadena.toLowerCase().trim())
					|| genero.toLowerCase().contains(cadena.toLowerCase().trim())
					|| raza.toLowerCase().contains(cadena.toLowerCase().trim())
					|| clase.toLowerCase().contains(cadena.toLowerCase().trim())) {

				listaBuscada.add(personaje);
			}
		}

		return listaBuscada;

	}

	public static ArrayList<Personaje> retrievePJsByUser(Usuario usuario) {
		/*
		 * devuelve lista de personajes del usuario
		 */
		String query = "SELECT p FROM Personaje p WHERE p.usuario.id='" + usuario.getId() + "'";

		ArrayList<Personaje> listaPersonajes = (ArrayList<Personaje>) daoPersonaje.list(query);

		return listaPersonajes;
	}

	public static void comprobarTablas() {
		/*
		 * Comprueba que las tablas genero, raza y clase tengan datos y de lo contrario
		 * las rellena. Opcionalmente puede rellenar las tablas de personajes y
		 * atributos con datos de prueba.
		 */

		ArrayList<Genero> listaGeneros = daoGenero.retrieveAllGeneros();
		if (listaGeneros.size() <= 1) {
			anadirGeneros();
		}

		ArrayList<Raza> listaRazas = daoRaza.retrieveAllRazas();
		if (listaRazas.size() <= 1) {
			anadirRazas();
		}

		ArrayList<Clase> listaClases = daoClase.retrieveAllClases();
		if (listaClases.size() <= 1) {
			anadirClases();
		}

		/*
		 * Empieza una parte que es opcional Aquí se rellena la tabla de usuarios con
		 * usuarios de ejemplo
		 *
		 */
		ArrayList<Atributos> listaAtributos = daoAtributos.retrieveAllAtributos();
		if (listaAtributos.size() <= 1) {
			anadirAtributosEjemplo();
		}

		ArrayList<Personaje> listaPersonajes = daoPersonaje.retrieveAllPersonajes("");
		if (listaPersonajes.size() <= 1) {
			anadirPersonajesEjemplo();
		}
		/*
		 * 
		 * Termina la parte opcional
		 */
	}

	public static ArrayList<Genero> getListaGenero() {
		ArrayList<Genero> listaGenero = daoGenero.retrieveAllGeneros();
		return listaGenero;
	}

	public static ArrayList<Raza> getListaRaza() {
		ArrayList<Raza> listaRaza = daoRaza.retrieveAllRazas();
		return listaRaza;
	}

	public static ArrayList<Clase> getListaClase() {
		ArrayList<Clase> listaClase = daoClase.retrieveAllClases();
		return listaClase;
	}

	public static ArrayList<Atributos> getListaAtributos() {
		ArrayList<Atributos> listaAtributos = daoAtributos.retrieveAllAtributos();
		return listaAtributos;
	}

	public static ArrayList<Personaje> getListaPersonajesRanking() {

		ArrayList<Personaje> listaPersonajes = daoPersonaje.retrieveAllPersonajes("");
		ArrayList<Personaje> listaBorrar = new ArrayList<Personaje>();
		for (int i = 0; i < listaPersonajes.size(); i++) {
			if (listaPersonajes.get(i).getUsuario() == null) {
				listaBorrar.add(listaPersonajes.get(i));
			}
		}
		for (int i = 0; i < listaBorrar.size(); i++) {
			listaPersonajes.remove(listaBorrar.get(i));
		}
		Collections.sort(listaPersonajes, new Comparator<Personaje>() {
			@Override
			public int compare(Personaje personaje1, Personaje personaje2) {
				return personaje2.getPuntos() - personaje1.getPuntos();
			}
		});

		return listaPersonajes;
	}

	public static Genero retrieveGeneroByName(String parametro) {
		String query = "SELECT g FROM Genero g WHERE g.nombreGenero='" + parametro + "'";
		Genero genero = daoGenero.retrieveGeneroNoId(query);
		return genero;
	}

	public static Raza retrieveRazaByName(String parametro) {
		String query = "SELECT r FROM Raza r WHERE r.nombreRaza='" + parametro + "'";
		Raza raza = daoRaza.retrieveRazaNoId(query);
		return raza;
	}

	public static Clase retrieveClaseByName(String parametro) {
		String query = "SELECT c FROM Clase c WHERE c.nombreClase='" + parametro + "'";
		Clase clase = daoClase.retrieveClaseNoId(query);
		return clase;
	}

	public static void anadirGeneros() {

		Genero obj1 = new Genero(1, "masculino");
		Genero obj2 = new Genero(2, "femenino");
		Genero[] lista = { obj1, obj2 };
		for (Genero obj : lista) {
			daoGenero.createGenero(obj);
		}
	}

	public static void anadirRazas() {

		Raza obj1 = new Raza(1, "humano", 1, 1, 1, 0, 2);
		Raza obj2 = new Raza(2, "elfo", 0, 3, 0, 2, 0);
		Raza obj3 = new Raza(3, "enano", 2, 0, 3, 0, 0);
		Raza obj4 = new Raza(4, "mediano", 0, 2, 0, 0, 3);
		Raza obj5 = new Raza(5, "gnomo", 0, 1, 0, 4, 0);
		Raza[] lista = { obj1, obj2, obj3, obj4, obj5 };
		for (Raza obj : lista) {
			daoRaza.createRaza(obj);
		}
	}

	public static void anadirClases() {

		Clase obj1 = new Clase(1, "soldado", 3, 3, 4, 0, 0);
		Clase obj2 = new Clase(2, "nomada", 3, 2, 5, 0, 0);
		Clase obj3 = new Clase(3, "cazador", 3, 2, 3, 2, 0);
		Clase obj4 = new Clase(4, "ladron", 1, 5, 2, 0, 2);
		Clase obj5 = new Clase(5, "juglar", 0, 3, 2, 0, 5);
		Clase obj6 = new Clase(6, "mago", 0, 2, 1, 5, 2);
		Clase obj7 = new Clase(7, "brujo", 0, 2, 1, 4, 3);
		Clase obj8 = new Clase(8, "ermitanio", 1, 2, 3, 3, 1);
		Clase[] lista = { obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8};
		for (Clase obj : lista) {
			daoClase.createClase(obj);
		}
	}

	public static void anadirAtributosEjemplo() {

		Atributos obj1 = new Atributos(1, 4, 3, 4, 0, 0);
		Atributos obj2 = new Atributos(2, 4, 3, 4, 0, 0);
		Atributos obj3 = new Atributos(3, 6, 4, 6, 0, 0);
		Atributos obj4 = new Atributos(4, 2, 3, 5, 7, 3);
		Atributos obj5 = new Atributos(5, 4, 8, 6, 2, 0);
		Atributos obj6 = new Atributos(6, 0, 3, 1, 14, 2);
		Atributos obj7 = new Atributos(7, 5, 2, 13, 0, 0);

		Atributos[] lista = { obj1, obj2, obj3, obj4, obj5, obj6, obj7 };
		for (Atributos obj : lista) {
			daoAtributos.createAtributos(obj);
		}
	}

	public static void anadirPersonajesEjemplo() {

		Personaje obj1 = new Personaje(1, "orco1", 11, daoAtributos.retrieveAtributos(1), daoClase.retrieveClase(1),
				daoGenero.retrieveGenero(1), daoRaza.retrieveRaza(1), null);
		Personaje obj2 = new Personaje(2, "orco2", 11, daoAtributos.retrieveAtributos(2), daoClase.retrieveClase(1),
				daoGenero.retrieveGenero(1), daoRaza.retrieveRaza(1), null);
		Personaje obj3 = new Personaje(3, "jefeOrco", 15, daoAtributos.retrieveAtributos(3), daoClase.retrieveClase(1),
				daoGenero.retrieveGenero(1), daoRaza.retrieveRaza(1), null);
		Personaje obj4 = new Personaje(4, "Goku", 13, daoAtributos.retrieveAtributos(4), daoClase.retrieveClase(8),
				daoGenero.retrieveGenero(1), daoRaza.retrieveRaza(1), daoUsuario.retrieveUser(2));
		Personaje obj5 = new Personaje(5, "Leia", 15, daoAtributos.retrieveAtributos(5), daoClase.retrieveClase(1),
				daoGenero.retrieveGenero(2), daoRaza.retrieveRaza(2), daoUsuario.retrieveUser(2));
		Personaje obj6 = new Personaje(6, "Morgana", 5, daoAtributos.retrieveAtributos(6), daoClase.retrieveClase(6),
				daoGenero.retrieveGenero(2), daoRaza.retrieveRaza(5), daoUsuario.retrieveUser(2));
		Personaje obj7 = new Personaje(7, "Albatros", 29, daoAtributos.retrieveAtributos(7), daoClase.retrieveClase(2),
				daoGenero.retrieveGenero(1), daoRaza.retrieveRaza(3), daoUsuario.retrieveUser(2));

		Personaje[] lista = { obj1, obj2, obj3, obj4, obj5, obj6, obj7 };
		for (Personaje obj : lista) {
			daoPersonaje.createPersonaje(obj);

		}
	}

	public static int[] getAtributosBase(String nombreRaza, String nombreClase) {

		int[] atributosBase = new int[5];

		if (nombreRaza.equals("") || nombreClase.equals("")) {
			return atributosBase;
		}

		Raza raza = retrieveRazaByName(nombreRaza);
		Clase clase = retrieveClaseByName(nombreClase);
		int[] atributosRaza = { raza.getFuerza(), raza.getAgilidad(), raza.getVigor(), raza.getMagia(),
				raza.getLabia() };
		int[] atributosClase = { clase.getFuerza(), clase.getAgilidad(), clase.getVigor(), clase.getMagia(),
				clase.getLabia() };
		for (int i = 0; i < 5; i++) {
			atributosBase[i] = atributosRaza[i] + atributosClase[i];
		}
		return atributosBase;
	}

	// public static String firstToMayus(String valor) {
//	    if (valor == null || valor.isEmpty()) {
//	        return valor;
//	    } else {       
//	        return  valor.toUpperCase().charAt(0) + valor.substring(1, valor.length()).toLowerCase();
//	    }
//	}

//	public static int retrieveIdAtributos(String parametro) {
//		String query = "SELECT g FROM Atributos g WHERE g.nombreAtributos='%" + parametro + "%'";
//		Atributos atributos = daoAtributos.retrieveAtributosNoId(query);
//		return atributos.getId();
//	}
}
