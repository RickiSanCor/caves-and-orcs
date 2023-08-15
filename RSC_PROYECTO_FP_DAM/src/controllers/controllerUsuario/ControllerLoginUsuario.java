package controllers.controllerUsuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.modelPJ.Atributos;
import models.modelPJ.Clase;
import models.modelPJ.Genero;
import models.modelPJ.Personaje;
import models.modelPJ.Raza;
import models.modelUsuario.Usuario;
import services.servicePJ.ServicePJ;
import services.serviceUsuario.ServiceUserAuxiliar;
import services.serviceUsuario.ServiceUserMain;

/**
 * Servlet implementation class ControllerLoginMain
 */
@WebServlet("")
public class ControllerLoginUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerLoginUsuario() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServiceUserMain.conectarBD();

		request.getSession().setAttribute("mensajeInfo", "");
		request.getSession().setAttribute("mensajeDanger", "");

		Usuario userLogged = (Usuario) request.getSession().getAttribute("userLogged");

		String idioma = request.getParameter("idioma");
		String visitPage = request.getParameter("visitPage");

		if (visitPage == null) {
			String idiomaCambiado = request.getParameter("idiomaCambiado");

			if (idiomaCambiado != null) {
				visitPage = (String) request.getSession().getAttribute("visitPage");

				if (userLogged != null) {
					Usuario userChanged = new Usuario(userLogged.getUsername(), userLogged.getPassword(),
							userLogged.getNombre(), userLogged.getApellidos(), userLogged.getEmail(),
							userLogged.getRol());

					userLogged.setIdioma(idioma);
					ServiceUserMain.editarUsuario(userLogged, userChanged);
					userChanged = ServiceUserAuxiliar.buscarUserByNick(userLogged.getUsername());
					request.getSession().setAttribute("userLogged", userChanged);

				}
			}
		}
		if (idioma != null) {
			request.getSession().setAttribute("idioma", idioma);
		}

		request.getSession().setAttribute("visitPage", visitPage);

		if (visitPage == null || visitPage.equals("index")) {
			response.sendRedirect(request.getContextPath() + "/jsp/index/index.jsp");

		} else if (visitPage.equals("login")) {
			request.getSession().setAttribute("visitPage", "login");
			response.sendRedirect(request.getContextPath() + "/jsp/actions/login.jsp");

		} else if (visitPage.equals("alta")) {
			request.getSession().setAttribute("visitPage", "alta");
			response.sendRedirect(request.getContextPath() + "/jsp/actions/alta.jsp");

		} else if (visitPage.equals("perfil")) {
			request.getSession().setAttribute("visitPage", "perfil");
			response.sendRedirect(request.getContextPath() + "/jsp/actions/perfil.jsp");

		} else if (visitPage.equals("editarPerfil")) {
			request.getSession().setAttribute("visitPage", "editarPerfil");
			response.sendRedirect(request.getContextPath() + "/jsp/actions/editarPerfil.jsp");

		} else if (visitPage.equals("cambiarPass")) {
			request.getSession().setAttribute("visitPage", "cambiarPass");
			response.sendRedirect(request.getContextPath() + "/jsp/actions/cambiarPass.jsp");

		} else if (visitPage.equals("cerrarSesion")) {
			request.getSession().setAttribute("pjCreado", null);
			request.getSession().setAttribute("userLogged", null);
			request.getSession().setAttribute("visitPage", null);
			response.sendRedirect(request.getContextPath() + "/jsp/index/index.jsp");

		} else if (visitPage.equals("editarUserConAdmin")) {
			request.getSession().setAttribute("visitPage", "editarUserConAdmin");
			response.sendRedirect(request.getContextPath() + "/jsp/actions/editarUserConAdmin.jsp");

		} else if (visitPage.equals("borrarUser")) {
			request.getSession().setAttribute("visitPage", "borrarUser");
			response.sendRedirect(request.getContextPath() + "/jsp/actions/borrarUser.jsp");

		} else if (visitPage.equals("crearPJ")) {
			if ((request.getSession().getAttribute("listaGenero") == null)
					|| (request.getSession().getAttribute("listaRaza") == null)
					|| (request.getSession().getAttribute("listaClase") == null)) {

				ArrayList<Genero> listaGenero = ServicePJ.getListaGenero();
				request.getSession().setAttribute("listaGenero", listaGenero);
				ArrayList<Raza> listaRaza = ServicePJ.getListaRaza();
				request.getSession().setAttribute("listaRaza", listaRaza);
				ArrayList<Clase> listaClase = ServicePJ.getListaClase();
				request.getSession().setAttribute("listaClase", listaClase);
			}
			request.getSession().setAttribute("visitPage", "crearPJ");
			response.sendRedirect(request.getContextPath() + "/jsp/pj/crearPJ.jsp");

		} else if (visitPage.equals("crearAtribPJ")) {
			if ((request.getSession().getAttribute("listaAtributos") == null)) {

				ArrayList<Atributos> listaAtributos = ServicePJ.getListaAtributos();
				request.getSession().setAttribute("listaAtributos", listaAtributos);
			}
			request.getSession().setAttribute("visitPage", "crearAtribPJ");
			response.sendRedirect(request.getContextPath() + "/jsp/pj/crearAtribPJ.jsp");

		} else if (visitPage.equals("listaPJs")) {
			request.getSession().setAttribute("visitPage", "listaPJs");
			response.sendRedirect(request.getContextPath() + "/jsp/pj/listaPJs.jsp");

		} else if (visitPage.equals("vistaPJ")) {
			Personaje pjParaVer = (Personaje) request.getSession().getAttribute("pjCreado");
			if (pjParaVer == null) {
				String idPJ = request.getParameter("idPJ");
				int idPersonaje = Integer.parseInt(idPJ);
				pjParaVer = ServicePJ.retrievePersonajeByID(idPersonaje);
			}
			request.getSession().setAttribute("pjCreado", pjParaVer);
			request.getSession().setAttribute("yaGuardado", "yes");
			request.getSession().setAttribute("visitPage", "vistaPJ");
			response.sendRedirect(request.getContextPath() + "/jsp/pj/vistaPJ.jsp");

		} else if (visitPage.equals("borrarPJ")) {
			Personaje pjParaBorrar = (Personaje) request.getSession().getAttribute("pjParaBorrar");
			if (pjParaBorrar == null) {
				String idPJ = request.getParameter("idPJ");
				int idPersonaje = Integer.parseInt(idPJ);
				pjParaBorrar = ServicePJ.retrievePersonajeByID(idPersonaje);
			}
			request.getSession().setAttribute("pjParaBorrar", pjParaBorrar);
			request.getSession().setAttribute("visitPage", "borrarPJ");
			response.sendRedirect(request.getContextPath() + "/jsp/pj/borrarPJ.jsp");

		} else if (visitPage.equals("verRanking")) {

			ArrayList<Personaje> listaPersonajes = ServicePJ.getListaPersonajesRanking();
			request.getSession().setAttribute("listaPersonajes", listaPersonajes);
			request.getSession().setAttribute("visitPage", "verRanking");
			response.sendRedirect(request.getContextPath() + "/jsp/pj/verRanking.jsp");

		} else {

			response.sendRedirect(request.getContextPath() + "/jsp/game/" + visitPage + ".jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idioma = (String) request.getSession().getAttribute("idioma");
		ResourceBundle rb = ServiceUserAuxiliar.getResourceBundle(idioma);

		request.getSession().setAttribute("mensajeInfo", "");
		request.getSession().setAttribute("mensajeDanger", "");
		String mensajeDanger;

		String submitPageBtn = (String) request.getParameter("submitPageBtn");

//Submit desde página de login
		if (submitPageBtn.equals(rb.getString("sbmtLogin"))) {

			String username = request.getParameter("username").trim();
			String password = request.getParameter("pass").trim();

			mensajeDanger = rb.getString(ServiceUserMain.login(username, password));
			request.getSession().setAttribute("mensajeDanger", mensajeDanger);

			if (mensajeDanger.equals("")) {
				// Si todo va bien
				Usuario user = ServiceUserAuxiliar.buscarUserByNick(username);
				request.getSession().setAttribute("userLogged", user);
				request.getSession().setAttribute("idioma", user.getIdioma());

				if (user.getRol().equals("Admin") && user.getId() == 1) {
					
					ArrayList<Usuario> listaUsuarios = ServiceUserAuxiliar.getListaTodosUsuariosSinId1();
					request.getSession().setAttribute("listaUsuarios", listaUsuarios);
					
					
				} else if (user.getRol().equals("Admin") && user.getId() != 1) {	
					ArrayList<Usuario> listaUsuarios = ServiceUserAuxiliar.getListaUsuarios();
					request.getSession().setAttribute("listaUsuarios", listaUsuarios);
				}

				Personaje personaje = (Personaje) request.getSession().getAttribute("pjCreado");
				if (personaje != null) {
					response.sendRedirect(request.getContextPath() + "/jsp/pj/vistaPJ.jsp");

				} else {
					request.getSession().setAttribute("visitPage", "index");
					response.sendRedirect(request.getContextPath() + "/jsp/index/index.jsp");
				}

			} else if (mensajeDanger.equals(rb.getString("usuarioNoExiste"))) {
				request.getSession().setAttribute("visitPage", "alta");
				response.sendRedirect(request.getContextPath() + "/jsp/actions/alta.jsp");

			} else {
				response.sendRedirect(request.getContextPath() + "/jsp/actions/login.jsp");
			}

//Submit desde página de alta
		} else if (submitPageBtn.equals(rb.getString("sbmtAlta"))) {

			String pass2 = request.getParameter("pass2").trim();

			Usuario user = new Usuario(request.getParameter("username").trim(), request.getParameter("pass").trim(),
					request.getParameter("nombre").trim(), request.getParameter("apellidos").trim(),
					request.getParameter("email").trim(), "Normal");

			if (idioma != null) {
				user.setIdioma(idioma);
			}
			mensajeDanger = rb.getString(ServiceUserMain.alta(user, pass2));
			request.getSession().setAttribute("mensajeDanger", mensajeDanger);

			if (mensajeDanger.equals("")) {
				// Si todo va bien

				request.getSession().setAttribute("mensajeInfo", rb.getString("altaCorrecta"));
				request.getSession().setAttribute("visitPage", "login");
				response.sendRedirect(request.getContextPath() + "/jsp/actions/login.jsp");

			} else {
				response.sendRedirect(request.getContextPath() + "/jsp/actions/alta.jsp");
			}

//Submit para editar perfil
		} else if (submitPageBtn.equals(rb.getString("sbmtEditarPerfil"))) {

			Usuario userLogged = (Usuario) request.getSession().getAttribute("userLogged");
			Usuario user = ServiceUserAuxiliar.buscarUserByNick(userLogged.getUsername());

			Usuario userChanged = new Usuario(request.getParameter("username").trim(), user.getPassword(),
					request.getParameter("nombre").trim(), request.getParameter("apellidos").trim(),
					request.getParameter("email").trim(), user.getRol(), user.getIdioma());
			if (request.getParameter("rol") != null) {
				String rol = request.getParameter("rol");
				userChanged.setRol(rol);
			}
			mensajeDanger = rb.getString(ServiceUserMain.editarUsuario(user, userChanged));
			request.getSession().setAttribute("mensajeDanger", mensajeDanger);

			if (mensajeDanger.equals("")) {
				// Si todo va bien

				request.getSession().setAttribute("userLogged", userChanged);
				request.getSession().setAttribute("mensajeInfo", rb.getString("edicionCorrecta"));
				request.getSession().setAttribute("visitPage", "perfil");
				response.sendRedirect(request.getContextPath() + "/jsp/actions/perfil.jsp");
			} else {
				response.sendRedirect(request.getContextPath() + "/jsp/actions/editarPerfil.jsp");
			}

//Submit para cambiar la contraseña
		} else if (submitPageBtn.equals(rb.getString("sbmtCambiarPass"))) {

			Usuario userLogged = (Usuario) request.getSession().getAttribute("userLogged");

			String actualPass = request.getParameter("actualPass").trim();
			String pass = request.getParameter("pass").trim();
			String pass2 = request.getParameter("pass2").trim();

			mensajeDanger = rb.getString(ServiceUserMain.cambiarPass(userLogged, actualPass, pass, pass2));
			request.getSession().setAttribute("mensajeDanger", mensajeDanger);

			if (mensajeDanger.equals("")) {
				// Si todo va bien

				request.getSession().setAttribute("userLogged", userLogged);
				request.getSession().setAttribute("mensajeInfo", rb.getString("passCambiado"));
				request.getSession().setAttribute("visitPage", "perfil");
				response.sendRedirect(request.getContextPath() + "/jsp/actions/perfil.jsp");

			} else {
				response.sendRedirect(request.getContextPath() + "/jsp/actions/cambiarPass.jsp");
			}

		}

	}
}
