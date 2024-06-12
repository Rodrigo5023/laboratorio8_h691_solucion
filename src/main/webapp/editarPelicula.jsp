<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Creacion Pelicula</title>
</head>
<body>
<h2>Formulario de Creacion de Pelicula</h2>
<form action="/enviar_datos" method="post">


    <label for="titulo">Titulo:</label><br>
    <input type="text" id="titulo" name="titulo" required><br>

    <label for="director">Director:</label><br>
    <input type="text" id="director" name="director" required><br>

    <label for="anoPubicacion">Ano Publicacion:</label><br>
    <input type="text" id="anoPubicacion" name="anoPubicacion" required><br>

    <label for="rating">Rating:</label><br>
    <input type="text" id="rating" name="rating" required><br>

    <label for="boxOffice">BoxOffice:</label><br>
    <input type="text" id="boxOffice" name="boxOffice" required><br>

    <label for="duracion">Duracion:</label><br>
    <input type="text" id="duracion" name="duracion" required><br>

    <label for="genero">Genero:</label><br>
    <input type="text" id="genero" name="genero" required><br>

    <label for="streaming">Streaming:</label><br>
    <input type="text" id="streaming" name="streaming" required><br>

    <label for="premioOscar">PremioOscar:</label><br>
    <input type="text" id="premioOscar" name="premioOscar" required><br>



    <input type="submit" value="Enviar">
</form>
</body>
</html>
