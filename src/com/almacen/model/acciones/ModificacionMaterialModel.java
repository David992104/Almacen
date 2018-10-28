package com.almacen.model.acciones;

import com.almacen.conexion.Conexion;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ModificacionMaterialModel {

    Logger LOG = Logger.getLogger("cam.almacen.model.acciones.ModificacionMaterialModel");
    Conexion conexion = new Conexion();

    public int tipoBusqueda;
    public String datoPrimitivo;

    public String[] seleccionarTipoBus() {
        if (tipoBusqueda == 1) {
            return realizarBusquedaId(datoPrimitivo);
        } else {
            if (tipoBusqueda == 2) {
                return realizarBusquedaName(datoPrimitivo);
            }
        }
        return null;
    }

    public String[] realizarBusquedaId(String idmaterial) {
        String consulta = "select * from materiales where idmaterial='" + idmaterial + "';";
        String[] datos = null;
        try {
            ResultSet infor = conexion.setQuery(consulta);
            ResultSetMetaData metaDatos = infor.getMetaData();
            datos = new String[metaDatos.getColumnCount()];
            if (infor.first()) {
                for (int i = 1; i <= datos.length; i++) {
                    datos[i - 1] = infor.getString(i);
                }
            }
            LOG.info("Consulta echa en materiales");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se encontro el recurso");
        }
        return datos;
    }

    public String[] realizarBusquedaName(String nombreMaterial) {
        String consulta = "select * from materiales where nombre='" + nombreMaterial + "';";
        String[] datos = null;
        try {
            ResultSet infor = conexion.setQuery(consulta);
            ResultSetMetaData metaDatos = infor.getMetaData();
            datos = new String[metaDatos.getColumnCount()];
            if (infor.first()) {
                for (int i = 1; i <= datos.length; i++) {
                    datos[i - 1] = infor.getString(i);
                }
            }
            LOG.info("Consulta echa en materiales");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se encontro el recurso");
        }
        return datos;
    }

    public boolean cerrarApp() {
        return conexion.cerrarConexion();
    }
}
