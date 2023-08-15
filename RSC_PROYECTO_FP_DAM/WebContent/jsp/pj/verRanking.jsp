<%@page import="java.util.ArrayList"%>
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
    ArrayList<Personaje> listaPersonajes = (ArrayList<Personaje>) session.getAttribute("listaPersonajes");
    %>
    <div class="container text-dark">
        <div class="d-flex justify-content-center mx-5 py-4 bgCartel rounded">
            <div class="bgPergamino rounded">
                <h2 class="fw-bolder text-center my-3 mx-5"><%=rb.getString("ranking")%></h2>
            </div>
        </div>
        <div class="d-flex justify-content-center"><%@include
                file="../templates/mensajes.jsp"%></div>
        <div class="container text-dark justify-content-center">
            <div class="bgPergamino rounded">
                <form action="<%=request.getContextPath()%>/ControllerGame"
                    method="post">
                    <div class="grid-container-6c texto-justificado">
                        <div class="grid-position-1-7 boldTxt fs-4 p-4 border border-dark text-center"><%=rb.getString("ranking")%></div>
                        
                        <div class="p-2 boldTxt border border-dark text-center"><%=rb.getString("posicion")%></div>
                        <div class="p-2 boldTxt border border-dark text-center"><%=rb.getString("nameUser")%></div>
                        <div class="p-2 boldTxt border border-dark text-center"><%=rb.getString("namePJ")%></div>
                        <div class="p-2 boldTxt border border-dark text-center"><%=rb.getString("numVictory")%></div>
                        <div class="p-2 boldTxt border border-dark text-center"><%=rb.getString("numDefeat")%></div>
                        <div class="p-2 boldTxt border border-dark text-center"><%=rb.getString("numPoints")%></div>
                        
                        <%
                        int posicion = 1;
                        for (Personaje personaje : listaPersonajes) {
                        %>
                        
                        <div class="p-2 border border-dark text-center"><%=posicion%></div>
                        <div class="p-2 border border-dark text-center"><%=listaPersonajes.get(posicion-1).getUsuario().getUsername()%></div>
                        <div class="p-2 border border-dark text-center"><%=listaPersonajes.get(posicion-1).getNombre()%></div>
                        <div class="p-2 border border-dark text-center"><%=listaPersonajes.get(posicion-1).getVictorias()%></div>
                        <div class="p-2 border border-dark text-center"><%=listaPersonajes.get(posicion-1).getDerrotas()%></div>
                        <div class="p-2 border border-dark text-center"><%=listaPersonajes.get(posicion-1).getPuntos()%></div>
                        	
                        <%
                        posicion++;
                        }
                        %>
                        
                    </div>
                </form>
            </div>
        </div>
        <%@include file="../templates/pie.jsp"%>
    </div>
</body>
</html>