package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.pelicula;

import java.sql.*;
import java.util.ArrayList;

public class detallesDao extends baseDao{

    public pelicula obtenerPelicula(int idPelicula) {

        pelicula movie = new pelicula();


        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "SELECT A.*, B.NOMBRE FROM \n" +
                    "(SELECT * FROM PELICULA WHERE IDPELICULA = \n" +
                     idPelicula +
                    ") AS A \n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM GENERO) AS B\n" +
                    "ON A.IDGENERO = B.IDGENERO";
            // hacer el join con el genero y pedir que se haga por rating desc
            // agregar buscador

            ResultSet rs = stmt.executeQuery(sql);


            while (rs.next()) {

                genero genero = new genero();

                int id = rs.getInt(1);
                movie.setIdPelicula(id);
                String titulo = rs.getString("titulo");
                movie.setTitulo(titulo);
                String director = rs.getString("director");
                movie.setDirector(director);
                int anoPublicacion = rs.getInt("anoPublicacion");
                movie.setAnoPublicacion(anoPublicacion);
                double rating = rs.getDouble("rating");
                movie.setRating(rating);
                double boxOffice = rs.getDouble("boxOffice");
                movie.setBoxOffice(boxOffice);
                int idGenero = rs.getInt("idGenero");
                String nombregenero = rs.getString("nombre");
                genero.setIdGenero(idGenero);
                genero.setNombre(nombregenero);
                movie.setGenero(genero);



            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return movie;
    }
}
