package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    String url = "jdbc:sqlite:C:/Users/Wainer Gaitán/Documents/CBSqlite/Reto_Cinco.db"; //ruta de acceso donde se almacena mi db
    Connection connect;
    
    public Connection getConexion(){
        try {
            connect = DriverManager.getConnection(url);
            if(connect!=null){ //si se conecto a db
                JOptionPane.showMessageDialog(null, "Conexion realizada.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage().toString());
        }
        return connect;
    }
    
}
