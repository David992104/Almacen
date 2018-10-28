package com.almacen.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

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
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el controlador" + e);
        }
    }
    
    public void sentencia() {
        try {
            String DSN = "jdbc:mysql://localhost/almacen";
            String user = "root";
            String pasword = "root";
            conexion = DriverManager.getConnection(DSN, user, pasword);
            LOG.info("Conexion establecida");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la conexión" + e);
        }
        try {
            sentencia = conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            LOG.info("Consulta echa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el objeto" + e);
            LOG.log(Level.INFO, "Error al cargar el objeto{0}", e);
        }
    }
    
    public boolean cerrarConexion(){
        boolean confirm = false;
        try {
            conexion.close();
            LOG.info("Conexion cerrada");
            confirm = false;
        } catch (SQLException e) {
            LOG.log(Level.INFO, "Error{0}", e);        
        }
        if (!confirm) {
            System.exit(0);
        }else{
            LOG.info("Error al cerrar sesion");        
        }
        return confirm;
    }
    
    public ResultSet setQuery(String consulta) throws SQLException {
        sentencia();
        ResultSet login = this.sentencia.executeQuery(consulta);
        LOG.info("Conexion cerrada");
        return login;
    }
    
    public void setUpdate(String consulta) throws SQLException {
        sentencia();
        this.sentencia.executeUpdate(consulta);
        LOG.info("Conexion cerrada");
    }
    
}
