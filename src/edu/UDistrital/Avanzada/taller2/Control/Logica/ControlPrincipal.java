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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Properties;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

/**
 * Control principal (Fachada).
 * - Crea el ControlVista.
 * - El ControlVista crea y controla la VentanaPrincipal.
 * - Expone métodos para que ControlVista 
 */
public class ControlPrincipal {

    private final GestorMiniPig cMiniPig;
    private final PropertiesLoader loader;

    private final ControlVista controlVista;

    public ControlPrincipal() {
        this.cMiniPig = new GestorMiniPig();
        this.loader = new PropertiesLoader();

        this.controlVista = new ControlVista(this);

        // UI debe iniciar en el EDT
        SwingUtilities.invokeLater(() -> controlVista.iniciarUI());
    }

    // =========================================================
    // FACHADA hacia GestorMiniPig (CRUD + edición en memoria)
    // =========================================================

    public ArrayList<MiniPigDTO> listarTodos() throws SQLException {
        return cMiniPig.listar();
    }

    public boolean insertarSiNoExiste(MiniPigDTO dto) throws SQLException {
        return cMiniPig.insertarSiNoExiste(dto);
    }

    public ArrayList<MiniPigDTO> insertarSiNoExisteYRefrescar(MiniPigDTO dto) throws SQLException {
        return cMiniPig.insertarSiNoExisteYRefrescar(dto);
    }

    public ArrayList<MiniPigDTO> eliminarPorCodigoYRefrescar(int codigo) throws SQLException {
        return cMiniPig.eliminarPorCodigoYRefrescar(codigo);
    }

    public ArrayList<MiniPigDTO> eliminarPorMicrochipYRefrescar(String microchip) throws SQLException {
        return cMiniPig.eliminarPorMicrochipYRefrescar(microchip);
    }

    public MiniPigDTO cargarParaEdicionPorCodigo(int codigo) throws SQLException {
        return cMiniPig.cargarParaEdicionPorCodigo(codigo);
    }

    public MiniPigDTO cargarParaEdicionPorMicrochip(String microchip) throws SQLException {
        return cMiniPig.cargarParaEdicionPorMicrochip(microchip);
    }

    public void aplicarCambiosEnMemoria(MiniPigDTO cambios) {
        cMiniPig.aplicarCambiosEnMemoriaCompleto(cambios);
    }

    public void guardarCambios() throws SQLException {
        cMiniPig.guardarCambios();
    }

    public void imprimirMiniPigsEnConsolaAntesDeSalir() throws SQLException {
        for (MiniPigDTO d : listarTodos()) {
            System.out.println(d);
        }
    }

    // =========================================================
    // CARGA DESDE PROPERTIES (si aún lo necesitas)
    // =========================================================

    public File seleccionarArchivoProperties() {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        fc.showOpenDialog(fc);
        return fc.getSelectedFile();
    }

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
        String idMicrochip = nullIfEmptyOrNull(datos[3]);
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
}