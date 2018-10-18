package com.almacen.model.login;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

import com.almacen.conexion.Conexion;

public class LoginModel {

    public String usuario;
    public String contrasena;
    Conexion conexion = new Conexion();

    public boolean IniciarSesion() {
        boolean confirmarInicio = false;
        String consulta;
        try {
            consulta = "select passUser from users where nameUser='" + usuario + "';";
            ResultSet r = conexion.setConsulta(consulta);
            if (r.first()) {
                consulta = "select nameUser from users where passUser='" + contrasena + "';";
                ResultSet o = conexion.setConsulta(consulta);
                if (o.first()) {
                    confirmarInicio = true;
                } else {
                    JOptionPane.showMessageDialog(null, "informacion erronea\n");
                }
            } else {
                JOptionPane.showMessageDialog(null, "informacion erronea\n");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "informacion erronea\n" + e);
        }

        return confirmarInicio;
    }
}
