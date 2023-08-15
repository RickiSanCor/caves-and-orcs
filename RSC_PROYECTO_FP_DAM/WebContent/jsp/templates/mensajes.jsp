<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
</head>
<body>
    <% 
    String mensajeDanger = (String) session.getAttribute("mensajeDanger");
   	String mensajeInfo = (String) session.getAttribute("mensajeInfo");
   	if (mensajeDanger == null) {
   		mensajeDanger = "";
   	}
   	if (mensajeInfo == null) {
   		mensajeInfo = "";
    }
    %>
    <div class="my-3"><span class="text-success fw-bold bg-success-subtle fs-5"><%=mensajeInfo%></span></div>
    <div class="my-3"><span class="text-danger fw-bold bg-danger-subtle fs-5"><%=mensajeDanger%></span></div>
</body>
</html>