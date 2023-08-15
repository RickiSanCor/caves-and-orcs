<%@page import="services.serviceUsuario.ServiceUserAuxiliar"%>
<%@page import="models.modelUsuario.Usuario"%>
<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>
    <%
    	String idioma2 = (String) session.getAttribute("idioma");
    %>
    <%
    	ResourceBundle rb2 = ServiceUserAuxiliar.getResourceBundle(idioma2);
    %>
    <%Usuario userLogged2 = (Usuario) session.getAttribute("userLogged");%>
    <nav class= "navbar navbar-dark navbar-expand-lg bg-dark sticky-top z-0" data-bs-theme="dark">
      <div class="container-fluid">
        <a class="navbar-brand ms-1" href="<%=request.getContextPath()%>?visitPage=perfil" aria-current="page">
            <img src="<%=request.getContextPath()%>/img/iconperfil.png" alt="LogoPerfil" width="30" height="30" class="d-inline-block align-text-top">
            <span>Perfil</span>
        </a>
        
        <%if (userLogged2.getId() != 1) {%>
	        <div class="collapse navbar-collapse" id="navbarSupportedContent">
	          <ul class="navbar-nav me-auto">
	            <li class="nav-item">
	                <button class="btn btn-sm btn-secondary m-1 mt-2 ms-3" type="button">
	                  <a class="nav-link active p-0 fs-7" href="<%=request.getContextPath()%>?visitPage=editarPerfil"><%=rb2.getString("editarPerfil")%></a>
	                </button>
	            </li>
	            <li class="nav-item">
	            <button class="btn btn-sm btn-secondary m-1 mt-2 ms-3" type="button">
	              <a class="nav-link active p-0 fs-7" href="<%=request.getContextPath()%>?visitPage=cambiarPass"><%=rb2.getString("cambiarPass")%></a>
	            </button>  
	            </li>
	          </ul>
	        </div>
        <%}%>
      </div>
    </nav>
</body>
</html>