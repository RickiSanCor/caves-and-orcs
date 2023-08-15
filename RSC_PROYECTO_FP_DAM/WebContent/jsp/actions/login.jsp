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
		<div class="container d-flex justify-content-center">
			<fieldset>
				<form action="<%=request.getContextPath()%>/" method="post">
					<div class="d-flex justify-content-start text-dark text-center">
						<div class="d-flex bgPergamino rounded">
							<div class="m-4 grid-container-2c grid-gap-20-30">
								<div class="grid-position-1-2 grid-item-right">
									<label for="username"><%=rb.getString("username")%>:</label>
								</div>
								<input type="text" id="username" name="username"
									placeholder="<%=rb.getString("username")%>">
								<div class="grid-position-1-2 grid-item-right">
									<label for="pass"><%=rb.getString("password")%>:</label>
								</div>
								<input type="password" id="pass" name="pass"
									placeholder="<%=rb.getString("password")%>"> <input
									class="btn btn-secondary mt-3" type="submit" id="btnLogin"
									name="submitPageBtn" value="<%=rb.getString("sbmtLogin")%>">
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