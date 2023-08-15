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
	<%
		Usuario user = (Usuario) session.getAttribute("userLogged");
	%>
	<%@include file="../templates/cabeceraEditarPerfil.jsp"%>
	<div class="container text-dark">
		<div class="d-flex justify-content-center"><%@include
				file="../templates/mensajes.jsp"%></div>
		<div class="container d-flex justify-content-center">
			<fieldset>
				<form action="<%=request.getContextPath()%>/" method="post">
					<div class="d-flex justify-content-start text-dark text-center">
						<div class="d-flex bgPergamino rounded">
							<div class="m-4 grid-container-2c grid-gap-20-30">
								<div class="grid-position-1-2 grid-item-right">
									<label for="username">sbmtEditarPerfil:</label>
								</div>
								<input type="text" id="username" name="username"
									value="<%=user.getUsername()%>">
								<div class="grid-position-1-2 grid-item-right">
									<label for="nombre"><%=rb.getString("nombre")%>:</label>
								</div>
								<input type="text" id="nombre" name="nombre"
									value="<%=user.getNombre()%>">
								<div class="grid-position-1-2 grid-item-right">
									<label for="apellidos"><%=rb.getString("apellidos")%>:</label>
								</div>
								<input type="text" id="apellidos" name="apellidos"
									value="<%=user.getApellidos()%>">
								<div class="grid-position-1-2 grid-item-right">
									<label for="email"><%=rb.getString("email")%>:</label>
								</div>
								<input type="email" id="email" name="email"
									value="<%=user.getEmail()%>">
								<%
									if (user.getRol().equals("Admin")) {
								%>
								<div class="grid-position-1-2 grid-item-right">
									<label for="rol"><%=rb.getString("rol")%>:</label>
								</div>
								<select name="rol">
									<option value="Admin"><%=rb.getString("admin")%></option>
									<option value="Normal"><%=rb.getString("normal")%></option>
								</select>
								<%
									}
								%>
								<input class="btn btn-secondary mt-3" type="submit"
									id="btnEditar" name="submitPageBtn"
									value="<%=rb.getString("sbmtEditarPerfil")%>">
							</div>
						</div>
					</div>
				</form>
			</fieldset>
		</div>
		<%@include file="../templates/pie.jsp"%>
	</div>
</body>
</html>