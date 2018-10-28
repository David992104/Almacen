package com.almacen.main;

import com.almacen.view.login.LoginView;
import com.almacen.conexion.Conexion;
import com.almacen.view.principal.PrincipalView;
import javax.swing.JOptionPane;
import java.util.logging.Logger;

public class Almacen {

    private static final Logger LOG = Logger.getLogger("com.almacen.main.Almacen");

    public static void main(String[] args) {
        try {
            Conexion conexion = new Conexion();
            conexion.PrepararBaseDatos();
            /*LoginView login = new LoginView();
            login.setVisible(true);*/
            PrincipalView principal = new PrincipalView();
            principal.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de base de datos" + e);

        }

    }

}
