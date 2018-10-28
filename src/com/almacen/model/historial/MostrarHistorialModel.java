/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almacen.model.historial;

import com.almacen.conexion.Conexion;
import com.almacen.view.principal.PrincipalView;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author djoso
 */
public class MostrarHistorialModel {

    Conexion conexion = new Conexion();

    public int historialDe = 0;

    public DefaultTableModel confirmar() {
        if (historialDe == 0) {
            return consultaSalida();
        } else {
            return consultaEntrada();
        }
    }

    public DefaultTableModel consultaSalida() {
        String consulta = "select * from salidasMaterial order by idsalida desc";

        DefaultTableModel modelo = new DefaultTableModel();

        try {
            ResultSet datos = conexion.setQuery(consulta);
            datos.beforeFirst();
            //Obtener Titulos de la base de datos
            ResultSetMetaData metaDatos = datos.getMetaData();
            int columnas = metaDatos.getColumnCount();
            Object[] titulos = new Object[columnas];
            for (int i = 0; i < titulos.length; i++) {
                titulos[i] = metaDatos.getColumnLabel(i + 1);
            }
            modelo.setColumnIdentifiers(titulos);

            //Estraer informacion de la tabla
            while (datos.next()) {
                Object[] informacion = new Object[titulos.length];
                for (int i = 0; i < informacion.length; i++) {
                    informacion[i] = datos.getObject(i + 1);
                }
                modelo.addRow(informacion);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return modelo;
    }

    public DefaultTableModel consultaEntrada() {
        String consulta = "select * from entradasMaterial order by identrada desc";

        DefaultTableModel modelo = new DefaultTableModel();

        try {
            ResultSet datos = conexion.setQuery(consulta);
            datos.beforeFirst();
            //Obtener Titulos de la base de datos
            ResultSetMetaData metaDatos = datos.getMetaData();
            int columnas = metaDatos.getColumnCount();
            Object[] titulos = new Object[columnas];
            for (int i = 0; i < titulos.length; i++) {
                titulos[i] = metaDatos.getColumnLabel(i + 1);
            }
            modelo.setColumnIdentifiers(titulos);

            //Estraer informacion de la tabla
            while (datos.next()) {
                Object[] informacion = new Object[titulos.length];
                for (int i = 0; i < informacion.length; i++) {
                    informacion[i] = datos.getObject(i + 1);
                }
                modelo.addRow(informacion);
            }
        } catch (SQLException e) {

        }
        return modelo;
    }

    public boolean regresarPrincipal() {
        boolean confirm = false;
        try {
            PrincipalView regresar = new PrincipalView();
            regresar.setVisible(true);
            confirm = true;
        } catch (Exception e) {

        }
        return confirm;
    }
}
