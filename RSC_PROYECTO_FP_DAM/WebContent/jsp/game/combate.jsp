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
        Personaje personaje = (Personaje) session.getAttribute("pjCreado");
        Personaje enemigo = (Personaje) session.getAttribute("enemigo");
        ArrayList<String> filasGrid = (ArrayList<String>) session.getAttribute("filasGrid");
        int saludPJ = (int) session.getAttribute("saludPJ");
        int saludEnemigo = (int) session.getAttribute("saludEnemigo");
        int numBotones = (int) session.getAttribute("numBotones");
        ArrayList<String> nombresBotones = (ArrayList<String>) session.getAttribute("nombresBotones");
        String nombreAtacante = (String) session.getAttribute("nombreAtacante");
        String nombreDefensor = (String) session.getAttribute("nombreDefensor");
        String primerTurno = (String) session.getAttribute("primerTurno");
        String combate = (String) session.getAttribute("combate");
        String turno = (String) session.getAttribute("turno");
        
        if (nombreAtacante.equals(enemigo.getNombre())) {
        	nombreAtacante = rb.getString(enemigo.getNombre());
        } else if (nombreDefensor.equals(enemigo.getNombre())) {
        	nombreDefensor = rb.getString(enemigo.getNombre());
        }
//         if (combate.equals("combateVictoria") || combate.equals("combateDerrota")) {
//             nombreAtacante = "";
//             nombreDefensor = "";
//         }
    %>
    <div class="container text-dark">
        <div class="d-flex justify-content-center mx-5 py-4 bgCartel rounded">
            <div class="bgPergamino rounded">
                <h2 class="fw-bolder text-center my-3 mx-5"><%=rb.getString("combate")%></h2>
            </div>
        </div>
        <div class="d-flex justify-content-center"><%@include
                file="../templates/mensajes.jsp"%></div>
        <div class="container text-dark justify-content-center">
            <div class="bgPergamino rounded">
                <form action="<%=request.getContextPath()%>/ControllerGame"
                    method="post">
                    <div class="grid-container-5c text-center">
                        <%-- Fila 1 --%>
                        <div class="grid-position-2-5 m-2"><%=rb.getString(filasGrid.get(0))%></div>

                        <div class=" grid-position-c1-c2-r1-r8 m-2">
                            <img
                                src="<%=request.getContextPath()%>/img/pjs/<%=personaje.getRaza().getId()%><%=personaje.getClase().getId()%><%=personaje.getGenero().getId()%>.jpg"
                                alt="Logo" width="180" height="240"
                                class="d-inline-block align-text-top rounded">
                        </div>

                        <div class="boldTxt m-2"><%=personaje.getNombre()%></div>

                        <%-- Fila 2 --%>
                        <div class="boldTxt m-2"><%=rb.getString(filasGrid.get(1)) + " " + nombreAtacante%></div>

                        <div class="boldTxt m-2"><%=rb.getString(enemigo.getNombre())%></div>

                        <div class="grid-item-center grid-position-c5-c6-r1-r8 m-2">
                        
                            <img
                                src="<%=request.getContextPath()%>/img/enemigos/<%=enemigo.getNombre()%>.jpg"
                                alt="Logo" width="180" height="240"
                                class="d-inline-block align-text-top rounded">
                        </div>

                        <div class="m-2"><%=rb.getString("saludPJ")%>:
                            <%=saludPJ%></div>

                        <%-- Fila 3 --%>
                        <%
                        if (filasGrid.get(4).equals("")) {
                            nombreAtacante = "";
                            nombreDefensor = "";
                        }
                        %>
                        <div class="m-2"><%=nombreAtacante + " " + rb.getString(filasGrid.get(4))%></div>

                        <div class="m-2"><%=rb.getString("saludPJ")%>:
                            <%=saludEnemigo%></div>

                        <%-- Fila 4 --%>
                        <%
                            if (rb.getString(filasGrid.get(5)).equals(rb.getString("exito"))) {
                        %>
                        <div
                            class="grid-position-2-5 m-2 text-success fw-bold bg-success-subtle fs-5"><%=rb.getString(filasGrid.get(5))%></div>
                        <%
                            } else if (rb.getString(filasGrid.get(5)).equals(rb.getString("fallo"))) {
                        %>
                        <div
                            class="grid-position-2-5 m-2 text-danger fw-bold bg-danger-subtle fs-5"><%=rb.getString(filasGrid.get(5))%></div>
                        <%
                            } else {
                        %>
                        <div class="grid-position-2-5 m-2"><%=rb.getString(filasGrid.get(5))%></div>
                        <%
                            }
                        %>

                        <%-- Fila 5 --%>
                        <%
                        if (filasGrid.get(7).equals("")) {
                            nombreAtacante = "";
                            nombreDefensor = "";
                        }
                        %>
                        <div class="grid-position-2-5 m-2"><%=nombreAtacante + " " + rb.getString(filasGrid.get(7))%></div>
                        <%-- Fila 6 --%>
                        <%
                        if (filasGrid.get(9).equals("")) {
                            nombreAtacante = "";
                            nombreDefensor = "";
                        }
                        %>
                        <div class="grid-position-2-5 m-2"><%=nombreAtacante + " " + rb.getString(filasGrid.get(9)) + filasGrid.get(10) + rb.getString(filasGrid.get(11))%></div>
                        <%-- Fila 7 --%>
                        <%
                        if (filasGrid.get(13).equals("")) {
                            nombreAtacante = "";
                            nombreDefensor = "";
                        }
                        %>
                        <div class="grid-position-2-5 m-2"><%=nombreDefensor + " " + rb.getString(filasGrid.get(13))%></div>

                        <%
                            if (numBotones == 1) {
                        %>
                        <div></div>
                        <input class="btn btn-secondary grid-position-2-5 m-2"
                            type="submit" id="btnUnico" name="submitPageBtn"
                            value="<%=rb.getString(nombresBotones.get(0))%>">
                        <div></div>
                        <%
                            } else if (numBotones == 4) {
                        %>
                        <input class="btn btn-secondary m-2" type="submit" id="btnOpcion1"
                            name="submitPageBtn"
                            value="<%=rb.getString(nombresBotones.get(0))%>"> <input
                            class="btn btn-secondary m-2" type="submit" id="btnOpcion2"
                            name="submitPageBtn"
                            value="<%=rb.getString(nombresBotones.get(1))%>">

                        <div></div>

                        <input class="btn btn-secondary m-2" type="submit" id="btnOpcion3"
                            name="submitPageBtn"
                            value="<%=rb.getString(nombresBotones.get(2))%>"> <input
                            class="btn btn-secondary m-2" type="submit" id="btnOpcion4"
                            name="submitPageBtn"
                            value="<%=rb.getString(nombresBotones.get(3))%>">

                        <%
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