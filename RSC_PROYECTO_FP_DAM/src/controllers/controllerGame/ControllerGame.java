package controllers.controllerGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.modelPJ.Personaje;
import models.modelUsuario.Usuario;
import services.servicePJ.ServicePJ;
import services.serviceUsuario.ServiceUserAuxiliar;
import services.serviceUsuario.ServiceUserMain;

/**
 * Servlet implementation class ControllerGame
 */
@WebServlet("/ControllerGame")
public class ControllerGame extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerGame() {
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

		if (idioma != null) {
			request.getSession().setAttribute("idioma", idioma);
		}

		request.getSession().setAttribute("visitPage", visitPage);

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

		Personaje pjJugando = (Personaje) request.getSession().getAttribute("pjJugando");
		Usuario userLogged = (Usuario) request.getSession().getAttribute("userLogged");
		String submitPageBtn = (String) request.getParameter("submitPageBtn");

//Submit para continuar jugando
		if (submitPageBtn.equals(rb.getString("sbmtContinuar"))) {

			request.getSession().setAttribute("turno", "");
			request.getSession().setAttribute("primerTurno", "");
			request.getSession().setAttribute("combate", "");
			request.getSession().setAttribute("saludPJ", "");
			request.getSession().setAttribute("saludEnemigo", "");
			request.getSession().setAttribute("titularFin", "");
			request.getSession().setAttribute("motivoFin", "");
			request.getSession().setAttribute("recompensaFin", "");
			request.getSession().setAttribute("enemigo", "");
			request.getSession().setAttribute("nombreAtacante", "");
			request.getSession().setAttribute("nombreDefensor", "");
			request.getSession().setAttribute("filasGrid", "");
			request.getSession().setAttribute("numBotones", "");
			request.getSession().setAttribute("nombresBotones", "");

			String lineas = (String) request.getSession().getAttribute("lineas");
			lineas = "mision1Pagina2";

			request.getSession().setAttribute("lineas", lineas);
			request.getSession().setAttribute("visitPage", "elegirAccion");
			response.sendRedirect(request.getContextPath() + "/jsp/game/elegirAccion.jsp");

//Submit para elegir acción
		} else if (submitPageBtn.equals(rb.getString("sbmtLabia")) || submitPageBtn.equals(rb.getString("sbmtAgilidad"))
				|| submitPageBtn.equals(rb.getString("sbmtMagia")) || submitPageBtn.equals(rb.getString("sbmtHuir"))) {

			int atributo = 0;
			int suma = 0;
			int numeroMenor = 1;
			int numeroMayor = 20;
			int tirada = (int) Math.round(Math.random() * (numeroMayor - numeroMenor) + numeroMenor);
			int dificultad = 19;
			String lineas = "mision1Pagina3";

			if (submitPageBtn.equals(rb.getString("sbmtLabia"))) {
				atributo = pjJugando.getAtributos().getLabia();
				lineas += "Opcion1";
			} else if (submitPageBtn.equals(rb.getString("sbmtAgilidad"))) {
				atributo = pjJugando.getAtributos().getAgilidad();
				lineas += "Opcion2";
			} else if (submitPageBtn.equals(rb.getString("sbmtMagia"))) {
				atributo = pjJugando.getAtributos().getMagia();
				lineas += "Opcion3";
			} else if (submitPageBtn.equals(rb.getString("sbmtHuir"))) {
				atributo = pjJugando.getAtributos().getAgilidad();
				dificultad = 14;
				lineas += "Opcion4";
			}

			if (atributo != 0) {
				suma = tirada + atributo;
				if (suma >= dificultad) {
					lineas += "Exito";
				} else if (suma < dificultad) {
					lineas += "Fallo";
				}
			} else if (atributo == 0) {
				lineas += "Fallo";
			}

			if (lineas.equals("mision1Pagina3Opcion4Exito")) {
				request.getSession().setAttribute("titularFin", "hasHuido");
				request.getSession().setAttribute("motivoFin", "finAventuraHuida");
				request.getSession().setAttribute("recompensaFin", "recompensaHuida");
			}

			request.getSession().setAttribute("lineas", lineas);
			request.getSession().setAttribute("visitPage", "resultadoAccion");
			response.sendRedirect(request.getContextPath() + "/jsp/game/resultadoAccion.jsp");

//Submits para el combate
		} else if (submitPageBtn.equals(rb.getString("sbmtCombate"))
				|| submitPageBtn.equals(rb.getString("sbmtDistraer"))
				|| submitPageBtn.equals(rb.getString("sbmtContinuarAtaque"))
				|| submitPageBtn.equals(rb.getString("sbmtAtaque"))
				|| submitPageBtn.equals(rb.getString("sbmtAtaqueMagia"))
				|| submitPageBtn.equals(rb.getString("sbmtIntentarHuir"))
				|| submitPageBtn.equals(rb.getString("sbmtTurnoEnemigo"))) {

			String lineas = (String) request.getSession().getAttribute("lineas");
			Personaje enemigo = new Personaje();
			String combate = (String) request.getSession().getAttribute("combate");
			String turno = (String) request.getSession().getAttribute("turno");
			String accion = "";
			String resultado = "";
			String resultadoTexto = "";
			int saludPJ = 0;
			int saludEnemigo = 0;
			int danio = 0;
			Personaje atacante = new Personaje();
			Personaje defensor = new Personaje();
			String nombreAtacante = "";
			String nombreDefensor = "";
			ArrayList<String> filasGrid = new ArrayList<String>();
			String primerTurno = (String) request.getSession().getAttribute("primerTurno");
			String enemigoAbatido = (String) request.getSession().getAttribute("enemigoAbatido");

			if (turno == null) {
				turno = "";
			}
			if (combate == null) {
				combate = "";
			}
			if (primerTurno == null) {
				primerTurno = "";
			}
			if (enemigoAbatido == null) {
				enemigoAbatido = "0";
			}
			if (lineas.contains("Exito")) {
				primerTurno = "primerTurnoPersonaje";
			} else if (lineas.contains("Fallo")) {
				primerTurno = "primerTurnoEnemigo";
			}

			if (lineas.contains("Exito") || combate.equals("combateJefeOrco")) {
				enemigo = ServicePJ.retrievePersonajeByID(3);
				combate = "combateJefeOrco";

			} else if (lineas.contains("Fallo") || combate.equals("combateOrco1")) {
				enemigo = ServicePJ.retrievePersonajeByID(1);
				combate = "combateOrco1";
			} else if (combate.equals("combateOrco2")) {
				enemigo = ServicePJ.retrievePersonajeByID(2);
			}

			if (!lineas.equals("")) {
				saludPJ = pjJugando.getSalud();
				saludEnemigo = enemigo.getSalud();
			} else {
				saludPJ = (int) request.getSession().getAttribute("saludPJ");
				saludEnemigo = (int) request.getSession().getAttribute("saludEnemigo");
			}

			if (saludEnemigo == 0) {
				saludEnemigo = enemigo.getSalud();
			}

			if (lineas.contains("Exito")
					|| (turno.equals("enemigo") && !(primerTurno.equals("primerTurnoEnemigo"))
							&& enemigoAbatido.equals("0"))
					|| submitPageBtn.equals(rb.getString("sbmtContinuarAtaque"))
					|| primerTurno.equals("primerTurnoPersonaje")) {
				turno = "personaje";
				atacante = pjJugando;
				defensor = enemigo;
				nombreAtacante = pjJugando.getNombre();
				nombreDefensor = enemigo.getNombre();
			} else if (lineas.contains("Fallo") || primerTurno.equals("primerTurnoEnemigo")
					|| !enemigoAbatido.equals("0")
					|| (turno.equals("personaje") && !(submitPageBtn.equals(rb.getString("sbmtContinuarAtaque")))
							&& !(primerTurno.equals("primerTurnoPersonaje")))) {
				turno = "enemigo";
				atacante = enemigo;
				defensor = pjJugando;
				nombreAtacante = enemigo.getNombre();
				nombreDefensor = pjJugando.getNombre();
			}

			// filasGrid.get(0);//Fila 1
			filasGrid.add(combate);

			if (!(combate.equals("combateVictoria")) && !(combate.equals("combateDerrota"))) {

				// filasGrid.get(1 y 2);//Fila 2
				filasGrid.add("turno");
				filasGrid.add("");

				// Aquí hay que hacer la tirada de dados
				int atributoAtacante = 0;
				int atributoDefensor = 0;
				int suma = 0;
				int numeroMenor = 1;
				int numeroMayor = 20;
				int tiradaAtacante = (int) Math.round(Math.random() * (numeroMayor - numeroMenor) + numeroMenor);
				int tiradaDefensor = (int) Math.round(Math.random() * (numeroMayor - numeroMenor) + numeroMenor);

				if (submitPageBtn.equals(rb.getString("sbmtDistraer"))) {
					atributoAtacante = atacante.getAtributos().getLabia();
					atributoDefensor = defensor.getAtributos().getLabia();
					accion = "accDistraer";
					resultadoTexto = "Distraccion";
					danio = 0;

				} else if (submitPageBtn.equals(rb.getString("sbmtAtaque"))) {
					atributoAtacante = atacante.getAtributos().getFuerza();
					atributoDefensor = defensor.getAtributos().getAgilidad();
					accion = "accAtaque";
					resultadoTexto = "Impacto";
					danio = atacante.getAtributos().getFuerza();

				} else if (submitPageBtn.equals(rb.getString("sbmtAtaqueMagia"))) {
					atributoAtacante = atacante.getAtributos().getMagia();
					atributoDefensor = defensor.getAtributos().getAgilidad();
					accion = "accAtaqueMagia";
					resultadoTexto = "Impacto";
					danio = atacante.getAtributos().getMagia();

				} else if (submitPageBtn.equals(rb.getString("sbmtIntentarHuir"))) {
					atributoAtacante = atacante.getAtributos().getAgilidad();
					atributoDefensor = defensor.getAtributos().getAgilidad();
					accion = "accIntentarHuir";
					resultadoTexto = "Huir";
					danio = 0;

				} else if (submitPageBtn.equals(rb.getString("sbmtContinuarAtaque"))) {
					atributoAtacante = atacante.getAtributos().getFuerza() * 2;
					atributoDefensor = defensor.getAtributos().getAgilidad();
					accion = "accAtaque";
					resultadoTexto = "Impacto";
					danio = atacante.getAtributos().getFuerza() * 2;

				} else if (submitPageBtn.equals(rb.getString("sbmtTurnoEnemigo")) && !enemigoAbatido.equals("1")) {
					atributoAtacante = atacante.getAtributos().getFuerza();
					atributoDefensor = defensor.getAtributos().getAgilidad();
					accion = "accAtaque";
					resultadoTexto = "Impacto";
					danio = atacante.getAtributos().getFuerza();
				}

				int dificultad = tiradaDefensor + atributoDefensor;

				if (atributoAtacante != 0) {
					suma = tiradaAtacante + atributoAtacante;
					if (suma >= dificultad) {
						resultado = "exito";
						resultadoTexto = "exito" + resultadoTexto;
					} else if (suma < dificultad) {
						resultado = "fallo";
						resultadoTexto = "fallo" + resultadoTexto;
						danio = 0;
					}
				} else if (atributoAtacante == 0) {
					resultado = "fallo";
					resultadoTexto = "fallo" + resultadoTexto;
					danio = 0;
				}

				// filasGrid.get(3 y 4);//Fila 3
				if (accion.equals("")) {
					filasGrid.add("");
					filasGrid.add("");
				} else {
					filasGrid.add("");
					filasGrid.add(accion);
				}

				// filasGrid.get(5, 6 y 7);//Filas 4 y 5
				if (lineas.equals("") && !enemigoAbatido.equals("1")) {

					filasGrid.add(resultado);

					filasGrid.add("");
					filasGrid.add(resultadoTexto);
				} else {
					filasGrid.add("");

					filasGrid.add("");
					filasGrid.add("");
					danio = 0;
				}

				// filasGrid.get(8, 9 ,10 y 11);//Fila 6
				if (danio > 0) {
					filasGrid.add("");
					filasGrid.add("inflinge");
					filasGrid.add(" " + danio + " ");
					filasGrid.add("puntosDanio");
				} else {
					filasGrid.add("");
					filasGrid.add("");
					filasGrid.add("");
					filasGrid.add("");
				}
				if (enemigoAbatido.equals("2")) {
					enemigoAbatido = "0";
				} else if (enemigoAbatido.equals("1")) {
					enemigoAbatido = "2";
				}
				// filasGrid.get(12 y 13);//Fila 7
				if (pjJugando.getId() == atacante.getId()) {
					saludEnemigo -= danio;

					if (saludEnemigo <= 0) {
						saludEnemigo = 0;
						filasGrid.add("");
						filasGrid.add("esVencido");

						if (combate.equals("combateOrco1")) {
							combate = "combateOrco2";
							enemigoAbatido = "1";
						} else if (combate.equals("combateOrco2")) {
							combate = "combateJefeOrco";
							enemigoAbatido = "1";
						} else if (combate.equals("combateJefeOrco")) {
							combate = "combateVictoria";
						}
					} else {
						filasGrid.add("");
						filasGrid.add("");
					}
				} else if (enemigo.getId() == atacante.getId()) {
					saludPJ -= danio;

					if (saludPJ <= 0) {
						saludPJ = 0;
						filasGrid.add("");
						filasGrid.add("esVencido");
						combate = "combateDerrota";
					} else {
						filasGrid.add("");
						filasGrid.add("");
					}
				}

			} else {
				// filasGrid.get(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)
				for (int i = 1; i <= 13; i++) {
					filasGrid.add("");
				}
			}

			// botones
			int numBotones = 0;
			ArrayList<String> nombresBotones = new ArrayList<String>();
			String motivoFin = "";
			String recompensaFin = "";
			String titularFin = "";

			if (turno.equals("personaje") && lineas.equals("") && !resultadoTexto.equals("exitoDistraccion")
					&& !resultadoTexto.equals("exitoHuir") && !combate.equals("combateVictoria")
					|| !enemigoAbatido.equals("0") || (turno.equals("enemigo") && (!lineas.equals("")))) {
				numBotones = 1;
				nombresBotones.add("sbmtTurnoEnemigo");

			} else if (resultadoTexto.equals("exitoDistraccion")) {
				numBotones = 1;
				nombresBotones.add("sbmtContinuarAtaque");

			} else if ((turno.equals("personaje") && !lineas.equals("")) || (turno.equals("enemigo")
					&& (lineas.equals("")) && !combate.equals("combateDerrota") && (enemigoAbatido.equals("0")))) {
				numBotones = 4;
				nombresBotones.add("sbmtDistraer");
				nombresBotones.add("sbmtAtaque");
				nombresBotones.add("sbmtAtaqueMagia");
				nombresBotones.add("sbmtIntentarHuir");

			} else if (resultadoTexto.equals("exitoHuir") || combate.equals("combateVictoria")
					|| combate.equals("combateDerrota")) {

				if (resultadoTexto.equals("exitoHuir")) {
					motivoFin = "finAventuraHuida";
					recompensaFin = "recompensaHuida";
					titularFin = "hasHuido";

				} else if (combate.equals("combateVictoria")) {
					motivoFin = "finAventuraVictoria";
					recompensaFin = "recompensaVictoria";
					titularFin = "victoria";
					pjJugando.aumentarVictorias();
					pjJugando.calcularPuntos();

				} else if (combate.equals("combateDerrota")) {

					if (pjJugando.getAtributos().getVigor() > 1) {
						motivoFin = "finAventuraDerrota";
						recompensaFin = "recompensaDerrota";
						titularFin = "derrota";
						pjJugando.aumentarDerrotas();
						pjJugando.calcularPuntos();

					} else {
						motivoFin = "vigorCero";
						recompensaFin = "";
						titularFin = "muerte";
					}
				}

				ServicePJ.editarPersonajeBD(pjJugando);
				numBotones = 1;
				nombresBotones.add("sbmtFinAventura");
			}

			if (lineas.equals("")) {
				primerTurno = "";
			}

			lineas = "";

			// Sesiones sólo para el controlador
			request.getSession().setAttribute("lineas", lineas);
			request.getSession().setAttribute("turno", turno);
			request.getSession().setAttribute("primerTurno", primerTurno);
			request.getSession().setAttribute("enemigoAbatido", enemigoAbatido);

			// Sesiones para el controlador y la vista
			request.getSession().setAttribute("combate", combate);
			request.getSession().setAttribute("saludPJ", saludPJ);
			request.getSession().setAttribute("saludEnemigo", saludEnemigo);
			request.getSession().setAttribute("titularFin", titularFin);
			request.getSession().setAttribute("motivoFin", motivoFin);
			request.getSession().setAttribute("recompensaFin", recompensaFin);

			// Sesiones sólo para la vista
			request.getSession().setAttribute("enemigo", enemigo);
			request.getSession().setAttribute("nombreAtacante", nombreAtacante);
			request.getSession().setAttribute("nombreDefensor", nombreDefensor);
			request.getSession().setAttribute("filasGrid", filasGrid);
			request.getSession().setAttribute("numBotones", numBotones);
			request.getSession().setAttribute("nombresBotones", nombresBotones);

			request.getSession().setAttribute("pjJugando", pjJugando);
			request.getSession().setAttribute("visitPage", "combate");
			response.sendRedirect(request.getContextPath() + "/jsp/game/combate.jsp");

// Submit terminar la aventura
		} else if (submitPageBtn.equals(rb.getString("sbmtFinAventura"))) {

			String motivoFin = (String) request.getSession().getAttribute("motivoFin");

			if (motivoFin.equals("finAventuraDerrota") || motivoFin.equals("vigorCero")) {
				pjJugando = ServicePJ.disminuirAtributos(pjJugando);
			}

			request.getSession().setAttribute("pjJugando", pjJugando);
			request.getSession().setAttribute("visitPage", "finAventura");
			response.sendRedirect(request.getContextPath() + "/jsp/game/finAventura.jsp");

//Submit guardar el progreso de la aventura
		} else if (submitPageBtn.equals(rb.getString("sbmtGuardarProgreso"))) {

			String motivoFin = (String) request.getSession().getAttribute("motivoFin");

			if (motivoFin.equals("finAventuraVictoria")) {

				int puntosARepartir = Integer.parseInt(request.getParameter("puntosARepartir"));

				if (puntosARepartir != 0) {
					mensajeDanger = rb.getString("repartirPuntos");
					request.getSession().setAttribute("mensajeDanger", mensajeDanger);
					response.sendRedirect(request.getContextPath() + "/jsp/game/finAventura.jsp");

				} else {

					pjJugando.getAtributos().setFuerza(Integer.parseInt(request.getParameter("fuerza")));
					pjJugando.getAtributos().setAgilidad(Integer.parseInt(request.getParameter("agilidad")));
					pjJugando.getAtributos().setVigor(Integer.parseInt(request.getParameter("vigor")));
					pjJugando.getAtributos().setMagia(Integer.parseInt(request.getParameter("magia")));
					pjJugando.getAtributos().setLabia(Integer.parseInt(request.getParameter("labia")));

					ServicePJ.editarAtributosPersonaje(pjJugando);
					ServicePJ.editarAtributosPersonajeEnBD(pjJugando);
					pjJugando.calcularSalud();

					pjJugando.calcularPuntos();
					ServicePJ.editarPersonajeBD(pjJugando);

					request.getSession().setAttribute("pjJugando", pjJugando);
					request.getSession().setAttribute("mensajeInfo", rb.getString("personajeGuardado"));
					ArrayList<Personaje> listaPersonajesUsuario = ServicePJ.retrievePJsByUser(userLogged);
					request.getSession().setAttribute("listaPersonajes", listaPersonajesUsuario);
					request.getSession().setAttribute("visitPage", "listaPJs");
					response.sendRedirect(request.getContextPath() + "/jsp/pj/listaPJs.jsp");
				}

			} else {

				if (motivoFin.equals("finAventuraDerrota") || motivoFin.equals("vigorCero")) {
					ServicePJ.editarAtributosPersonajeEnBD(pjJugando);
					pjJugando.calcularSalud();
					pjJugando.calcularPuntos();
					ServicePJ.editarPersonajeBD(pjJugando);
				}

				request.getSession().setAttribute("pjJugando", pjJugando);
				request.getSession().setAttribute("mensajeInfo", rb.getString("personajeGuardado"));
				ArrayList<Personaje> listaPersonajesUsuario = ServicePJ.retrievePJsByUser(userLogged);
				request.getSession().setAttribute("listaPersonajes", listaPersonajesUsuario);
				request.getSession().setAttribute("visitPage", "listaPJs");
				response.sendRedirect(request.getContextPath() + "/jsp/pj/listaPJs.jsp");
			}

		}

	}

}
