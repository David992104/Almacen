/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almacen.model.principal;

import com.almacen.view.acciones.salidaMaterialView;
import javax.swing.JOptionPane;

/**
 *
 * @author djoso
 */
public class principalModel {

    public boolean ejecutarSalidaMat() {
        boolean confirm = false;
        try {
            salidaMaterialView salida = new salidaMaterialView();
            salida.setVisible(true);
            confirm = true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se puede acceder");
        }
        return confirm;
    }

}
