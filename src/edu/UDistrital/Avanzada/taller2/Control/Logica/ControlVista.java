/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.UDistrital.Avanzada.taller2.Control.Logica;
import edu.UDistrital.Avanzada.taller2.Vista.VentanaPrincipal;

/**
 * Controlador de la ventana principal:
 * - Crea la VentanaPrincipal (Vista)
 * - Registra listeners
 * - Se comunica con ControlPrincipal (fachada) para operaciones
 */
public class ControlVista {

    private final ControlPrincipal cPrincipal;
    private VentanaPrincipal ventana;

    public ControlVista(ControlPrincipal cPrincipal) {
        this.cPrincipal = cPrincipal;
    }

    /**
     * Crea y muestra la ventana. Debe ejecutarse en el EDT.
     */
    public void iniciarUI() {
        ventana = new VentanaPrincipal();

        // aquí luego conectamos listeners:
        // ventana.panelTabla.btnInsertar.addActionListener(...)

        ventana.setVisible(true);
    }

    public VentanaPrincipal getVentana() {
        return ventana;
    }
}