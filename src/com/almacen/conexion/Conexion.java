/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almacen.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author djoso
 */
public class Conexion {

    Connection conexion;
    Statement sentencia;

    public void PrepararBaseDatos() {
        try {
            String contador = "com.mysql.jdbc.Driver";
            Class.forName(contador).newInstance();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el controlador");
        }
        try {
            String DSN = "jdbc:mysql://localhost/almacen";
            String user = "root";
            String pasword = "root";
            conexion = DriverManager.getConnection(DSN, user, pasword);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la conexión");
        }
        try {
            sentencia = conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el objeto");
        }
    }

}
