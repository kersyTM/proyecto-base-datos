package com.mycompany.cineclienteservidor;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Pelicula {

    private int id;
    private String nombre;
    private int duracion;
    private String genero;
    private String actoresPrincipales;
    private double costoEntrada;

    public Pelicula() {
    }
    

    public Pelicula(int id, String nombre, int duracion, String genero, String actoresPrincipales, double costoEntrada) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.genero = genero;
        this.actoresPrincipales = actoresPrincipales;
        this.costoEntrada = costoEntrada;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getActoresPrincipales() {
        return actoresPrincipales;
    }

    public void setActoresPrincipales(String actoresPrincipales) {
        this.actoresPrincipales = actoresPrincipales;
    }

    public double getCostoEntrada() {
        return costoEntrada;
    }

    public void setCostoEntrada(double costoEntrada) {
        this.costoEntrada = costoEntrada;
    }

    public DefaultTableModel Mostrar() {

        Conexion conexion = new Conexion();
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Duracion");
        modelo.addColumn("Genero");
        modelo.addColumn("Actores Principales");
        modelo.addColumn("Costo por Entrada");

        String datos[] = new String[6];
        Statement stmt;

        try {
            stmt = conexion.conectar().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Cartelera");

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);

                modelo.addRow(datos);
            }

            conexion.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros");
            System.out.println("Error: " + ex.toString());
        }

        return modelo;
    }

    public void Insertar() {

        Conexion conexion = new Conexion();

        String sql = "INSERT INTO Cartelera (nombre, duracion, genero, actoresPrincipales, costoEntrada)"
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            CallableStatement cs = conexion.conectar().prepareCall(sql);

            cs.setString(1, this.nombre);
            cs.setInt(2, this.duracion);
            cs.setString(3, this.genero);
            cs.setString(4, this.actoresPrincipales);
            cs.setDouble(5, this.costoEntrada);
            
            cs.execute();

            conexion.desconectar();

            JOptionPane.showMessageDialog(null, "Registro guardado con exito.");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el registro");
            System.out.println("Error: " + ex.toString());
        }

    }

    public void Modificar() {

        Conexion conexion = new Conexion();

        String sql = "UPDATE Cartelera SET"
                + " nombre = ?,"
                + "duracion = ?,"
                + "genero = ?,"
                + "actoresPrincipales = ?,"
                + "costoEntrada = ?"
                + " WHERE id = ?";

        try {
            CallableStatement cs = conexion.conectar().prepareCall(sql);

            cs.setString(1, this.nombre);
            cs.setInt(2, this.duracion);
            cs.setString(3, this.genero);
            cs.setString(4, this.actoresPrincipales);
            cs.setDouble(5, this.costoEntrada);
            cs.setInt(6, this.id);

            cs.execute();

            conexion.desconectar();

            JOptionPane.showMessageDialog(null, "Registro actualizado con exito.");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro");
            System.out.println("Error: " + ex.toString());
        }
    }

    public void Eliminar() {

        int opt = JOptionPane.showConfirmDialog(null,
                "¿Desea eliminar el registro con el nombre: " + this.nombre);

        if (opt == 0) {
            Conexion conexion = new Conexion();

            String sql = "DELETE FROM Cartelera WHERE id = ?";

            try {
                CallableStatement cs = conexion.conectar().prepareCall(sql);

                cs.setInt(1, this.id);

                cs.execute();

                conexion.desconectar();

                JOptionPane.showMessageDialog(null, "Registro eliminado con exito.");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro");
                System.out.println("Error: " + ex.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Eliminacion cancelada con exito.");
        }

    }

}
