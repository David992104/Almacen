package com.almacen.main;

import com.almacen.view.login.LoginView;
import com.almacen.conexion.Conexion;
import javax.swing.JOptionPane;

/**
 *
 * @author djoso
 */
public class Almacen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Conexion conexion = new Conexion();
            conexion.PrepararBaseDatos();
            LoginView login = new LoginView();
            login.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de base de datos");
        }

    }

}
