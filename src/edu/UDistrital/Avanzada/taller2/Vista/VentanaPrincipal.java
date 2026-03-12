/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.UDistrital.Avanzada.taller2.Vista;

import edu.UDistrital.Avanzada.taller2.Control.Logica.ControlPrincipal;
import java.awt.Component;
import java.io.File;
import javax.swing.*;

/**
 *
 * @author natha
 */
public class VentanaPrincipal {
    private ControlPrincipal cPrincipal;
    
    public File seleccionarArchivo(Component parent) {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        fc.setDialogTitle("Selecciona archivo .properties");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = fc.showOpenDialog(parent);
        return (res == JFileChooser.APPROVE_OPTION) ? fc.getSelectedFile() : null;
    }
}
