<%@page import="java.util.ArrayList"%>
<%@page import="models.modelPJ.Genero"%>
<%@page import="models.modelPJ.Raza"%>
<%@page import="models.modelPJ.Clase"%>
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
				<h2 class="fw-bolder text-center my-3 mx-5"><%=rb.getString("creaPJ")%></h2>
			</div>
		</div>
		<div class="d-flex justify-content-center"><%@include
				file="../templates/mensajes.jsp"%></div>
		<div class="container">
			<%
				ArrayList<Genero> listaGenero = (ArrayList<Genero>) session.getAttribute("listaGenero");
				ArrayList<Raza> listaRaza = (ArrayList<Raza>) session.getAttribute("listaRaza");
				ArrayList<Clase> listaClase = (ArrayList<Clase>) session.getAttribute("listaClase");
				ArrayList<String> parametrosPJ = (ArrayList<String>) session.getAttribute("parametrosPJ");
				String textoRaza = (String) session.getAttribute("textoRaza");
				String textoClase = (String) session.getAttribute("textoClase");
			%>
			<fieldset>
				<form action="<%=request.getContextPath()%>/ControllerPJ"
					method="post">
					<div class="d-flex flex-row flex-wrap justify-content-between">

						<div class="mb-4">
							<div class="d-flex justify-content-start text-dark text-center">
								<div class="d-flex bgPergamino rounded">
									<div class="m-4 grid-container-2c grid-gap-20-30">
										<div class="grid-item-right">
											<label for="nombrePJ"><%=rb.getString("nombrePJ")%>:</label>
										</div>
										<%
											if (parametrosPJ.get(0).equals("")) {
										%>
										<div class="grid-item-left">
											<input class="ms-3" type="text" id="nombrePJ" name="nombrePJ"
												placeholder=<%=rb.getString("nombrePJ")%>>
										</div>
										<%
											} else {
										%>
										<div class="grid-item-left">
											<input class="ms-3" type="text" id="nombrePJ" name="nombrePJ"
												placeholder=<%=rb.getString("nombrePJ")%>
												value=<%=parametrosPJ.get(0)%>>
										</div>

										<%
											}
										%>
										<div class="grid-item-right">
											<label for="generoPJ"><%=rb.getString("generoPJ")%>:</label>
										</div>
										<%
											if (parametrosPJ.get(1).equals("")) {
										%>
										<div class="grid-item-left">
											<select class="ms-3" id="generoPJ" name="generoPJ">
												<option disabled selected value><%=rb.getString("elegirGenero")%></option>
												<%
													for (Genero genero : listaGenero) {
												%>
												<option value="<%=genero.getNombreGenero()%>"><%=rb.getString(genero.getNombreGenero())%></option>
												<%
													}
												%>
											</select>
										</div>
										<%
											} else {
										%>
										<div class="grid-item-left">
											<select class="ms-3" id="generoPJ" name="generoPJ">
												<option disabled value><%=rb.getString("elegirGenero")%></option>
												<%
													for (Genero genero : listaGenero) {
															if (genero.getNombreGenero().equals(parametrosPJ.get(1))) {
												%>
												<option selected value="<%=genero.getNombreGenero()%>"><%=rb.getString(genero.getNombreGenero())%></option>
												<%
													} else {
												%>
												<option value="<%=genero.getNombreGenero()%>"><%=rb.getString(genero.getNombreGenero())%></option>
												<%
													}
														}
												%>
											</select>
										</div>
										<%
											}
										%>
										<div class="grid-item-right">
											<label for="razaPJ"><%=rb.getString("razaPJ")%>:</label>
										</div>
										<%
											if (parametrosPJ.get(2).equals("")) {
										%>
										<div class="grid-item-left">
											<select class="ms-3" id="razaPJ" name="razaPJ">
												<option disabled selected value><%=rb.getString("elegirRaza")%></option>
												<%
													for (Raza raza : listaRaza) {
												%>
												<option value="<%=raza.getNombreRaza()%>"><%=rb.getString(raza.getNombreRaza())%></option>
												<%
													}
												%>
											</select>
										</div>
										<%
											} else {
										%>
										<div class="grid-item-left">
											<select class="ms-3" id="razaPJ" name="razaPJ">
												<option disabled value><%=rb.getString("elegirRaza")%></option>
												<%
													for (Raza raza : listaRaza) {
															if (raza.getNombreRaza().equals(parametrosPJ.get(2))) {
												%>

												<option selected value="<%=raza.getNombreRaza()%>"><%=rb.getString(raza.getNombreRaza())%></option>
												<%
													} else {
												%>
												<option value="<%=raza.getNombreRaza()%>"><%=rb.getString(raza.getNombreRaza())%></option>
												<%
													}
														}
												%>
											</select>
										</div>
										<%
											}
										%>
										<div class="grid-item-right">
											<label for="clasePJ"><%=rb.getString("clasePJ")%>:</label>
										</div>
										<%
											if (parametrosPJ.get(3).equals("")) {
										%>
										<div class="grid-item-left">
											<select class="ms-3" id="clasePJ" name="clasePJ">
												<option disabled selected value><%=rb.getString("elegirClase")%></option>
												<%
													for (Clase clase : listaClase) {
												%>
												<option value="<%=clase.getNombreClase()%>"><%=rb.getString(clase.getNombreClase())%></option>
												<%
													}
												%>
											</select>
										</div>
										<%
											} else {
										%>
										<div class="grid-item-left">
											<select class="ms-3" id="clasePJ" name="clasePJ">
												<option disabled selected value><%=rb.getString("elegirClase")%></option>
												<%
													for (Clase clase : listaClase) {
															if (clase.getNombreClase().equals(parametrosPJ.get(3))) {
												%>

												<option selected value="<%=clase.getNombreClase()%>"><%=rb.getString(clase.getNombreClase())%></option>
												<%
													} else {
												%>
												<option value="<%=clase.getNombreClase()%>"><%=rb.getString(clase.getNombreClase())%></option>
												<%
													}
														}
												%>
											</select>
										</div>
										<%
											}
										%>
										<input class="btn btn-success grid-position-1-2" type="submit"
											id="btnCrearPJ" name="submitPageBtn"
											value="<%=rb.getString("sbmtElegirAtrbPJ")%>">
									</div>
								</div>
							</div>
						</div>
						<div class="d-flex bgPergamino rounded">
							<div class="grid-container-2c-2r grid-gap-5-30">
								<div class="grid-item-left ms-3 mt-2">
									<input type="submit" id="btnVerRaza" class="btn btn-secondary"
										name="submitPageBtn" value="<%=rb.getString("sbmtVerRaza")%>">
								</div>
								<div class="grid-item-left mt-2">
									<input type="submit" id="btnVerClase" class="btn btn-secondary"
										name="submitPageBtn" value="<%=rb.getString("sbmtVerClase")%>">
								</div>
								<%
									if (textoRaza != null) {
								%>
								<div id="textoRaza" class="ms-3 my-3 texto-justificado"><%=rb.getString(textoRaza)%></div>
								<%
									} else {
								%>
								<div id="textoRaza" class="ms-3 my-3 texto-justificado"><%=rb.getString("")%></div>
								<%
									}
								%>
								<%
									if (textoRaza != null) {
								%>
								<div id="textoClase" class="me-3 my-3 texto-justificado"><%=rb.getString(textoClase)%></div>
								<%
									} else {
								%>
								<div id="textoClase" class="me-3 my-3 texto-justificado"><%=rb.getString("")%></div>
								<%
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