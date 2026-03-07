/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.UDistrital.Avanzada.taller2.Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *<p>
 * Esta clase PropertiesLoader es un Cargador de archivos .properties
 * Responsabilidad única: cargar
 *</p>
 * 
 * @author nath
 * @version 1.0
 * @since 2026-03-06
 * 
 */

public class PropertiesLoader {
    
    
    /**
     * Lee el archivo indicado y carga sus propiedades.
     *
     * @param archivo archivo {@code .properties} seleccionado
     * @return propiedades cargadas
     * @throws IOException si ocurre un error de lectura
     * @throws IllegalArgumentException si el archivo es {@code null} o inválido
     */
    
    public Properties cargar(File archivo) throws IOException {
        if (archivo == null) {
            throw new IllegalArgumentException("El archivo no puede ser null.");
        }
        if (!archivo.exists() || !archivo.isFile()) {
            throw new IllegalArgumentException("Archivo inválido: " + archivo.getAbsolutePath());
        }

        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(archivo)) {
            props.load(fis);
        }
        return props;
    }
}