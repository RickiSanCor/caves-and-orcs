<%@page import="java.util.ArrayList"%>
<%@page import="models.modelUsuario.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="../templates/cabecera.jsp"%>
</head>
<body>
	<br />
	<div class="container text-dark">
		<div class="d-flex justify-content-center mx-5 py-4 bgCartel rounded">
			<div class="bgPergamino rounded">
				<h2 class="fw-bolder text-center my-3 mx-5"><%=rb.getString("bienvenida")%></h2>
			</div>
		</div>
		<div class="d-flex justify-content-center"><%@include
				file="../templates/mensajes.jsp"%></div>
		<%
			if (userLogged != null && userLogged.getRol().equals("Admin")) {
		%>
		<div class="d-flex justify-content-end my-4">
			<%
				ArrayList<Usuario> lista = (ArrayList<Usuario>) session.getAttribute("listaUsuarios");
			%>
			<%
				String searching = (String) session.getAttribute("searching");
			%>
			<form action="<%=request.getContextPath()%>/ControllerLoginAdmin"
				method="post" class="d-flex" role="search">
				<%
					if (searching != null && searching.equals("searching")) {
				%>
				<button class="btn btn-sm btn-light translate-middle-y ms-3 fs-6"
					type="button">
					<div>
						<a class="nav-link active p-0 fs-7 text-nowrap"
							href="<%=request.getContextPath()%>/ControllerLoginAdmin?visitPage=quitarFiltro"><%=rb.getString("quitarFiltro")%></a>
					</div>
				</button>
				<%
					}
				%>
				<input class="form-control translate-middle-y ms-3" type="text"
					id="cadenaBuscada" name="cadenaBuscada"
					placeholder="<%=rb.getString("phBuscarUsuario")%>">
				<button
					class="btn btn-sm btn-secondary translate-middle-y ms-3 fs-6"
					type="submit" id="btnBuscar" name="submitPageBtn"
					value=<%=rb.getString("sbmtBuscar")%>><%=rb.getString("sbmtBuscar")%></button>
			</form>
		</div>
		<div class="row text-dark" id="card-dinamica">
			<%
				for (Usuario user : lista) {
			%>
			<article class="col-md-6 col-lg-3 mb-3">
				<div class="card text-center shadow bgPergamino">
					<div class="mt-3"><%=rb.getString("username")%>:
						<%=user.getUsername()%></div>
					<div><%=rb.getString("nombre")%>:
						<%=user.getNombre()%></div>
					<div><%=rb.getString("apellidos")%>:
						<%=user.getApellidos()%></div>
					<div><%=rb.getString("email")%>:
						<%=user.getEmail()%></div>
					<div class="card-body">
						<button class="btn btn-sm btn-secondary m-1 mt-2 ms-3"
							type="button">
							<a class="nav-link active p-0 fs-7"
								href="<%=request.getContextPath()%>/ControllerLoginAdmin?visitPage=editarUserConAdmin&idUserModif=<%=user.getUsername()%>"><%=rb.getString("editar")%></a>
						</button>
						<button class="btn btn-sm btn-danger m-1 mt-2 ms-3" type="button">
							<a class="nav-link active p-0 fs-7"
								href="<%=request.getContextPath()%>/ControllerLoginAdmin?visitPage=borrarUser&idUserModif=<%=user.getUsername()%>"><%=rb.getString("borrar")%></a>
						</button>
					</div>
				</div>
			</article>
			<%
				}
			%>
		</div>
		<%
			}
		%>
		<%@include file="../templates/pie.jsp"%>
	</div>
</body>
</html>