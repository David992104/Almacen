
package com.almacen.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {

    public Connection conexion;
    public Statement sentencia;

    private static final Logger LOG = Logger.getLogger("com.almacen.conexion.Conexion");
    
    public void PrepararBaseDatos() {
        try {
            String contador = "com.mysql.jdbc.Driver";
            Class.forName(contador).newInstance();
            LOG.info("Controlador activo");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el controlador"+e);
        }
    }

    public void sentencia(){
         try {
            String DSN = "jdbc:mysql://localhost/almacen";
            String user = "root";
            String pasword = "root";
            conexion = DriverManager.getConnection(DSN, user, pasword);
            LOG.info("Conexion establecida");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la conexión"+e);
        }
        try {
            sentencia = conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            LOG.info("Consulta echa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el objeto"+e);
        }
    }
    
    public ResultSet setConsulta(String consulta) throws SQLException{
        sentencia();
        ResultSet salida = this.sentencia.executeQuery(consulta);
        LOG.info("Conexion cerrada");
        return salida;
    }
    
    
}
