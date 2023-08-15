<%@page import="models.modelPJ.Personaje"%>
<%@page import="models.modelPJ.Genero"%>
<%@page import="models.modelPJ.Raza"%>
<%@page import="models.modelPJ.Clase"%>
<%@page import="models.modelPJ.Atributos"%>
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
				<h2 class="fw-bolder text-center my-3 mx-5"><%=rb.getString("eligeAtrPJ")%></h2>
			</div>
		</div>
		<div class="d-flex justify-content-center"><%@include
				file="../templates/mensajes.jsp"%></div>
		<div class="container d-flex justify-content-center">
			<%
				Personaje personaje = (Personaje) session.getAttribute("pjCreadoParcial");
			%>
			<fieldset>
				<form action="<%=request.getContextPath()%>/ControllerPJ"
					method="post">
					<div class="d-flex justify-content-start text-dark text-center">
						<div class="d-flex bgPergamino rounded">
							<div class="m-4 grid-container-4c grid-gap-20-30">
								<div class="grid-position-1-2 grid-item-right">
									<label for="nombrePJ"><%=rb.getString("nombrePJ")%>:</label>
								</div>
								<div><%=personaje.getNombre()%></div>
								<div class="grid-position-1-2 grid-item-right">
									<label for="generoPJ"><%=rb.getString("generoPJ")%>:</label>
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
								<div>
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
								<div>
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
								<div>
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
								<div>
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
								<div>
									<label for="minLabia"><%=rb.getString("min")%>: </label>
								</div>
								<div><%=personaje.getAtributos().getLabia()%></div>
								<input class="btn btn-success mt-3 grid-position-1-2" type="submit"
									id="btnCrearPJ" name="submitPageBtn"
									value="<%=rb.getString("sbmtCrearPJ")%>">
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