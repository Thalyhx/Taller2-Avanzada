/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.UDistrital.Avanzada.taller2.Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelTablaMiniPigs extends JPanel {

    public JButton btnLimpiar;
    public JButton btnBuscar;
    public JButton btnInsertar;
    public JButton btnSalir;

    public JComboBox<String> comboCerditos;
    public JTextField txtBusqueda;

    public JTable tabla;
    public DefaultTableModel modelo;

    public PanelTablaMiniPigs() {
    setLayout(new BorderLayout(10, 10));

    JLabel titulo = new JLabel("Mini Pigs", SwingConstants.CENTER);
    titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 44f));
    add(titulo, BorderLayout.NORTH);

    JPanel acciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
    btnLimpiar = new JButton("Limpiar");
    comboCerditos = new JComboBox<>();
    comboCerditos.setPrototypeDisplayValue("0000 - Nombre muy largo");
    btnBuscar = new JButton("Buscar");
    btnInsertar = new JButton("Insertar");
    acciones.add(btnLimpiar);
    acciones.add(comboCerditos);
    acciones.add(btnBuscar);
    acciones.add(btnInsertar);

    JPanel buscador = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    txtBusqueda = new JTextField(30);
    buscador.add(new JLabel("Dato:"));
    buscador.add(txtBusqueda);

    JPanel top = new JPanel(new BorderLayout());
    top.add(acciones, BorderLayout.NORTH);
    top.add(buscador, BorderLayout.SOUTH);

    // top va debajo del título, así que lo ponemos en WEST/EAST? No.
    // Mejor: crea un panel general para norte:
    JPanel north = new JPanel(new BorderLayout());
    north.add(titulo, BorderLayout.NORTH);
    north.add(top, BorderLayout.SOUTH);

    removeAll();
    add(north, BorderLayout.NORTH);

    String[] cols = {"Código", "Nombre", "Género", "Id microchip", "Raza", "Color", "Editar", "Eliminar"};
    modelo = new DefaultTableModel(cols, 0) {
        @Override public boolean isCellEditable(int row, int col) { return false; }
    };
    tabla = new JTable(modelo);
    add(new JScrollPane(tabla), BorderLayout.CENTER);

    JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    btnSalir = new JButton("Salir");
    bottom.add(btnSalir);
    add(bottom, BorderLayout.SOUTH);
}
}