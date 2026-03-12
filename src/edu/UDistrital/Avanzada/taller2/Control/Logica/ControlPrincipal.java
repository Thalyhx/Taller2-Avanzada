/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.UDistrital.Avanzada.taller2.Control.Logica;

import edu.UDistrital.Avanzada.taller2.Modelo.MiniPigDTO;
import edu.UDistrital.Avanzada.taller2.Modelo.PropertiesLoader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author natha
 */
public class ControlPrincipal {

    private ControlVista cVista;
    private GestorMiniPig cMiniPig;
    private PropertiesLoader loader;
    
    public ControlPrincipal() {
        this.loader = new PropertiesLoader();
        this.cVista = new ControlVista(this);
        this.cMiniPig = new GestorMiniPig(this);
    }
}
