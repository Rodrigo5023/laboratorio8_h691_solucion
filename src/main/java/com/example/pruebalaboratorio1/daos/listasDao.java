package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.streaming;
import com.example.pruebalaboratorio1.beans.pelicula;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class listasDao extends baseDao{

    public ArrayList<genero> listarGeneros() {

        ArrayList<genero> listaGeneros = new ArrayList<>();


        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "select idgenero, nombre from genero";


            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                genero generobean = new genero();
                int idgenero = rs.getInt(1);
                generobean.setIdGenero(idgenero);
                String nombre = rs.getString("nombre");
                generobean.setNombre(nombre);

                listaGeneros.add(generobean);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaGeneros;
    }

    public ArrayList<streaming> listarStraming() {

        ArrayList<streaming> listaStreaming = new ArrayList<>();


        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "select idstreaming, nombreServicio from streaming";


            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                streaming streamingbean = new streaming();
                int idStreaming = rs.getInt(1);
                streamingbean.setIdStreaming(idStreaming);
                String nombreServicio = rs.getString("nombreServicio");
                streamingbean.setNombreServicio(nombreServicio);

                listaStreaming.add(streamingbean);



            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaStreaming;
    }


}
