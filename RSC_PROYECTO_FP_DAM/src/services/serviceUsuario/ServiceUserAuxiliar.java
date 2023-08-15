package services.serviceUsuario;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import org.jasypt.util.password.StrongPasswordEncryptor;

import daos.daoUsuario.DAOUsuario;
import models.modelUsuario.Usuario;

public class ServiceUserAuxiliar {

	private static DAOUsuario daoUsuario = new DAOUsuario();;

	public static boolean existe(String username) {
		/*
		 * comprueba si el usuario existe dentro de la base de datos. solo tiene que
		 * comprobar al usuario no la contraseña
		 */
		ArrayList<Usuario> listaUsuarios = daoUsuario.retrieveAllUsers();

		boolean resultado = false;
		for (Usuario user : listaUsuarios) {
			if (username.equals(user.getUsername())) {
				resultado = true;
			}
		}
		return resultado;
	}

	public static boolean comprobarUserYPass(String username, String password) {
		/*
		 * comprueba si el usuario y la contraseña coinciden dentro de la base de datos
		 */
		boolean resultado = false;
		ArrayList<Usuario> listaUsuarios = daoUsuario.retrieveAllUsers();

		for (Usuario user : listaUsuarios) {
			if (username.equals(user.getUsername()) && comprobarPassword(password, user.getPassword())) {
				resultado = true;
			}
		}
		return resultado;
	}

	public static boolean comprobarPassword(String pass, String userPass) {
		// solo usar para comparar la clave introducida con la de la base de datos
		boolean coinciden = false;

		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

		// LOGIN
		if (passwordEncryptor.checkPassword(pass, userPass)) {
			coinciden = true;
		}

		return coinciden;
	}

	public static ArrayList<Usuario> getListaUsuarios() {

		ArrayList<Usuario> listaUsuarios = daoUsuario.retrieveAllUsers();

		ArrayList<Usuario> listaUsuariosNormales = filtrarNormales(listaUsuarios);
		return listaUsuariosNormales;
	}

	public static ArrayList<Usuario> getListaTodosUsuarios() {

		ArrayList<Usuario> listaUsuarios = daoUsuario.retrieveAllUsers();

		return listaUsuarios;
	}

	public static ArrayList<Usuario> getListaTodosUsuariosSinId1() {

		ArrayList<Usuario> listaUsuarios = daoUsuario.retrieveAllUsers();
		int posicion = 0;
		for (int i = 0; i < listaUsuarios.size(); i++) {
			if (listaUsuarios.get(i).getId() == 1) {
				posicion= i;
			}
		}
		listaUsuarios.remove(posicion);
		
		return listaUsuarios;
	}

	public static boolean comprobarEmail(String email) {
		/*
		 * comprueba si el email ya existe dentro de la base de datos
		 */
		ArrayList<Usuario> listaUsuarios = daoUsuario.retrieveAllUsers();
		boolean resultado = false;

		for (Usuario user : listaUsuarios) {
			if (email.equals(user.getEmail())) {

				resultado = true;
			}
		}
		return resultado;
	}

	public static ArrayList<Usuario> buscarContiene(String cadena) {
		/*
		 * busca usuarios a través de varios parámetros
		 */
		String query = "SELECT u FROM Usuario u WHERE u.rol='Normal'" + "AND (u.username like '%" + cadena + "%'"
				+ "OR u.nombre like '%" + cadena + "%'" + "OR u.apellidos like '%" + cadena + "%'"
				+ "OR u.email like '%" + cadena + "%')";

		ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) daoUsuario.list(query);

		return listaUsuarios;
	}

	public static ArrayList<Usuario> filtrarNormales(ArrayList<Usuario> listaUsuarios) {

		String query = "SELECT u FROM Usuario u WHERE u.rol='Normal'";
		ArrayList<Usuario> listaUsuariosNormales = (ArrayList<Usuario>) daoUsuario.list(query);

		return listaUsuariosNormales;
	}

	public static int buscarMaxId() {
		/*
		 * busca el último id asignado
		 */
		ArrayList<Usuario> listaUsuarios = daoUsuario.retrieveAllUsers();

		int maxId = 0;

		for (Usuario user : listaUsuarios) {
			if (maxId < user.getId()) {

				maxId = user.getId();
			}
		}
		return maxId;
	}

	public static Usuario buscarUserByNick(String username) {
		/*
		 * busca usuario a través del username
		 */
		ArrayList<Usuario> listaUsuarios = daoUsuario.retrieveAllUsers();

		Usuario userSearched = new Usuario();

		for (Usuario user : listaUsuarios) {
			if (username.equals(user.getUsername())) {

				userSearched = user;
			}
		}
		return userSearched;
	}

	public static ResourceBundle getResourceBundle(String idioma) {

		if (idioma == null) {
			idioma = "es";
		}
		Locale locale = new Locale(idioma);
		ResourceBundle rb = ResourceBundle.getBundle("idioma", locale);
		return rb;
	}

	public static void anadirUsersEjemplo() {

		Usuario user2 = new Usuario(2, "pepe", "1234", "Pepe", "González", "pepe@cavesandorcs.com", "Normal", "en");
		Usuario user3 = new Usuario(3, "mariaLaJefa", "1111", "María", "Pérez", "maria@cavesandorcs.com", "Normal",
				"es");
		Usuario user4 = new Usuario(4, "luciaSoyYo", "2222", "Lucía", "Álvarez", "lucia@cavesandorcs.com", "Normal",
				"es");
		Usuario user5 = new Usuario(5, "alberto231", "3333", "Alberto", "Ruíz", "alberto@cavesandorcs.com", "Normal",
				"es");
		Usuario user6 = new Usuario(6, "miguelon", "4321", "Miguel", "Sanz", "miguel@cavesandorcs.com", "Normal", "es");
		Usuario user7 = new Usuario(7, "phiilip", "1122", "Felipe", "Álvarez", "felipe@cavesandorcs.com", "Normal",
				"es");
		Usuario user8 = new Usuario(8, "admin2", "2321", "Admin2", "Admin2", "admin2@cavesandorcs.com", "Admin", "es");
		Usuario user9 = new Usuario(9, "leiaOrgana", "333", "Elena", "Sánchez", "elenaleia@cavesandorcs.com", "Normal",
				"es");
		Usuario user10 = new Usuario(10, "ire321", "321", "Irene", "Ramírez", "ire321@cavesandorcs.com", "Normal",
				"es");
		Usuario[] lista = { user2, user3, user4, user5, user6, user7, user8, user9, user10 };
		for (Usuario obj : lista) {
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			String encryptedPassword = passwordEncryptor.encryptPassword(obj.getPassword());
			obj.setPassword(encryptedPassword);
			daoUsuario.createUser(obj);
		}
	}

}
