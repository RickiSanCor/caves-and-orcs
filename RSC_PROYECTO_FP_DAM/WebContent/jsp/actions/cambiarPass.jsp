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
	<%@include file="../templates/cabeceraEditarPerfil.jsp"%>
	<div class="container text-dark">
		<div class="d-flex justify-content-center"><%@include
				file="../templates/mensajes.jsp"%></div>
		<div class="container d-flex justify-content-center">
			<fieldset>
				<form action="<%=request.getContextPath()%>/" method="post">
					<div class="d-flex justify-content-start text-dark text-center">
						<div class="d-flex bgPergamino rounded">
							<div class="m-2 grid-container-2c grid-gap-20-30">
								<div class="grid-position-1-2 grid-item-right">
									<label for="pass"><%=rb.getString("passwordActual")%>:</label>
								</div>
								<input type="password" id="actualPass" name="actualPass"
									placeholder=<%=rb.getString("passwordActual")%>>
								<div class="grid-position-1-2 grid-item-right">
									<label for="pass"><%=rb.getString("newPass")%>:</label>
								</div>
								<input type="password" id="pass" name="pass"
									placeholder=<%=rb.getString("newPass")%>>
								<div class="grid-position-1-2 grid-item-right">
									<label for="pass2"><%=rb.getString("newPass2")%>:</label>
								</div>
								<input type="password" id="pass2" name="pass2"
									placeholder=<%=rb.getString("newPass2")%>> <input
									class="btn btn-secondary mt-3" type="submit"
									id="btnCambiarPass" name="submitPageBtn"
									value="<%=rb.getString("sbmtCambiarPass")%>">
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