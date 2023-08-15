<%@page import="models.modelPJ.Personaje"%>
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
	<%
		Personaje personaje = (Personaje) session.getAttribute("pjCreado");
		String lineas = (String) session.getAttribute("lineas");
	%>
	<div class="container text-dark">
		<div class="d-flex justify-content-center mx-5 py-4 bgCartel rounded">
			<div class="bgPergamino rounded">
				<h2 class="fw-bolder text-center my-3 mx-5"><%=rb.getString("empiezaLaAventura")%></h2>
			</div>
		</div>
		<div class="d-flex justify-content-center"><%@include
				file="../templates/mensajes.jsp"%></div>
		<div class="container text-dark justify-content-center">
			<div class="bgPergamino rounded">
				<form action="<%=request.getContextPath()%>/ControllerGame"
					method="post">
					<div class="grid-container-4c texto-justificado">
						<div class="grid-position-1-5 mx-5 my-4"><%=rb.getString(lineas)%></div>
						<input class="btn btn-secondary grid-position-2-4 mb-4" type="submit"
							id="btnContinuar" name="submitPageBtn"
							value="<%=rb.getString("sbmtContinuar")%>">
					</div>
				</form>
			</div>
		</div>
		<%@include file="../templates/pie.jsp"%>
	</div>
</body>
</html>