/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.UDistrital.Avanzada.taller2.Vista;

import edu.UDistrital.Avanzada.taller2.Modelo.MiniPigDTO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaPrincipal extends JFrame implements IVentanaPrincipal {

    public final PanelTablaMiniPigs panelTabla;
    public final PanelInsertarMiniPig panelInsertar;
    public final PanelMiniPigsSalir panelCards;

    private final CardLayout card;
    private final JPanel contenedor;

    public VentanaPrincipal() {
        setTitle("Mini Pigs");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1100, 720);
        setLocationRelativeTo(null);

        panelTabla = new PanelTablaMiniPigs();
        panelInsertar = new PanelInsertarMiniPig();
        panelCards = new PanelMiniPigsSalir();

        card = new CardLayout();
        contenedor = new JPanel(card);
        contenedor.add(panelTabla, "TABLA");
        contenedor.add(panelInsertar, "INSERTAR");
        contenedor.add(panelCards, "CARDS");

        setContentPane(contenedor);
        irATabla();
    }

    @Override public String getTextoBusqueda() { return panelTabla.txtBusqueda.getText(); }

    @Override
    public void mostrarMiniPigsEnTabla(List<MiniPigDTO> lista) {
        var m = panelTabla.modelo;
        m.setRowCount(0);
        for (MiniPigDTO dto : lista) {
            // columna Acciones se llena con texto placeholder; el controlador maneja clicks por columna
            m.addRow(new Object[]{dto.getCodigo(), dto.getNombre(), dto.getGenero(), dto.getIdMicrochip(), dto.getRaza(), dto.getColor(), "Editar | Eliminar"});
        }
    }

    @Override public void limpiar() {
        panelTabla.txtBusqueda.setText("");

        panelInsertar.txtCodigo.setText("");
        panelInsertar.txtNombre.setText("");
        panelInsertar.txtGenero.setText("");
        panelInsertar.txtIdMicrochip.setText("");
        panelInsertar.txtRaza.setText("");
        panelInsertar.txtColor.setText("");
        panelInsertar.txtPeso.setText("");
        panelInsertar.txtAltura.setText("");
        panelInsertar.txtC1.setText("");
        panelInsertar.txtC2.setText("");
        panelInsertar.txtFoto.setText("");
    }

    @Override public void mostrarMensaje(String msg) { JOptionPane.showMessageDialog(this, msg); }
    @Override public void mostrarError(String msg) { JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE); }

    @Override public void irATabla() { card.show(contenedor, "TABLA"); }
    @Override public void irAInsertar() { card.show(contenedor, "INSERTAR"); }
    @Override public void irACards() { card.show(contenedor, "CARDS"); }

    @Override public void cerrar() { dispose(); }

    // --- Edición no implementada aquí porque tu requisito para editar está en la tabla con botón editar.
    // Si quieres, creamos PanelEditar aparte; por ahora usas insertar y tabla.
}