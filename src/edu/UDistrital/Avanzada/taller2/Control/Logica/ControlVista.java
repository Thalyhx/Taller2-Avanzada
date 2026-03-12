/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.UDistrital.Avanzada.taller2.Control.Logica;

import edu.UDistrital.Avanzada.taller2.Modelo.MiniPigDTO;
import edu.UDistrital.Avanzada.taller2.Vista.VentanaPrincipal;

import java.io.File;
import java.util.ArrayList;

public class ControlVista {

    private final ControlPrincipal cPrincipal;
    private VentanaPrincipal ventana;

    public ControlVista(ControlPrincipal cPrincipal) {
        this.cPrincipal = cPrincipal;
    }

    public void iniciarUI() {
        ventana = new VentanaPrincipal();

        // Precarga inicial
        try {
            File archivo = ventana.pedirArchivoPropertiesMiniPigs();
            if (archivo == null) {
                ventana.mostrarError("No se seleccionó archivo .properties. No se puede iniciar.");
                ventana.cerrar();
                return;
            }

            ArrayList<MiniPigDTO> cargados = cPrincipal.cargarMiniPigs(archivo);
            int insertados = 0;
            for (MiniPigDTO dto : cargados) {
                if (cPrincipal.insertarSiNoExiste(dto)) insertados++;
            }
            ventana.mostrarMensaje("Precarga completada. Insertados: " + insertados);

        } catch (Exception ex) {
            ventana.mostrarError("Error en precarga: " + ex.getMessage());
            ventana.cerrar();
            return;
        }

        // Pintar tabla y combo
        refrescarTablaYCombo();

        // Conectar eventos
        wireEvents();

        ventana.setVisible(true);
    }

    private void wireEvents() {
        // Ir a insertar
        ventana.panelTabla.btnInsertar.addActionListener(e -> ventana.irAInsertar());

        // Guardar inserción
        ventana.panelInsertar.btnGuardar.addActionListener(e -> {
            try {
                MiniPigDTO dto = leerDTODesdePanelInsertar();
                boolean ok = cPrincipal.insertarSiNoExiste(dto);
                if (!ok) {
                    ventana.mostrarError("Ya existe un minipig con ese código o microchip.");
                    return;
                }
                ventana.mostrarMensaje("Insertado correctamente.");
                ventana.limpiar();
                ventana.irATabla();
                refrescarTablaYCombo();
            } catch (Exception ex) {
                ventana.mostrarError("Error insertando: " + ex.getMessage());
            }
        });

        // Volver desde insertar
        ventana.panelInsertar.btnVolver.addActionListener(e -> {
            ventana.limpiar();
            ventana.irATabla();
        });

        // Limpiar
        ventana.panelTabla.btnLimpiar.addActionListener(e -> ventana.limpiar());

        // Buscar 
        ventana.panelTabla.btnBuscar.addActionListener(e -> {
            try {
                String dato = ventana.getTextoBusqueda();
                if (dato == null || dato.trim().isEmpty()) {
                    ventana.mostrarError("Digite un dato para buscar (código o microchip).");
                    return;
                }

                MiniPigDTO encontrado;
                if (dato.trim().matches("\\d+")) {
                    encontrado = cPrincipal.cargarParaEdicionPorCodigo(Integer.parseInt(dato.trim()));
                } else {
                    encontrado = cPrincipal.cargarParaEdicionPorMicrochip(dato.trim());
                }

                if (encontrado == null) {
                    ventana.mostrarError("No encontrado.");
                    return;
                }

                // Mostrar solo el encontrado en la tabla
                ArrayList<MiniPigDTO> uno = new ArrayList<>();
                uno.add(encontrado);
                ventana.mostrarMiniPigsEnTabla(uno);

            } catch (Exception ex) {
                ventana.mostrarError("Error buscando: " + ex.getMessage());
            }
        });

        // Selección desde combo 
        ventana.panelTabla.comboCerditos.addActionListener(e -> {
            try {
                String sel = (String) ventana.panelTabla.comboCerditos.getSelectedItem();
                if (sel == null) return;
                int codigo = Integer.parseInt(sel.split("-")[0].trim());
                MiniPigDTO dto = cPrincipal.cargarParaEdicionPorCodigo(codigo);
                if (dto == null) return;
                ArrayList<MiniPigDTO> uno = new ArrayList<>();
                uno.add(dto);
                ventana.mostrarMiniPigsEnTabla(uno);
            } catch (Exception ignored) {
            }
        });

        // Salir con botón
        ventana.panelTabla.btnSalir.addActionListener(e -> salir());

        // Salir 
        ventana.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosing(java.awt.event.WindowEvent e) {
                salir();
            }
        });
    }

    private void salir() {
        try {
            cPrincipal.imprimirMiniPigsEnConsolaAntesDeSalir();
        } catch (Exception ignored) {}
        System.exit(0);
    }

    private void refrescarTablaYCombo() {
        try {
            ArrayList<MiniPigDTO> lista = cPrincipal.listarTodos();
            ventana.mostrarMiniPigsEnTabla(lista);

            ventana.panelTabla.comboCerditos.removeAllItems();
            for (MiniPigDTO dto : lista) {
                ventana.panelTabla.comboCerditos.addItem(dto.getCodigo() + " - " + dto.getNombre());
            }
        } catch (Exception ex) {
            ventana.mostrarError("Error refrescando: " + ex.getMessage());
        }
    }

    private MiniPigDTO leerDTODesdePanelInsertar() {
        int codigo = Integer.parseInt(ventana.panelInsertar.txtCodigo.getText().trim());
        String nombre = ventana.panelInsertar.txtNombre.getText().trim();
        String genero = ventana.panelInsertar.txtGenero.getText().trim();
        String micro = ventana.panelInsertar.txtIdMicrochip.getText().trim();
        String raza = ventana.panelInsertar.txtRaza.getText().trim();
        String color = ventana.panelInsertar.txtColor.getText().trim();
        String peso = ventana.panelInsertar.txtPeso.getText().trim();
        String altura = ventana.panelInsertar.txtAltura.getText().trim();
        String c1 = ventana.panelInsertar.txtC1.getText().trim();
        String c2 = ventana.panelInsertar.txtC2.getText().trim();
        String foto = ventana.panelInsertar.txtFoto.getText().trim();

        return new MiniPigDTO(codigo, nombre, genero, micro, raza, color, peso, altura, c1, c2, foto);
    }

    public VentanaPrincipal getVentana() {
        return ventana;
    }
}