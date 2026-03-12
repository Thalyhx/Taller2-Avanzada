/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import java.util.Comparator;
import java.util.Properties;
import javax.swing.JFileChooser;

/**
 * Control principal 
 *
 * @author natha
 */
public class ControlPrincipal {

    private final ControlVista cVista;
    private final GestorMiniPig cMiniPig;
    private final PropertiesLoader loader;

    public ControlPrincipal() {
        this.cVista = new ControlVista(this);   
        this.cMiniPig = new GestorMiniPig();   
        this.loader = new PropertiesLoader();
    }

    // ---------------------------
    // CARGA DESDE PROPERTIES
    // ---------------------------

    /**
     * Abre un JFileChooser  y retorna el File seleccionado.
     * Si el usuario cancela, retorna null.
     */
    public File seleccionarArchivoProperties() {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        fc.showOpenDialog(fc);
        return fc.getSelectedFile(); 
    }

    /**
     * Carga MiniPigs desde un archivo properties.
     */
    public ArrayList<MiniPigDTO> cargarMiniPigs(File archivoProperties) throws IOException {
        if (archivoProperties == null) {
            throw new IllegalArgumentException("No se seleccionó archivo.");
        }

        Properties props = loader.cargar(archivoProperties);

        if (props == null || props.isEmpty()) {
            throw new IllegalArgumentException("El archivo properties está vacío.");
        }

        ArrayList<String> claves = new ArrayList<>(props.stringPropertyNames());
        claves.sort(Comparator.naturalOrder());

        ArrayList<MiniPigDTO> lista = new ArrayList<>();
        for (String clave : claves) {
            String linea = props.getProperty(clave);
            if (linea == null || linea.trim().isEmpty()) continue;
            lista.add(parsearLinea(linea, clave));
        }
        return lista;
    }

    /**
     * Flujo completo estilo “CargaObjeto”: abre chooser y retorna la lista parseada.
     */
    public ArrayList<MiniPigDTO> cargarMiniPigsDesdeChooser() throws IOException {
        File f = seleccionarArchivoProperties();
        return cargarMiniPigs(f);
    }

    private MiniPigDTO parsearLinea(String linea, String clave) {
        String[] datos = linea.split(",");
        if (datos.length != 11) {
            throw new IllegalArgumentException(
                    "Formato incorrecto en " + clave + " (se esperaban 11 campos): " + linea
            );
        }

        int codigo = parseIntObligatorio(datos[0], "codigo", clave);

        String nombre = nullIfEmptyOrNull(datos[1]);
        String genero = nullIfEmptyOrNull(datos[2]);
        String idMicrochip = nullIfEmptyOrNull(datos[3]); // alfanumérico
        String raza = nullIfEmptyOrNull(datos[4]);
        String color = nullIfEmptyOrNull(datos[5]);
        String peso = nullIfEmptyOrNull(datos[6]);
        String altura = nullIfEmptyOrNull(datos[7]);
        String c1 = nullIfEmptyOrNull(datos[8]);
        String c2 = nullIfEmptyOrNull(datos[9]);
        String foto = nullIfEmptyOrNull(datos[10]);

        return new MiniPigDTO(
                codigo, nombre, genero, idMicrochip, raza, color,
                peso, altura, c1, c2, foto
        );
    }

    private String nullIfEmptyOrNull(String s) {
        if (s == null) return null;
        String t = s.trim();
        if (t.isEmpty()) return null;
        if ("null".equalsIgnoreCase(t)) return null;
        return t;
    }

    private int parseIntObligatorio(String s, String campo, String clave) {
        String t = nullIfEmptyOrNull(s);
        if (t == null) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio en " + clave);
        }
        return Integer.parseInt(t);
    }

    /**
     * Verifica si el DTO tiene campos faltantes (null).
     */
    public boolean tieneCamposFaltantes(MiniPigDTO dto) {
        if (dto == null) return true;
        return dto.getNombre() == null
                || dto.getGenero() == null
                || dto.getIdMicrochip() == null
                || dto.getRaza() == null
                || dto.getColor() == null
                || dto.getPeso() == null
                || dto.getAltura() == null
                || dto.getCaracteristica1() == null
                || dto.getCaracteristica2() == null
                || dto.getFoto() == null;
    }

    // getters si tu vista/otros controles necesitan acceso
    public ControlVista getcVista() { return cVista; }
    public GestorMiniPig getcMiniPig() { return cMiniPig; }
}