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
				<h2 class="fw-bolder text-center my-3 mx-5"><%=rb.getString("accion")%></h2>
			</div>
		</div>
		<div class="d-flex justify-content-center"><%@include
				file="../templates/mensajes.jsp"%></div>
		<div class="container text-dark justify-content-center">
			<div class="bgPergamino rounded">
				<form action="<%=request.getContextPath()%>/ControllerGame"
					method="post">
					<div class="grid-container-4c texto-justificado">
							<%
								if (lineas.contains("Exito")) {
							%>
							<div
								class="grid-position-2-4 mt-4 text-success bg-success-subtle fw-bold fs-5 text-center"><%=rb.getString("exito")%></div>
							<%
								} else if (lineas.contains("Fallo")) {
							%>
							<div
								class="grid-position-2-4 mt-4 text-danger bg-danger-subtle fw-bold fs-5 text-center"><%=rb.getString("fallo")%></div>
							<%
								}
							%>
						<div class="grid-position-1-5 mx-5 my-4"><%=rb.getString(lineas)%></div>
						<%
							if (lineas.equals("mision1Pagina3Opcion4Exito")) {
						%>
						<input class="btn btn-secondary mb-4 grid-position-2-4"
							type="submit" id="btnFinHuida" name="submitPageBtn"
							value="<%=rb.getString("sbmtFinAventura")%>">
						<%
							} else {
						%>
						<input class="btn btn-secondary mb-4 grid-position-2-4"
							type="submit" id="btnCombate" name="submitPageBtn"
							value="<%=rb.getString("sbmtCombate")%>">
						<%
							}
						%>
					</div>
				</form>
			</div>
		</div>
		<%@include file="../templates/pie.jsp"%>
	</div>
</body>
</html>