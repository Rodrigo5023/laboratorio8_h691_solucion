

<%@page import="java.util.ArrayList"%>
<%@page import="com.example.pruebalaboratorio1.beans.pelicula"%>
<%@page import="com.example.pruebalaboratorio1.beans.genero"%>
<%@page import="com.example.pruebalaboratorio1.beans.streaming"%>
<%@page import="java.text.NumberFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //Job job = (Job) request.getAttribute("job");
    ArrayList<pelicula> listaPeliculas = (ArrayList) request.getAttribute("listaPeliculas");
    ArrayList<genero> listaGeneros = (ArrayList) request.getAttribute("listaGeneros");
    ArrayList<streaming> listaStreaming = (ArrayList) request.getAttribute("listaStreaming");
    String searchTerm = request.getParameter("searchTerm");
    genero generoSeleccionado = (genero) request.getAttribute("generoSeleccionado");
    streaming streamingSeleccionado = (streaming) request.getAttribute("streamingSeleccionado");
    NumberFormat formatter = NumberFormat.getInstance();
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Películas</title>
</head>
<body>

<h1>Lista de Películas</h1>

<form action="listaPeliculas" method="POST">
    <div class="combobox-container">
        <label for="genero">Selecciona tus opciones:</label>
        <select id="genero" name="idGenero">
            <%
                if (generoSeleccionado.getIdGenero() != 0) {
            %>
                <option value="<%=generoSeleccionado.getIdGenero()%>"><%=generoSeleccionado.getNombre()%></option>
            <%
            } else {
            %>
                <option value="0" selected>Seleccione un Género</option>
            <%
                }
            %>
            <% for (genero gender: listaGeneros) {%>
                <option value="<%=gender.getIdGenero()%>"><%=gender.getNombre()%></option>
            <%}%>
        </select>

        <select id="streaming" name="idStreaming">
            <%
                if (streamingSeleccionado.getIdStreaming() != 0) {
            %>
            <option value="<%=streamingSeleccionado.getIdStreaming()%>"><%=streamingSeleccionado.getNombreServicio()%></option>
            <%
            } else {
            %>
            <option value="0" selected>Seleccione un Streaming</option>
            <%
                }
            %>

            <% for (streaming streaming: listaStreaming) {%>
            <option value="<%=streaming.getIdStreaming()%>"><%=streaming.getNombreServicio()%></option>

            <%}%>
        </select>
        <input type="hidden" name="action" value="filtrar">
        <button type="submit">Filtrar</button>
        <form action="listaPeliculas?action=listar" method="GET">
            <button type="submit">Limpiar</button>
        </form>
    </div>

</form>


<table border="1">
    <tr>

        <th>Titulo</th>
        <th>Director</th>
        <th>Ano Publicacion</th>
        <th>Rating</th>
        <th>BoxOffice</th>
        <th>Genero</th>
        <th>Duracion</th>
        <th>Streaming</th>
        <th>Premios Oscar</th>
        <th>Actores</th>
        <th>Borrar</th>

    </tr>
    <%
        for (pelicula movie : listaPeliculas) {
    %>
    <tr>

        <td><a href="viewPelicula?idPelicula=<%= movie.getIdPelicula() %>"><%=movie.getTitulo()%></a></td>
        <td><%=movie.getDirector()%></td>
        <td><%=movie.getAnoPublicacion()%></td>
        <td><%=movie.getRating()%>/10</td>
        <td>$<%=formatter.format(movie.getBoxOffice())%></td>
        <td><%=movie.getGenero().getNombre()%></td>
        <td><%=movie.getDuracion()%></td>
        <td><%=movie.getStreaming().getNombreServicio()%></td>
        <td><%=movie.isPremioOscar()%></td>
        <td><a href="listaActores?idPelicula=<%= movie.getIdPelicula() %>">Ver Actores</a></td>
        <%
            if (movie.isValidadorBorrado()) {
        %>
        <td> <a href="listaPeliculas?action=borrar&idPelicula=<%= movie.getIdPelicula() %>" class="button-link">Borrar</a></td>
        <%
            }
        %>
    </tr>

    <%
        }
    %>

</table>

</body>
</html>