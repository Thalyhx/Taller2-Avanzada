/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.UDistrital.Avanzada.taller2.Vista;

import javax.swing.*;
import java.awt.*;

public class PanelCardMiniPig extends JPanel {

    public JLabel lblTitulo;
    public JLabel lblCodigo;
    public JLabel lblNombre;
    public JLabel lblGenero;
    public JLabel lblMicrochip;
    public JLabel lblRaza;
    public JLabel lblColor;
    public JLabel lblPeso;
    public JLabel lblAltura;
    public JLabel lblC1;
    public JLabel lblC2;

    public PanelCardMiniPig() {
        setPreferredSize(new Dimension(260, 380));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        lblTitulo = new JLabel("Cerdito", SwingConstants.CENTER);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 18f));

        add(lblTitulo);
        add(Box.createVerticalStrut(8));

        lblCodigo = new JLabel();
        lblNombre = new JLabel();
        lblGenero = new JLabel();
        lblMicrochip = new JLabel();
        lblRaza = new JLabel();
        lblColor = new JLabel();
        lblPeso = new JLabel();
        lblAltura = new JLabel();
        lblC1 = new JLabel();
        lblC2 = new JLabel();

        add(lblCodigo);
        add(lblNombre);
        add(lblGenero);
        add(lblMicrochip);
        add(lblRaza);
        add(lblColor);
        add(lblPeso);
        add(lblAltura);
        add(lblC1);
        add(lblC2);
    }
}