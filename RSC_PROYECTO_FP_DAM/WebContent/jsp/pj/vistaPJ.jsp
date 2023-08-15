<%@page import="java.util.ArrayList"%>
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
	%>
	<div class="container text-dark">
		<div class="d-flex justify-content-center mx-5 py-4 bgCartel rounded">
			<div class="bgPergamino rounded">
				<h2 class="fw-bolder text-center my-3 mx-5"><%=rb.getString("vistaPJ")%></h2>
			</div>
		</div>
		<div class="d-flex justify-content-center"><%@include
				file="../templates/mensajes.jsp"%></div>
		<div class="container d-flex justify-content-center">
			<fieldset>
				<form action="<%=request.getContextPath()%>/ControllerPJ"
					method="post">
					<div class="d-flex justify-content-start text-dark text-center">
						<div class="d-flex bgPergamino rounded">
							<div class="m-4 grid-container-4c grid-gap-5-30">
								<%
									if (personaje.getAtributos().getVigor() == 0) {
								%>
								<div
									class="grid-position-1-5 m-2 text-light border border-dark bg-dark rounded fw-bold fs-5 text-center"><%=rb.getString("RIP")%></div>
								<div class="boldTxt grid-position-1-3 grid-item-center fs-4"><%=personaje.getNombre()%></div>
								<div class="grid-position-c3-c5-r2-r13">
								<%
									} else {
								%>
								<div class="boldTxt grid-position-1-3 grid-item-center fs-4"><%=personaje.getNombre()%></div>
								<div class="grid-position-c3-c5-r1-r12">
								<%
                                    }
                                %>
									<img
										src="<%=request.getContextPath()%>/img/pjs/<%=personaje.getRaza().getId()%><%=personaje.getClase().getId()%><%=personaje.getGenero().getId()%>.jpg"
										alt="Logo" width="300" height="400"
										class="d-inline-block align-text-top rounded">
								</div>
								<div class="grid-item-right"><%=rb.getString("saludPJ")%>:
								</div>
								<div><%=personaje.getSalud()%></div>
								<div class="grid-item-right"><%=rb.getString("generoPJ")%>:
								</div>
								<div><%=rb.getString(personaje.getGenero().getNombreGenero())%></div>
								<div class="grid-item-right"><%=rb.getString("razaPJ")%>:
								</div>
								<div><%=rb.getString(personaje.getRaza().getNombreRaza())%></div>
								<div class="grid-item-right"><%=rb.getString("clasePJ")%>:
								</div>
								<div><%=rb.getString(personaje.getClase().getNombreClase())%></div>
								<div
									class="boldTxt underlineTxt grid-position-1-3 grid-item-center"><%=rb.getString("atributosPJ")%>
								</div>
								<div class="grid-position-1-2 grid-item-right"><%=rb.getString("fuerza")%>:
								</div>
								<div><%=personaje.getAtributos().getFuerza()%></div>
								<div class="grid-item-right"><%=rb.getString("agilidad")%>:
								</div>
								<div><%=personaje.getAtributos().getAgilidad()%></div>
								<div class="grid-item-right"><%=rb.getString("vigor")%>:
								</div>
								<div><%=personaje.getAtributos().getVigor()%></div>
								<div class="grid-item-right"><%=rb.getString("magia")%>:
								</div>
								<div><%=personaje.getAtributos().getMagia()%></div>
								<div class="grid-item-right"><%=rb.getString("labia")%>:
								</div>
								<div><%=personaje.getAtributos().getLabia()%></div>

								<%
									if (personaje.getAtributos().getVigor() != 0) {
										String yaGuardado = (String) session.getAttribute("yaGuardado");
										if (yaGuardado == null || yaGuardado.equals("no")) {
								%>
								<input class="btn btn-success mt-3 grid-position-1-5"
									type="submit" id="btnGuardarPJ" name="submitPageBtn"
									value="<%=rb.getString("sbmtGuardarPJ")%>">
								<%
									} else {
								%>
								<input class="btn btn-success mt-3 grid-position-1-5"
									type="submit" id="btnJugar" name="submitPageBtn"
									value="<%=rb.getString("sbmtJugar")%>">
								<%
									}
									}
								%>
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