/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.UDistrital.Avanzada.taller2.Vista;

import javax.swing.*;
import java.awt.*;

public class PanelEditarMiniPig extends JPanel {

    public JTextField txtCodigo;
    public JTextField txtNombre;
    public JTextField txtGenero;
    public JTextField txtIdMicrochip;
    public JTextField txtRaza;
    public JTextField txtColor;
    public JTextField txtPeso;
    public JTextField txtAltura;
    public JTextField txtC1;
    public JTextField txtC2;
    public JTextField txtFoto;

    public JButton btnGuardar;
    public JButton btnVolver;

    public PanelEditarMiniPig() {
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Editando MiniPig", SwingConstants.CENTER);
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 26f));
        add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(11, 2, 10, 8));

        txtCodigo = new JTextField();
        txtNombre = new JTextField();
        txtGenero = new JTextField();
        txtIdMicrochip = new JTextField();
        txtRaza = new JTextField();
        txtColor = new JTextField();
        txtPeso = new JTextField();
        txtAltura = new JTextField();
        txtC1 = new JTextField();
        txtC2 = new JTextField();
        txtFoto = new JTextField();

        form.add(new JLabel("Código:")); form.add(txtCodigo);
        form.add(new JLabel("Nombre:")); form.add(txtNombre);
        form.add(new JLabel("Género:")); form.add(txtGenero);
        form.add(new JLabel("IdMicrochip:")); form.add(txtIdMicrochip);
        form.add(new JLabel("Raza:")); form.add(txtRaza);
        form.add(new JLabel("Color:")); form.add(txtColor);
        form.add(new JLabel("Peso:")); form.add(txtPeso);
        form.add(new JLabel("Altura:")); form.add(txtAltura);
        form.add(new JLabel("Característica 1:")); form.add(txtC1);
        form.add(new JLabel("Característica 2:")); form.add(txtC2);
        form.add(new JLabel("Url Foto:")); form.add(txtFoto);

        add(form, BorderLayout.CENTER);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        btnGuardar = new JButton("Guardar cambios");
        btnVolver = new JButton("Volver");
        acciones.add(btnGuardar);
        acciones.add(btnVolver);
        add(acciones, BorderLayout.SOUTH);
    }
}