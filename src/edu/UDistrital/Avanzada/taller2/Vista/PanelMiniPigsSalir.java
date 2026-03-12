/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.UDistrital.Avanzada.taller2.Vista;

import javax.swing.*;
import java.awt.*;

public class PanelMiniPigsSalir extends JPanel {

    public JButton btnTerminar; // “Terminar Ejecución”
    public JPanel contenedorCards; // aquí se agregan tarjetas

    public PanelMiniPigsSalir() {
        setLayout(new BorderLayout(10, 10));

        contenedorCards = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
        add(new JScrollPane(contenedorCards), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        btnTerminar = new JButton("Terminar Ejecución");
        bottom.add(btnTerminar);
        add(bottom, BorderLayout.SOUTH);
    }
}