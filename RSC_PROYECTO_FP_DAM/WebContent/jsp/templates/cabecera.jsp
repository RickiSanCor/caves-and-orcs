<%@page import="services.serviceUsuario.ServiceUserAuxiliar"%>
<%@page import="models.modelUsuario.Usuario"%>
<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" type="image/x-icon" href="../../img/logoDado500x500.png">
	<title>Caves & Orcs</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
    <%String idioma = (String) session.getAttribute("idioma");%>
    <%ResourceBundle rb = ServiceUserAuxiliar.getResourceBundle(idioma);%>
    <%Usuario userLogged = (Usuario) session.getAttribute("userLogged");%>
    <nav class= "navbar navbar-dark navbar-expand-lg bg-dark sticky-top" data-bs-theme="light">
	  <div class="container-fluid">
	    <a class="navbar-brand ms-1 me-0" href="<%=request.getContextPath()%>" aria-current="page">
	       <img src="<%=request.getContextPath()%>/img/logoDado1705x1704.png" alt="Logo" width="30" height="30" class="d-inline-block align-text-top">
           <span class="">Caves & Orcs</span>
        </a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	       <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link active ms-3" href="<%=request.getContextPath()%>/ControllerPJ?visitPage=crearPJ"><%=rb.getString("crearPJ")%></a>
	        </li>
	        <%if (userLogged != null) {%>
	        <li class="nav-item">
              <a class="nav-link active ms-3" href="<%=request.getContextPath()%>/ControllerPJ?visitPage=listaPJs"><%=rb.getString("listaPJs")%></a>
            </li>
            <li class="nav-item">
              <a class="nav-link active ms-3" href="<%=request.getContextPath()%>/ControllerPJ?visitPage=verRanking"><%=rb.getString("verRanking")%></a>
            </li>
            <%}%>
          </ul>
          <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
            <%if (userLogged == null) {%>
            <li class="nav-item">
              <a class="nav-link active ms-3" href="<%=request.getContextPath()%>?visitPage=login"><%=rb.getString("login")%></a>
            </li>
            <li class="nav-item">
              <a class="nav-link active ms-3" href="<%=request.getContextPath()%>?visitPage=alta"><%=rb.getString("registro")%></a>
            </li>
            <%} else {%>
            <li class="nav-item">
               <a class="nav-link active ms-3" href="<%=request.getContextPath()%>?visitPage=perfil"><%=userLogged.getUsername()%></a>
            </li>
            <li class="nav-item">
                <button class="btn btn-sm btn-secondary m-0 mt-1 ms-3 pt-1 pb-1" type="button">
                    <a class="nav-link active p-0 fs-6" href="<%=request.getContextPath()%>?visitPage=cerrarSesion"><%=rb.getString("cerrarSesion")%></a>
                </button>  
            </li>
            <%}%>
            <li class="nav-item dropdown">
	            <a class="nav-link dropdown-toggle active ms-3 p-0" href="#" role="button" id="navbarDropdownMenuLink" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	               <img src="<%=request.getContextPath()%>/img/iconidioma.png" alt="Logo" width="35" height="35" class="d-inline-block align-text-top">
	            </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="<%=request.getContextPath()%>?idioma=es&idiomaCambiado=true"><%=rb.getString("spanish")%></a>
					<a class="dropdown-item" href="<%=request.getContextPath()%>?idioma=en&idiomaCambiado=true"><%=rb.getString("english")%></a>
				</div>
            </li>
          </ul>
        </div>
	  </div>
	</nav>
</body>
</html>

