package com.almacen.model.acciones;

import com.almacen.conexion.Conexion;
import com.almacen.view.principal.PrincipalView;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class EntradaMaterialModel {

    private static final Logger LOG = Logger.getLogger("com.almacen.model.acciones.EntradaMaerialModel");
    Conexion conexion = new Conexion();
    
    public String cosaBuscar;
    public int cantidadStock;
    public int cantidadEntrada;
    public int idMaterial;
    public boolean tipoBusqueda = true;

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
            LOG.log(Level.INFO, "Error {0}", ex);
        }
        return confirm;
    }

    public String seleccionarTipoBusqueda() {
        String consulta;
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
        } catch (SQLException e) {
            LOG.log(Level.INFO, "Error de consulta {0}", e);
        }
        return datos;
    }

    public boolean realizarEntrada() {
        boolean confirm = false;
        String consulta;
        try {
            consulta = "update material set ";
            consulta += "enStock = '" + (cantidadStock + cantidadEntrada) + "' ";
            consulta += "where idMaterial = '" + idMaterial + "';";
            conexion.setUpdate(consulta);
            LOG.log(Level.INFO, "Cantidad modificada de material con id{0}", idMaterial);
            confirm = true;
            guardarEntradaHis();
        } catch (SQLException e) {
            LOG.log(Level.INFO, "No se pudo actualizar la cantidad{0}", e);
        }
        return confirm;
    }

    public void guardarEntradaHis() {
        Date date = new Date();
        String fecha = (date.getYear() + 1900) + "-" + date.getMonth() + "-" + date.getDay()
                + "-" + date.getHours() + ":" + date.getMinutes();
        String user = "admin";
        String update = "Insert into entradasMaterial (`idMaterial`, `fecha`, "
                + "`usuario`, `cantidadAnterior`, `cantidadEntrada`) values (";
        try {
            update += "'" + idMaterial + "',";
            update += "'" + fecha + "',";
            update += "'" + user + "',";
            update += "'" + cantidadStock + "',";
            update += "'" + cantidadEntrada + "');";
            conexion.setUpdate(update);
            LOG.info("Se añadio a historial");
        } catch (SQLException e) {
            LOG.log(Level.INFO, "No se puedo estraer infromacion", e);
        }
    }
}
