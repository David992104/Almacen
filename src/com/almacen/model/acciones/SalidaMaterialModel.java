/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almacen.model.acciones;

import com.almacen.conexion.Conexion;
import com.almacen.view.principal.PrincipalView;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author djoso
 */
public class SalidaMaterialModel {

    Logger LOG = Logger.getLogger("com.almacen.model.acciones.SalidaMaterialModel");
    Conexion conexion = new Conexion();

    public boolean tipoBusqueda;
    public String cosaBuscar;
    public int cantidadStock;
    public int cantidadSalida;
    public int idMaterial;

    public boolean regresarBoton() {
        boolean confirm = false;
        try {
            PrincipalView principal = new PrincipalView();
            principal.setVisible(true);
            confirm = true;
        } catch (Exception e) {

        }
        return confirm;
    }

    public boolean salirVentana() {
        boolean confirm = false;
        try {
            conexion.cerrarConexion();
            confirm = true;
        } catch (SQLException ex) {
            LOG.info("Eroro " + ex);
        }
        return confirm;
    }

    public String seleccionarTipoBusqueda() {
        String consulta = "";
        if (tipoBusqueda) {
            consulta = "select * from material where idMaterial = '";
        } else {
            consulta = "select * from material where nombre = '";
        }
        return consulta;
    }

    public String[] realizarConsulta() throws SQLException {
        String datos[] = new String[3];
        String consulta = seleccionarTipoBusqueda();
        consulta += cosaBuscar + "';";
        try {
            ResultSet buscar = conexion.setQuery(consulta);
            if ((buscar.first())) {
                datos[0] = buscar.getString("idMaterial");
                datos[1] = buscar.getString("nombre");
                datos[2] = buscar.getString("enStock");
                LOG.info("Consulta de salida echa");
            } else {
                JOptionPane.showMessageDialog(null, "Error no se encnotro nada con " + cosaBuscar, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            LOG.info("Error de consulta " + e);
        }
        return datos;
    }

    public boolean comprobarDisponibilidad() {
        boolean confirm = true;
        if (cantidadStock >= cantidadSalida) {
            confirm = true;
        } else {
            confirm = false;
        }
        return confirm;
    }

    public boolean realizarSalida() {
        boolean confirm = false;
        String consulta = "";
        try {
            consulta = "update material set ";
            consulta += "enStock = '" + (cantidadStock - cantidadSalida) + "' ";
            consulta += "where idMaterial = '" + idMaterial + "';";
            conexion.setUpdate(consulta);
            LOG.info("Cantidad modificada de material con id" + idMaterial);
            confirm = true;
        } catch (SQLException e) {
            LOG.info("No se pudo actualizar la cantidad");
        }
        return confirm;
    }
}
