package services.serviceUsuario;

import java.util.ArrayList;
import java.util.Date;

import org.jasypt.util.password.StrongPasswordEncryptor;

import daos.daoUsuario.DAOUsuario;
import models.modelUsuario.Usuario;
import services.servicePJ.ServicePJ;

public class ServiceUserMain {

	private static DAOUsuario daoUsuario = new DAOUsuario();

	public static void conectarBD() {
		
		Usuario user1 = daoUsuario.retrieveUser(1);
		
		if (user1 == null)  {
			Usuario user = new Usuario(1, "admin", "321", "Admin", "Admin", "admin@cavesandorcs.com", "Admin", "es");
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			String encryptedPassword = passwordEncryptor.encryptPassword(user.getPassword());
			user.setPassword(encryptedPassword);
			daoUsuario.createUser(user);
		
			/* Empieza una parte que es opcional
			 * Aquí se rellena la tabla de usuarios con usuarios de ejemplo
			 *
			 */
			ArrayList<Usuario> listaUsuarios = daoUsuario.retrieveAllUsers();
			if (listaUsuarios.size() <= 1)  {
				
				ServiceUserAuxiliar.anadirUsersEjemplo();
			}
			/*
			 * 
			 * Termina la parte opcional
			 */
		}
		ServicePJ.comprobarTablas();
		
	}
	
	public static String login(String username, String password) {
		
		String mensajeDanger = "";
		
		//Validador campos vacíos
		if (username.equals("")  || password.equals("")) {
			mensajeDanger = "rellenarCampos";
			
			//Otros validadores
		} else {
			if (!ServiceUserAuxiliar.existe(username)) {
				//Si no existe el usuario
				mensajeDanger = "usuarioNoExiste";
				
			} else {
				if (!ServiceUserAuxiliar.comprobarUserYPass(username, password)) {
					//Si existe el usuario pero la contraseña no coicide				
					mensajeDanger = "passErronea";
					
				}
			}
		}
		return mensajeDanger;
	}
	
	public static String alta(Usuario user, String pass2) {
		
		String mensajeDanger = "";

		//Validador campos vacíos
		if (user.getUsername().equals("")  
				|| user.getPassword().equals("")  
				|| pass2.equals("")  
				|| user.getNombre().equals("")  
				|| user.getApellidos().equals("")  
				|| user.getEmail().equals("")) {
			mensajeDanger = "rellenarCampos";
		
		//Otros validadores
		} else {

			if (ServiceUserAuxiliar.existe(user.getUsername())) {
				mensajeDanger = "usernameExiste";

			} else if (ServiceUserAuxiliar.comprobarEmail(user.getEmail())) {
				mensajeDanger = "emailExiste";
			
			} else if (!user.getPassword().equals(pass2)) {
				mensajeDanger = "pass1y2Distintos";
			
			//Si todo está correcto
			} else {
				StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
				String encryptedPassword = passwordEncryptor.encryptPassword(user.getPassword());
				user.setPassword(encryptedPassword);
				user.setId(ServiceUserAuxiliar.buscarMaxId()+1);
				daoUsuario.createUser(user);
				String titulo = "Alta Cavees & Orcs";//TODO:Idiomas
				String cuerpo = "<h1>Bienvenido</h1><br/> Te has dado de alta el: " + (new Date());
//				ServiceMailSMTP.enviarCorreo(user.getEmail(), titulo, cuerpo); // Deshabilitado para evitar el envío de emails.
			}
		}
		return mensajeDanger;
	}
	
	public static String editarUsuario(Usuario user, Usuario userChanged) {
		
		String mensajeDanger = "";
		
		//Validador campos vacíos
		if (userChanged.getUsername().equals("")  
				|| userChanged.getNombre().equals("")  
				|| userChanged.getApellidos().equals("")  
				|| userChanged.getEmail().equals("")) {
			mensajeDanger = "rellenarCampos";

		//Otros validadores
		} else if (user.getId() == 1) {
			mensajeDanger = "Por seguridad, al administrador principal no se le puede editar";

		} else {

			if (ServiceUserAuxiliar.existe(userChanged.getUsername()) && !userChanged.getUsername().equals(user.getUsername())) {
				mensajeDanger = "nombreExiste";

			} else if (ServiceUserAuxiliar.comprobarEmail(userChanged.getEmail()) && !userChanged.getPassword().equals(user.getPassword())) {
				mensajeDanger = "emailExiste";
				
			} else {
				//Si todo está correcto
				userChanged.setIdioma(user.getIdioma());
				userChanged.setId(user.getId());
				daoUsuario.updateUser(userChanged);

			}
		}
		return mensajeDanger;
	}
	
	public static String cambiarPass(Usuario user, String actualPass, String pass, String pass2) {
		
		String mensajeDanger = "";
		
		//Validador campos vacíos
		if (actualPass.equals("")
				|| pass.equals("") 
				|| pass2.equals("")) {
			mensajeDanger = "rellenarCampos";
		
		//Otros validadores
		} else if (user.getId() == 1) {
			mensajeDanger = "Por seguridad, al administrador principal no se le puede editar";
			
		} else {
			if (!ServiceUserAuxiliar.comprobarPassword(actualPass, user.getPassword())) {
				mensajeDanger = "passActualErronea";
				
			} else if (!pass.equals(pass2)) {
				mensajeDanger = "pass1y2Distintos";
				
			//Si todo está correcto
			} else {

				StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
				String encryptedPassword = passwordEncryptor.encryptPassword(pass);
				user.setPassword(encryptedPassword);
				daoUsuario.updateUser(user);
				String titulo = "Cambio contraseña de la cuenta de Caves & orcs";//TODO:Idiomas
				String cuerpo = "Has cambiado tu contraseña el: " + (new Date());
//				ServiceMailSMTP.enviarCorreo(user.getEmail(), titulo, cuerpo); // Deshabilitado para evitar el envío de emails.
			}
		}
		return mensajeDanger;
	}

	public static void borrarUsuario(Usuario user) {
		
		String email = user.getEmail();
		daoUsuario.deleteUser(user.getId());
		String titulo = "Eliminación de cuenta de Caves & orcs";//TODO:Idiomas
		String cuerpo = "Tu cuenta ha sido borrada por un administrador el: " + (new Date());
//		ServiceMailSMTP.enviarCorreo(email, titulo, cuerpo); // Deshabilitado para evitar el envío de emails.
	}
	
	
	public static String buscarUsuario(String cadenaBuscada) {
		
		String mensajeDanger = "";
	
		if (cadenaBuscada.equals("")) {
			mensajeDanger = "buscarVacio";
		} else {
			ArrayList<Usuario> listaBuscada = ServiceUserAuxiliar.buscarContiene(cadenaBuscada);
			if (listaBuscada.size() == 0) {
				mensajeDanger = "noHayUsuario";
			}
		}
			return mensajeDanger;
	}

}
