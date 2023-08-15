<%@page import="services.serviceUsuario.ServiceUserAuxiliar"%>
<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<%
		String idioma3 = (String) session.getAttribute("idioma");
	%>
	<%
		ResourceBundle rb3 = ServiceUserAuxiliar.getResourceBundle(idioma3);
	%>
	<div
		class="container d-flex justify-content-center text-center fixedBottom text-dark fw-bold fs-4">
		<div class="bgFondoMadera rounded">
		
			<div class="mx-2 my-2 p-1 bgPergamino">
				<img src="<%=request.getContextPath()%>/img/logoDado500x500.png" alt="Logo"
					width="30" height="30" class="d-inline-block align-text-top">
				<span id="nombreRSC" class="mx-2"><%=rb3.getString("rsc")%></span>
				<img src="<%=request.getContextPath()%>/img/logoDado500x500.png" alt="Logo"
					width="30" height="30" class="d-inline-block align-text-top">
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		crossorigin="anonymous"></script>
	<script src="../../js/atributos.js" type="text/javascript"></script>
	<script src="../../js/descripciones.js" type="text/javascript"></script>
</body>
</html>