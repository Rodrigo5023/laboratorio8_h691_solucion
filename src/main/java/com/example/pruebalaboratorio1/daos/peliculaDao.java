package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.pelicula;
import com.example.pruebalaboratorio1.beans.streaming;

import java.sql.*;
import java.util.ArrayList;

public class peliculaDao extends baseDao{

    public ArrayList<pelicula> listarPeliculas() {

        ArrayList<pelicula> listaPeliculas = new ArrayList<>();


        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "SELECT A.*, B.NOMBRE, C.NOMBRESERVICIO FROM  \n" +
                    "(SELECT * FROM PELICULA ) AS A \n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM GENERO) AS B\n" +
                    "ON A.IDGENERO = B.IDGENERO\n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM STREAMING) AS C\n" +
                    "ON A.IDSTREAMING = C.IDSTREAMING\n" +
                    "ORDER BY RATING DESC , BOXOFFICE DESC";


            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                pelicula movie = new pelicula();
                genero genero = new genero();
                streaming streaming = new streaming();
                int idPelicula = rs.getInt(1);
                movie.setIdPelicula(idPelicula);
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
                String duracion = rs.getString("duracion");
                movie.setDuracion(duracion);
                int idStreaming = rs.getInt("idStreaming");
                String nombreServicio = rs.getString("nombreServicio");
                streaming.setIdStreaming(idStreaming);
                streaming.setNombreServicio(nombreServicio);
                movie.setStreaming(streaming);
                boolean oscar = rs.getBoolean("premioOscar");
                movie.setPremioOscar(oscar);

                boolean validador= validarBorrado(movie);
                movie.setValidadorBorrado(validador);

                listaPeliculas.add(movie);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPeliculas;
    }

    public ArrayList<pelicula> listarPeliculasFiltradas(int idGenero, int idStreaming) {

        ArrayList<pelicula> listaPeliculasFiltradas= new ArrayList<>();

        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "SELECT A.*, B.NOMBRE, C.NOMBRESERVICIO FROM  \n" +
                    "(SELECT * FROM PELICULA WHERE IDGENERO = " + idGenero + " AND IDSTREAMING = " + idStreaming + ") AS A \n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM GENERO) AS B\n" +
                    "ON A.IDGENERO = B.IDGENERO\n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM STREAMING) AS C\n" +
                    "ON A.IDSTREAMING = C.IDSTREAMING\n" +
                    "ORDER BY RATING DESC , BOXOFFICE DESC";


            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                pelicula movie = new pelicula();
                genero genero = new genero();
                streaming streaming = new streaming();
                int idPelicula = rs.getInt(1);
                movie.setIdPelicula(idPelicula);
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
                int idGeneroMovie = rs.getInt("idGenero");
                String nombregenero = rs.getString("nombre");
                genero.setIdGenero(idGeneroMovie);
                genero.setNombre(nombregenero);
                movie.setGenero(genero);
                String duracion = rs.getString("duracion");
                movie.setDuracion(duracion);
                int idStreamingMovie = rs.getInt("idStreaming");
                String nombreServicio = rs.getString("nombreServicio");
                streaming.setIdStreaming(idStreamingMovie);
                streaming.setNombreServicio(nombreServicio);
                movie.setStreaming(streaming);
                boolean oscar = rs.getBoolean("premioOscar");
                movie.setPremioOscar(oscar);

                boolean validador= validarBorrado(movie);
                movie.setValidadorBorrado(validador);

                listaPeliculasFiltradas.add(movie);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPeliculasFiltradas;
    }

    public void editarPelicula(int idPelicula, String titulo, String director, int anoPublicacion, double rating, double boxOffice){
        try {
            String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";
            String username = "root";
            String password = "root";

            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password);) {
                String sql = "UPDATE PELICULA SET titulo = ?, director = ?, anoPublicacion = ? ," +
                        "rating = ?, boxOffice = ? WHERE IDPELICULA = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, titulo);
                    pstmt.setString(2, director);
                    pstmt.setInt(3, anoPublicacion);
                    pstmt.setDouble(4, rating);
                    pstmt.setDouble(5, boxOffice);
                    pstmt.setInt(6, idPelicula);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    public void borrarPelicula(int idPelicula) {

        try (Connection conn = this.getConnection();) {
            String sql1 = "DELETE FROM PROTAGONISTAS WHERE idPelicula = " + idPelicula;
            String sql2 = "DELETE FROM PELICULA WHERE idPelicula = " + idPelicula;
            try (PreparedStatement pstmt = conn.prepareStatement(sql1)) {
                pstmt.executeUpdate();
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql2)) {
                pstmt.executeUpdate();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
