<%@page import="models.modelUsuario.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="../templates/cabecera.jsp"%>
<%@include file="../templates/cabeceraEditarPerfil.jsp"%>
</head>
<body>
	<%
		Usuario user = (Usuario) session.getAttribute("userLogged");
	%>
	<div class="container text-dark">
		<div class="d-flex justify-content-center"><%@include
				file="../templates/mensajes.jsp"%></div>
		<div class="container d-flex justify-content-center">
			<fieldset class="bgPergamino rounded">
				<div class="text-dark fw-bold m-3 fs-4">
					<div><%=rb.getString("username")%>:
						<%=user.getUsername()%></div>
					<div><%=rb.getString("nombre")%>:
						<%=user.getNombre()%></div>
					<div><%=rb.getString("apellidos")%>:
						<%=user.getApellidos()%></div>
					<div><%=rb.getString("email")%>:
						<%=user.getEmail()%></div>
				</div>
			</fieldset>
		</div>
		<%@include file="../templates/pie.jsp"%>
	</div>
</body>
</html>