package controllers.controllerPJ;

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
 * Servlet implementation class ControllerPJ
 */
@WebServlet("/ControllerPJ")
public class ControllerPJ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerPJ() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

					userChanged.setIdioma(idioma);
					ServiceUserMain.editarUsuario(userLogged, userChanged);
					userChanged = ServiceUserAuxiliar.buscarUserByNick(userLogged.getUsername());
					request.getSession().setAttribute("userLogged", userChanged);

				}
			}
		}

		String searching = (String) request.getSession().getAttribute("searching");
		if (userLogged != null && (searching == null || !searching.equals("searching"))) {
			ArrayList<Personaje> listaPersonajesUsuario = ServicePJ.retrievePJsByUser(userLogged);
			request.getSession().setAttribute("listaPersonajes", listaPersonajesUsuario);
		}

		if (idioma != null) {
			request.getSession().setAttribute("idioma", idioma);
		}

		request.getSession().setAttribute("visitPage", visitPage);
		request.getSession().setAttribute("generoPJ", "");

		if (visitPage.equals("crearPJ")) {
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
			ArrayList<String> parametrosPJ = new ArrayList<String>();
			for (int i = 1; i < 5; i++) {
				parametrosPJ.add("");
			}
			request.getSession().setAttribute("textoRaza", "");
			request.getSession().setAttribute("textoClase", "");
			request.getSession().setAttribute("parametrosPJ", parametrosPJ);
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
			String idPJ = request.getParameter("idPJ");
			if (idPJ != null) {
				int idPersonaje = Integer.parseInt(idPJ);
				pjParaVer = ServicePJ.retrievePersonajeByID(idPersonaje);
			}
			request.getSession().setAttribute("pjCreado", pjParaVer);
			request.getSession().setAttribute("yaGuardado", "yes");
			request.getSession().setAttribute("visitPage", "vistaPJ");
			response.sendRedirect(request.getContextPath() + "/jsp/pj/vistaPJ.jsp");

		} else if (visitPage.equals("borrarPJ")) {

			String idPJ = request.getParameter("idPJ");
			int idPersonaje = Integer.parseInt(idPJ);
			Personaje pjParaBorrar = ServicePJ.retrievePersonajeByID(idPersonaje);

			request.getSession().setAttribute("pjParaBorrar", pjParaBorrar);
			request.getSession().setAttribute("visitPage", "borrarPJ");
			response.sendRedirect(request.getContextPath() + "/jsp/pj/borrarPJ.jsp");

		} else if (visitPage.equals("quitarFiltro")) {
			request.getSession().setAttribute("searching", "");
			ArrayList<Personaje> listaPersonajes = ServicePJ.retrievePJsByUser(userLogged);
			request.getSession().setAttribute("listaPersonajes", listaPersonajes);
			request.getSession().setAttribute("visitPage", "listaPJs");
			response.sendRedirect(request.getContextPath() + "/jsp/pj/listaPJs.jsp");
		
		} else if (visitPage.equals("verRanking")) {
			
			ArrayList<Personaje> listaPersonajes = ServicePJ.getListaPersonajesRanking();
			request.getSession().setAttribute("listaPersonajes", listaPersonajes);
			request.getSession().setAttribute("visitPage", "verRanking");
			response.sendRedirect(request.getContextPath() + "/jsp/pj/verRanking.jsp");
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

		Usuario userLogged = (Usuario) request.getSession().getAttribute("userLogged");
		String submitPageBtn = (String) request.getParameter("submitPageBtn");

		String searching = (String) request.getSession().getAttribute("searching");
		if (userLogged != null && (searching == null || !searching.equals("searching"))) {
			ArrayList<Personaje> listaPersonajesUsuario = ServicePJ.retrievePJsByUser(userLogged);
			request.getSession().setAttribute("listaPersonajes", listaPersonajesUsuario);
		}

//Submit para crear un personaje parcial, es decir a falta de elegir los atributos del PJ
		if (submitPageBtn.equals(rb.getString("sbmtElegirAtrbPJ"))) {

			String nombrePJ = request.getParameter("nombrePJ").trim();
			String generoPJ = request.getParameter("generoPJ");
			String razaPJ = request.getParameter("razaPJ");
			String clasePJ = request.getParameter("clasePJ");

			if (nombrePJ == null || nombrePJ.equals("") || generoPJ == null || razaPJ == null || clasePJ == null) {
				mensajeDanger = rb.getString("rellenarCampos");
				request.getSession().setAttribute("mensajeDanger", mensajeDanger);
				response.sendRedirect(request.getContextPath() + "/jsp/pj/crearPJ.jsp");

			} else {
				ArrayList<String> parametrosPJ = new ArrayList<String>();
				parametrosPJ.add(nombrePJ);
				parametrosPJ.add(generoPJ);
				parametrosPJ.add(razaPJ);
				parametrosPJ.add(clasePJ);

				int[] atributosBasePJ = ServicePJ.getAtributosBase(razaPJ, clasePJ);

				Personaje personaje = ServicePJ.createNewPartialPJ(parametrosPJ, atributosBasePJ, userLogged);
				request.getSession().setAttribute("pjCreadoParcial", personaje);

				request.getSession().setAttribute("yaGuardado", "no");
				request.getSession().setAttribute("visitPage", "crearAtribPJ");
				response.sendRedirect(request.getContextPath() + "/jsp/pj/crearAtribPJ.jsp");

			}


//Submit para crear un PJ habiendo elegido los atributos
		} else if (submitPageBtn.equals(rb.getString("sbmtCrearPJ"))) {

			int puntosARepartir = Integer.parseInt(request.getParameter("puntosARepartir"));

			if (puntosARepartir != 0) {
				mensajeDanger = rb.getString("repartirPuntos");
				request.getSession().setAttribute("mensajeDanger", mensajeDanger);
				response.sendRedirect(request.getContextPath() + "/jsp/pj/crearAtribPJ.jsp");

			} else if (Integer.parseInt(request.getParameter("vigor")) == 0) {
					mensajeDanger = rb.getString("vigorNoCero");
					request.getSession().setAttribute("mensajeDanger", mensajeDanger);
					response.sendRedirect(request.getContextPath() + "/jsp/pj/crearAtribPJ.jsp");
					
			} else {
				Personaje personajeParcial = (Personaje) request.getSession().getAttribute("pjCreadoParcial");

				ArrayList<String> parametrosPJ = new ArrayList<String>();
				int[] atributosPJ = new int[5];

				parametrosPJ.add(personajeParcial.getNombre());
				parametrosPJ.add(personajeParcial.getGenero().getNombreGenero());
				parametrosPJ.add(personajeParcial.getRaza().getNombreRaza());
				parametrosPJ.add(personajeParcial.getClase().getNombreClase());

				atributosPJ[0] = Integer.parseInt(request.getParameter("fuerza"));
				atributosPJ[1] = Integer.parseInt(request.getParameter("agilidad"));
				atributosPJ[2] = Integer.parseInt(request.getParameter("vigor"));
				atributosPJ[3] = Integer.parseInt(request.getParameter("magia"));
				atributosPJ[4] = Integer.parseInt(request.getParameter("labia"));
				

				Personaje personaje = ServicePJ.createNewPJ(parametrosPJ, atributosPJ, userLogged);
				request.getSession().setAttribute("pjCreado", personaje);

				request.getSession().setAttribute("mensajeInfo", rb.getString("personajeCreado"));
				request.getSession().setAttribute("yaGuardado", "no");
				request.getSession().setAttribute("visitPage", "vistaPJ");
				response.sendRedirect(request.getContextPath() + "/jsp/pj/vistaPJ.jsp");
			}

//Submit para guardar un personaje
		} else if (submitPageBtn.equals(rb.getString("sbmtGuardarPJ"))) {

			if (userLogged == null) {
				request.getSession().setAttribute("mensajeInfo", rb.getString("debesLogearte"));
				response.sendRedirect(request.getContextPath() + "/jsp/actions/login.jsp");

			} else {

				Personaje personaje = (Personaje) request.getSession().getAttribute("pjCreado");
				ServicePJ.guardarPersonaje(personaje, userLogged);
				request.getSession().setAttribute("mensajeInfo", rb.getString("personajeGuardado"));

				ArrayList<Personaje> listaPersonajesUser = ServicePJ.retrievePJsByUser(userLogged);
				request.getSession().setAttribute("listaPersonajes", listaPersonajesUser);

				request.getSession().setAttribute("visitPage", "listaPJs");
				response.sendRedirect(request.getContextPath() + "/jsp/pj/listaPJs.jsp");

			}

			
//Submit para ver raza o clase
		} else if (submitPageBtn.equals(rb.getString("sbmtVerRaza"))
				|| submitPageBtn.equals(rb.getString("sbmtVerClase"))) {
			
			String nombrePJ = request.getParameter("nombrePJ").trim();
			String generoPJ = request.getParameter("generoPJ");
			String razaPJ = request.getParameter("razaPJ");
			String clasePJ = request.getParameter("clasePJ");
			
			if (submitPageBtn.equals(rb.getString("sbmtVerRaza")) && razaPJ == null) {
				mensajeDanger = rb.getString("eligeRaza");
			} else if (submitPageBtn.equals(rb.getString("sbmtVerClase")) && clasePJ == null) {
				mensajeDanger = rb.getString("eligeClase");
			} else {
				mensajeDanger = rb.getString("");
			}
			
			ArrayList<String> parametrosPJ = new ArrayList<String>();
			if (nombrePJ == null) {
				parametrosPJ.add("");
			} else {
				parametrosPJ.add(nombrePJ);
			}
			if (generoPJ == null) {
				parametrosPJ.add("");
			} else {
				parametrosPJ.add(generoPJ);
			}
			if (razaPJ == null) {
				parametrosPJ.add("");
				request.getSession().setAttribute("textoRaza", "");
			} else {
				parametrosPJ.add(razaPJ);
				if (submitPageBtn.equals(rb.getString("sbmtVerRaza"))) {
					String textoRaza = "texto" + razaPJ;
					request.getSession().setAttribute("textoRaza", textoRaza);
				}
			}
			if (clasePJ == null) {
				parametrosPJ.add("");
				request.getSession().setAttribute("textoClase", "");
			} else {
				parametrosPJ.add(clasePJ);
				if (submitPageBtn.equals(rb.getString("sbmtVerClase"))) {
					String textoClase = "texto" + clasePJ;
					request.getSession().setAttribute("textoClase", textoClase);
				}
			}
			
			request.getSession().setAttribute("parametrosPJ", parametrosPJ);
			request.getSession().setAttribute("mensajeDanger", mensajeDanger);
			response.sendRedirect(request.getContextPath() + "/jsp/pj/crearPJ.jsp");

			
//Submit para borrar un personaje
		} else if (submitPageBtn.equals(rb.getString("sbmtBorrarPJ"))) {

			Personaje pjParaBorrar = (Personaje) request.getSession().getAttribute("pjParaBorrar");

			ServicePJ.borrarPersonaje(pjParaBorrar, userLogged);
			request.getSession().setAttribute("mensajeInfo", rb.getString("eliminacionCorrectaPJ"));

			ArrayList<Personaje> listaPersonajesUser = ServicePJ.retrievePJsByUser(userLogged);
			request.getSession().setAttribute("listaPersonajes", listaPersonajesUser);

			request.getSession().setAttribute("visitPage", "listaPJs");
			response.sendRedirect(request.getContextPath() + "/jsp/pj/listaPJs.jsp");

//Submit para cancelar acción
		} else if (submitPageBtn.equals(rb.getString("sbmtCancelar"))) {

			request.getSession().setAttribute("pjParaBorrar", null);
			request.getSession().setAttribute("visitPage", "listaPJs");
			response.sendRedirect(request.getContextPath() + "/jsp/pj/listaPJs.jsp");

//Submit para buscar personajes
		} else if (submitPageBtn.equals(rb.getString("sbmtBuscar"))) {

			String cadenaBuscada = request.getParameter("cadenaBuscada");

			mensajeDanger = rb.getString(ServicePJ.comprobarBuscarPJ(cadenaBuscada, userLogged));
			request.getSession().setAttribute("mensajeDanger", mensajeDanger);

			if (mensajeDanger.equals(rb.getString("buscarVacio"))) {
				ArrayList<Personaje> listaPersonajesUser = ServicePJ.retrievePJsByUser(userLogged);
				request.getSession().setAttribute("listaPersonajes", listaPersonajesUser);
				request.getSession().setAttribute("visitPage", "listaPJs");
				response.sendRedirect(request.getContextPath() + "/jsp/pj/listaPJs.jsp");

			} else {
				ArrayList<Personaje> listaPersonajes = ServicePJ.buscarPJContieneConIdioma(cadenaBuscada, userLogged);
				request.getSession().setAttribute("listaPersonajes", listaPersonajes);
				request.getSession().setAttribute("searching", "searching");
				request.getSession().setAttribute("visitPage", "listaPJs");
				response.sendRedirect(request.getContextPath() + "/jsp/pj/listaPJs.jsp");
			}

//Submit para empezar a jugar
		} else if (submitPageBtn.equals(rb.getString("sbmtJugar"))) {
			
			Personaje pjJugando = (Personaje) request.getSession().getAttribute("pjCreado");
			request.getSession().setAttribute("pjJugando", pjJugando);
			String lineas = "mision1Pagina1";
			request.getSession().setAttribute("lineas", lineas);
			request.getSession().setAttribute("visitPage", "inicioAventura");
			response.sendRedirect(request.getContextPath() + "/jsp/game/inicioAventura.jsp");

		}

	}

}
