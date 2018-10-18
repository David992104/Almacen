/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almacen.model.principal;

import com.almacen.view.acciones.EntradaMaterialView;
import com.almacen.view.acciones.NuevoUsuarioView;
import com.almacen.view.acciones.SalidaMaterialView;
import com.almacen.view.login.LoginView;
import javax.swing.JOptionPane;

/**
 *
 * @author djoso
 */
public class PrincipalModel {

    public boolean ejecutarSalidaMat() {
        boolean confirm = false;
        try {
            SalidaMaterialView salida = new SalidaMaterialView();
            salida.setVisible(true);
            confirm = true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se puede acceder");
        }
        return confirm;
    }
    
    public boolean cerrarSesion(){
        boolean confirm = false;
        try {
            LoginView login = new LoginView();
            login.setVisible(true);
            confirm = true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se puede acceder");
        }
        return confirm;
    }
    
    public boolean ejecutarNuevo(){
        boolean confirm = false;
        try {
            NuevoUsuarioView nuevo = new NuevoUsuarioView();
            nuevo.setVisible(true);
            confirm = true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se puede acceder");
        }
        return confirm;
    }

    public boolean ejecutarEntradaMat(){
         boolean confirm = false;
        try {
            EntradaMaterialView entrada = new EntradaMaterialView();
            entrada.setVisible(true);
            confirm = true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se puede acceder");
        }
        return confirm;
    }
}
