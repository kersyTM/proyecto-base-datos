package com.mycompany.cineclienteservidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    String url = "jdbc:mysql://localhost:3306/";
    String db = "cine";
    String user = "root";
    String password = "password";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection jdbc;
    
   public Connection conectar(){
        try {
            Class.forName(driver);
            jdbc = DriverManager.getConnection(url + db, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jdbc;
   }
   
   public void desconectar(){
        try {
            System.out.println("Conexion finalizada");
            jdbc.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
