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
	<div class="container text-dark">
		<div class="d-flex justify-content-center"><%@include
				file="../templates/mensajes.jsp"%></div>
		<div class="container d-flex justify-content-center text-dark">
			<%
				Usuario user = (Usuario) session.getAttribute("userParaModif");
			%>
			<fieldset class="mt-5 bgPergamino rounded">
				<div class="m-3"><%=rb.getString("confirmBorrar")%>
					<strong><%=user.getUsername()%></strong>?
				</div>
				<form class="text-center"
					action="<%=request.getContextPath()%>/ControllerLoginAdmin"
					method="post">
					<input class="btn btn-danger m-3" type="submit" id="btnBorrarUser"
						name="submitPageBtn"
						value="<%=rb.getString("sbmtBorrarUsuario")%>"> <input
						class="btn btn-secondary m-3" type="submit" id="btnCancelar"
						name="submitPageBtn" value="<%=rb.getString("sbmtCancelar")%>">
				</form>
			</fieldset>
		</div>
		<%@include file="../templates/pie.jsp"%>
	</div>
</body>
</html>