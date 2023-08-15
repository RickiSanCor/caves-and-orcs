package controllers.controllerUsuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.modelUsuario.Usuario;
import services.serviceUsuario.ServiceUserAuxiliar;
import services.serviceUsuario.ServiceUserMain;

/**
 * Servlet implementation class ControllerLoginAdmin
 */
@WebServlet("/ControllerLoginAdmin")
public class ControllerLoginAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerLoginAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().setAttribute("mensajeInfo", "");
		request.getSession().setAttribute("mensajeDanger", "");
		
		String visitPage = request.getParameter("visitPage");
		if (visitPage == null) {
			String idiomaCambiado = request.getParameter("idiomaCambiado");
			if (idiomaCambiado.equals("true")) {
				visitPage = (String) request.getSession().getAttribute("visitPage");
			}
		}
		request.getSession().setAttribute("visitPage", visitPage);
		
		if (visitPage.equals("editarUserConAdmin")){
			String NickUserModif = request.getParameter("idUserModif");
			Usuario userParaModif = ServiceUserAuxiliar.buscarUserByNick(NickUserModif);
			request.getSession().setAttribute("userParaModif", userParaModif);
			request.getSession().setAttribute("visitPage", "editarUserConAdmin");
			response.sendRedirect(request.getContextPath() + "/jsp/actions/editarUserConAdmin.jsp");
		
		} else if (visitPage.equals("borrarUser")){
			String NickUserModif = request.getParameter("idUserModif");
			Usuario userParaModif = ServiceUserAuxiliar.buscarUserByNick(NickUserModif);
			request.getSession().setAttribute("userParaModif", userParaModif);
			request.getSession().setAttribute("visitPage", "borrarUser");
			response.sendRedirect(request.getContextPath() + "/jsp/actions/borrarUser.jsp");
		
		} else if (visitPage.equals("quitarFiltro")){
			request.getSession().setAttribute("searching", "");
			ArrayList<Usuario> listaUsuarios = ServiceUserAuxiliar.getListaUsuarios();
			request.getSession().setAttribute("listaUsuarios", listaUsuarios);
			request.getSession().setAttribute("visitPage", "index");
			response.sendRedirect(request.getContextPath() + "/jsp/index/index.jsp");
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idioma = (String) request.getSession().getAttribute("idioma");
		ResourceBundle rb = ServiceUserAuxiliar.getResourceBundle(idioma);
		
		request.getSession().setAttribute("mensajeInfo", "");
		request.getSession().setAttribute("mensajeDanger", "");
		String mensajeDanger;

		String submitPageBtn = (String) request.getParameter("submitPageBtn");

		
//Submit para editar un usuario con el Admin
		if (submitPageBtn.equals(rb.getString("sbmtEditarUsuario"))){
			
			Usuario user = (Usuario) request.getSession().getAttribute("userParaModif");
			
			Usuario userChanged = new Usuario(
					request.getParameter("username").trim(),
					user.getPassword(),
					request.getParameter("nombre").trim(),
					request.getParameter("apellidos").trim(),
					request.getParameter("email").trim(),
					request.getParameter("rol").trim());
			
			mensajeDanger = rb.getString(ServiceUserMain.editarUsuario(user, userChanged));
			request.getSession().setAttribute("mensajeDanger", mensajeDanger);

			if (mensajeDanger.equals("")) {
				// Si todo va bien
				
				request.getSession().setAttribute("mensajeInfo", rb.getString("edicionCorrecta"));
				ArrayList<Usuario> listaUsuarios = ServiceUserAuxiliar.getListaUsuarios();
				request.getSession().setAttribute("listaUsuarios", listaUsuarios);
				request.getSession().setAttribute("visitPage", "index");
				response.sendRedirect(request.getContextPath() + "/jsp/index/index.jsp");
			} else {
				request.getSession().setAttribute("visitPage", "editarUserConAdmin");
				response.sendRedirect(request.getContextPath() + "/jsp/actions/editarUserConAdmin.jsp");
			}
			
			
//Submit para borrar usuario
		} else if (submitPageBtn.equals(rb.getString("sbmtBorrarUsuario"))){
		
			Usuario user = (Usuario) request.getSession().getAttribute("userParaModif");
		
			ServiceUserMain.borrarUsuario(user);
			request.getSession().setAttribute("mensajeInfo", rb.getString("eliminacionCorrecta"));
			ArrayList<Usuario> listaUsuarios = ServiceUserAuxiliar.getListaUsuarios();
			request.getSession().setAttribute("listaUsuarios", listaUsuarios);
			request.getSession().setAttribute("visitPage", "index");
			response.sendRedirect(request.getContextPath() + "/jsp/index/index.jsp");
		
//Submit para cancelar acción
		} else if (submitPageBtn.equals(rb.getString("sbmtCancelar"))){
			
			request.getSession().setAttribute("visitPage", "index");
			response.sendRedirect(request.getContextPath() + "/jsp/index/index.jsp");			
		
//Submit para buscar usuarios
		} else if (submitPageBtn.equals(rb.getString("sbmtBuscar"))){
			
			String cadenaBuscada = request.getParameter("cadenaBuscada");
			
			mensajeDanger = rb.getString(ServiceUserMain.buscarUsuario(cadenaBuscada));
			request.getSession().setAttribute("mensajeDanger", mensajeDanger);

			if (mensajeDanger.equals(rb.getString("buscarVacio"))) {
				ArrayList<Usuario> listaUsuarios = ServiceUserAuxiliar.getListaUsuarios();
				request.getSession().setAttribute("listaUsuarios", listaUsuarios);
				request.getSession().setAttribute("visitPage", "index");
				response.sendRedirect(request.getContextPath() + "/jsp/index/index.jsp");

			} else {
				ArrayList<Usuario> listaUsuarios = ServiceUserAuxiliar.buscarContiene(cadenaBuscada);
				request.getSession().setAttribute("listaUsuarios", listaUsuarios);
				request.getSession().setAttribute("searching", "searching");
				request.getSession().setAttribute("visitPage", "index");
				response.sendRedirect(request.getContextPath() + "/jsp/index/index.jsp");
			}
		
		}
	}

}
