package com.mycompany.cineclienteservidor;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Pago {

    private int id;
    private String nombreCliente;
    private String nombrePelicula;
    private double monto;
    private String tipo;
    private Date fecha;

    public Pago(String nombreCliente, String nombrePelicula, double monto, String tipo) {
        this.nombreCliente = nombreCliente;
        this.nombrePelicula = nombrePelicula;
        this.monto = monto;
        this.tipo = tipo;
        this.fecha = new java.sql.Date(System.currentTimeMillis());
    }

    public Pago(int id, String nombreCliente, String nombrePelicula, double monto, String tipo) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.nombrePelicula = nombrePelicula;
        this.monto = monto;
        this.tipo = tipo;
        this.fecha = new java.sql.Date(System.currentTimeMillis());
    }

    public Pago() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String mostrarPagos(ArrayList<Pago> pagos) {
        return "Nombre: " + this.getNombreCliente()
                + ", Pelicula: " + this.getNombrePelicula()
                + ", Monto: " + this.getMonto()
                + ", Metodo de Pago: " + this.getTipo()
                + ", Fecha: " + this.getFecha() + "\n";
    }

    public DefaultTableModel Mostrar() {

        Conexion conexion = new Conexion();
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Pelicula");
        modelo.addColumn("Monto");
        modelo.addColumn("Tipo de Pago");
        modelo.addColumn("Fecha");

        String datos[] = new String[6];
        Statement stmt;

        try {
            stmt = conexion.conectar().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pagos");

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

        String sql = "INSERT INTO pagos (nombreCliente, nombrePelicula, monto, tipoPago, fecha)"
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            CallableStatement cs = conexion.conectar().prepareCall(sql);

            cs.setString(1, this.nombreCliente);
            cs.setString(2, this.nombrePelicula);
            cs.setDouble(3, this.monto);
            cs.setString(4, this.tipo);
            cs.setString(5, this.fecha.toString());

            cs.execute();

            conexion.desconectar();

//            JOptionPane.showMessageDialog(null, "Registro guardado con exito.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el registro");
            System.out.println("Error: " + ex.toString());
        }

    }

    public void Modificar() {

        Conexion conexion = new Conexion();

        String sql = "UPDATE pagos SET"
                + " nombreCliente = ?,"
                + "nombrePelicula = ?,"
                + "Monto = ?,"
                + "tipoPago = ?"
                + " WHERE id = ?";

        try {
            CallableStatement cs = conexion.conectar().prepareCall(sql);

            cs.setString(1, this.nombreCliente);
            cs.setString(2, this.nombrePelicula);
            cs.setDouble(3, this.monto);
            cs.setString(4, this.tipo);
            cs.setInt(5, this.id);

            eliminarAux();

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
                "¿Desea eliminar el registro con el nombre: " + this.nombreCliente);

        if (opt == 0) {
            Conexion conexion = new Conexion();

            String sql = "DELETE FROM pagos WHERE id = ?";

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

    public void eliminarAux() {
        try {
            Conexion conexion = new Conexion();
            String sql = "DELETE FROM pagos WHERE id = ?";
            CallableStatement cs = conexion.conectar().prepareCall(sql);
            cs.setInt(1, FormCartelera.pagos.size()+1);
            cs.execute();
            conexion.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Pago.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
