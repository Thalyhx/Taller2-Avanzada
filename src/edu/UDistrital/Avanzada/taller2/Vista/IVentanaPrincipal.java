/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.UDistrital.Avanzada.taller2.Vista;

import edu.UDistrital.Avanzada.taller2.Modelo.MiniPigDTO;
import java.util.List;

public interface IVentanaPrincipal {
    String getTextoBusqueda();
    void mostrarMiniPigsEnTabla(List<MiniPigDTO> lista);

    void limpiar();
    void mostrarMensaje(String msg);
    void mostrarError(String msg);

    void irATabla();
    void irAInsertar();
    void irACards();

    void cerrar();
}