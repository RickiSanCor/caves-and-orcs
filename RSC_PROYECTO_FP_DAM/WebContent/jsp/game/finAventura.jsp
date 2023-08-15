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
		Personaje personaje = (Personaje) session.getAttribute("pjJugando");
		String titularFin = (String) session.getAttribute("titularFin");
		String motivoFin = (String) session.getAttribute("motivoFin");
		String recompensaFin = (String) session.getAttribute("recompensaFin");
	%>
	<div class="container text-dark">
		<div class="d-flex justify-content-center mx-5 py-4 bgCartel rounded">
			<div class="bgPergamino rounded">
				<h2 class="fw-bolder text-center my-3 mx-5"><%=rb.getString("terminaLaAventura")%></h2>
			</div>
		</div>
		<div class="d-flex justify-content-center"><%@include
				file="../templates/mensajes.jsp"%></div>
		<div class="d-flex justify-content-center">
			<div class="bgPergamino rounded">
				<form action="<%=request.getContextPath()%>/ControllerGame"
					method="post">
					<div class="m-4">
						<%
							if (motivoFin.equals("finAventuraVictoria")) {
						%>
						<div class="m-1 mb-2 text-center text-success fw-bold bg-success-subtle fs-5"><%=rb.getString(titularFin)%></div>
						<div class="m-1 text-center"><%=rb.getString(motivoFin)%></div>
						<div class="m-1 text-center"><%=rb.getString(recompensaFin)%></div>
						<div class="m-2">
							<div class="d-flex justify-content-center text-dark text-center">
								<div class="m-2 grid-container-4c grid-gap-5-30">
									<div class="boldTxt grid-position-1-3 grid-item-center fs-4"><%=personaje.getNombre()%></div>
									<div class="grid-position-1-2 grid-item-right">
										<label for="generoPJ"><%=rb.getString("generoPJ")%>:</label>
									</div>
									<div class="grid-position-c3-c5-r1-r5">
										<img
											src="<%=request.getContextPath()%>/img/pjs/<%=personaje.getRaza().getId()%><%=personaje.getClase().getId()%><%=personaje.getGenero().getId()%>.jpg"
											alt="Logo" width="180" height="240"
											class="d-inline-block align-text-top rounded">
									</div>
									<div><%=rb.getString(personaje.getGenero().getNombreGenero())%></div>
									<div class="grid-position-1-2 grid-item-right">
										<label for="razaPJ"><%=rb.getString("razaPJ")%>:</label>
									</div>
									<div><%=rb.getString(personaje.getRaza().getNombreRaza())%></div>
									<div class="grid-position-1-2 grid-item-right">
										<label for="clasePJ"><%=rb.getString("clasePJ")%>:</label>
									</div>
									<div><%=rb.getString(personaje.getClase().getNombreClase())%></div>
									<div class="grid-position-1-2 grid-item-right boldTxt">
										<label class="boldTxt underlineTxt grid-position-1-3 grid-item-center" for="atributosPJ"><%=rb.getString("atributosPJ")%></label>
									</div>
									<div class="grid-position-1-2 grid-item-right">
										<label for="puntosARepartir"><%=rb.getString("puntosARepartir")%>:</label>
									</div>
									<div>
										<input class="widthItem-60 text-center" type="text"
											id="puntosARepartir" name="puntosARepartir" readonly
											onmousedown="return false;" value="5">
									</div>
									<div class="grid-position-1-2 grid-item-right">
										<label for="fuerza"><%=rb.getString("fuerza")%>:</label>
									</div>
									<div>
										<input class="widthItem-60 text-center" type="number"
											id="fuerza" name="fuerza"
											min="<%=personaje.getAtributos().getFuerza()%>" max="100"
											value="0<%=personaje.getAtributos().getFuerza()%>"
											onkeydown="return false">
									</div>
									<div class="grid-item-right">
										<label for="minFuerza"><%=rb.getString("min")%>: </label>
									</div>
									<div><%=personaje.getAtributos().getFuerza()%></div>
									<div class="grid-position-1-2 grid-item-right">
										<label for="agilidad"><%=rb.getString("agilidad")%>:</label>
									</div>
									<div>
										<input class="widthItem-60 text-center" type="number"
											id="agilidad" name="agilidad"
											min="<%=personaje.getAtributos().getAgilidad()%>" max="100"
											value="0<%=personaje.getAtributos().getAgilidad()%>"
											onkeydown="return false">
									</div>
									<div class="grid-item-right">
										<label for="minAgilidad"><%=rb.getString("min")%>: </label>
									</div>
									<div><%=personaje.getAtributos().getAgilidad()%></div>
									<div class="grid-position-1-2 grid-item-right">
										<label for="vigor"><%=rb.getString("vigor")%>:</label>
									</div>
									<div>
										<input class="widthItem-60 text-center" type="number"
											id="vigor" name="vigor"
											min="<%=personaje.getAtributos().getVigor()%>" max="100"
											value="0<%=personaje.getAtributos().getVigor()%>"
											onkeydown="return false">
									</div>
									<div class="grid-item-right">
										<label for="minVigor"><%=rb.getString("min")%>: </label>
									</div>
									<div><%=personaje.getAtributos().getVigor()%></div>
									<div class="grid-position-1-2 grid-item-right">
										<label for="magia"><%=rb.getString("magia")%>:</label>
									</div>
									<div>
										<input class="widthItem-60 text-center" type="number"
											id="magia" name="magia"
											min="<%=personaje.getAtributos().getMagia()%>" max="100"
											value="0<%=personaje.getAtributos().getMagia()%>"
											onkeydown="return false">
									</div>
									<div class="grid-item-right">
										<label for="minMagia"><%=rb.getString("min")%>: </label>
									</div>
									<div><%=personaje.getAtributos().getMagia()%></div>
									<div class="grid-position-1-2 grid-item-right">
										<label for="labia"><%=rb.getString("labia")%>:</label>
									</div>
									<div>
										<input class="widthItem-60 text-center" type="number"
											id="labia" name="labia"
											min="<%=personaje.getAtributos().getLabia()%>" max="100"
											value="0<%=personaje.getAtributos().getLabia()%>"
											onkeydown="return false">
									</div>
									<div class="grid-item-right">
										<label for="minLabia"><%=rb.getString("min")%>: </label>
									</div>
									<div><%=personaje.getAtributos().getLabia()%></div>
									<input class="btn btn-success mt-3 grid-position-2-4"
										type="submit" id="btnGuardarProgreso" name="submitPageBtn"
										value="<%=rb.getString("sbmtGuardarProgreso")%>">
								</div>
							</div>
						</div>
						<%
							} else {
						%>
						<div class="m-1 mb-2 text-center text-danger fw-bold bg-danger-subtle fs-5"><%=rb.getString(titularFin)%></div>
						<div class="m-1 text-center"><%=rb.getString(motivoFin)%></div>
						<div class="m-1 text-center"><%=rb.getString(recompensaFin)%></div>
						<div class="m-2">
							<div class="d-flex justify-content-center text-dark text-center">
								<div class="m-2 grid-container-4c grid-gap-5-30">
									<div class="boldTxt grid-position-1-3 grid-item-center fs-4"><%=personaje.getNombre()%></div>
									<div class="grid-position-c3-c5-r1-r12">
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
									<div class="boldTxt underlineTxt grid-position-1-3 grid-item-center"><%=rb.getString("atributosPJ")%>
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
									<input class="btn btn-success mt-4 grid-position-2-4"
										type="submit" id="btnGuardarProgreso" name="submitPageBtn"
										value="<%=rb.getString("sbmtGuardarProgreso")%>">
								</div>
							</div>
						</div>
						<%
							}
						%>
						<%
							
						%>




					</div>
				</form>
			</div>
		</div>
		<%@include file="../templates/pie.jsp"%>
	</div>
</body>
</html>