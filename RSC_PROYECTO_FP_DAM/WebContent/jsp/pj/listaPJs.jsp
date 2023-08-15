<%@page import="java.util.ArrayList"%>
<%@page import="models.modelUsuario.Usuario"%>
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
	<div class="container text-dark">
		<div class="d-flex justify-content-center mx-5 py-4 bgCartel rounded">
			<div class="bgPergamino rounded">
				<h2 class="fw-bolder text-center my-3 mx-5"><%=rb.getString("listaPJs")%></h2>
			</div>
		</div>
		<%
			ArrayList<Personaje> lista = (ArrayList<Personaje>) session.getAttribute("listaPersonajes");
		%>
		<%
			String searching = (String) session.getAttribute("searching");
		%>
		<%
			if (lista.size() == 0 && (searching == null || !searching.equals("searching"))) {
				session.setAttribute("mensajeInfo", rb.getString("noHayPersonajes"));
			}
		%>
		<div class="d-flex justify-content-center"><%@include
				file="../templates/mensajes.jsp"%></div>
		<%
			if (lista.size() != 0 || (searching != null && searching.equals("searching"))) {
		%>
		<div class="d-flex justify-content-end me-5 my-4">
			<form action="<%=request.getContextPath()%>/ControllerPJ"
				method="post" class="d-flex" role="search">
				<%
					if (searching != null && searching.equals("searching")) {
				%>
				<button class="btn btn-sm btn-light translate-middle-y ms-3 fs-6"
					type="button">
					<div>
						<a class="nav-link active p-0 fs-7 text-nowrap"
							href="<%=request.getContextPath()%>/ControllerPJ?visitPage=quitarFiltro"><%=rb.getString("quitarFiltro")%></a>
					</div>
				</button>
				<%
					}
				%>
				<input class="form-control translate-middle-y ms-3" type="text"
					id="cadenaBuscada" name="cadenaBuscada"
					placeholder="<%=rb.getString("phBuscarPJ")%>">
				<button
					class="btn btn-sm btn-secondary translate-middle-y ms-3 fs-6"
					type="submit" id="btnBuscarPJ" name="submitPageBtn"
					value=<%=rb.getString("sbmtBuscar")%>><%=rb.getString("sbmtBuscar")%></button>
			</form>
		</div>
		<div class="d-flex justify-content-center text-dark text-center">
			<div class="grid-container-4c grid-gap-20-30">
				<%
					for (Personaje personaje : lista) {
				%>
				<div class="d-flex bgPergamino rounded">
					<div class="m-3 grid-container-2c-70px grid-gap-5-30">
						<%
                            if (personaje.getAtributos().getVigor() == 0) {
                        %>
                        <div
                            class="grid-position-1-3 m-2  fw-bold text-light border border-dark bg-dark rounded fs-5 text-center"><%=rb.getString("RIP")%></div>
                        <%
                            }
                        %>
						<div class="grid-item-right">
							<img
								src="<%=request.getContextPath()%>/img/pjs/<%=personaje.getRaza().getId()%><%=personaje.getClase().getId()%><%=personaje.getGenero().getId()%>.jpg"
								alt="Logo" width="30" height="40"
								class="d-inline-block align-text-top rounded">
						</div>
						<div class="boldTxt "><%=personaje.getNombre()%></div>
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
						<button
							class="btn btn-sm btn-secondary mt-3 grid-position-1-2 widthItem-60"
							type="button">
							<a class="nav-link active p-0 fs-7"
								href="<%=request.getContextPath()%>/ControllerPJ?visitPage=vistaPJ&idPJ=<%=personaje.getId()%>"><%=rb.getString("verPJ")%></a>
						</button>
						<button
							class="btn btn-sm btn-danger mt-3 widthItem-60 grid-item-left"
							type="button">
							<a class="nav-link active p-0 fs-7"
								href="<%=request.getContextPath()%>/ControllerPJ?visitPage=borrarPJ&idPJ=<%=personaje.getId()%>"><%=rb.getString("borrarPJ")%></a>
						</button>
					</div>
				</div>
				<%
					}
				%>
			</div>
		</div>
		<%
			}
		%>
		<%@include file="../templates/pie.jsp"%>
	</div>
</body>
</html>