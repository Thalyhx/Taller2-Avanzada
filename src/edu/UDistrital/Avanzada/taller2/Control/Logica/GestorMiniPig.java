/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.UDistrital.Avanzada.taller2.Control.Logica;

import edu.UDistrital.Avanzada.taller2.Control.DAO.MiniPigDAO;
import edu.UDistrital.Avanzada.taller2.Modelo.MiniPigDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Gestor (lógica) para operaciones de MiniPig.
 * <p>
 * controla los datos, invocando al DAO,
 * devuelve la lista refrescada para que la Vista se actualice.
 * </p>
 *
 * @author nath
 * @version 1.0
 * @since 2026-03-07
 */
public class GestorMiniPig {

    private final MiniPigDAO dao;

    /**
     * Crea el gestor con su DAO.
     */
    public GestorMiniPig() {
        this.dao = new MiniPigDAO();
    }

    /**
     * Elimina el minipig actualmente en visualización usando su código y retorna
     * la lista actualizada para refrescar la vista.
     *
     * @param codigo código del minipig a eliminar
     * @return lista actualizada (sin el registro eliminado)
     * @throws SQLException si ocurre un error en BD
     */
    public ArrayList<MiniPigDTO> eliminarPorCodigoYRefrescar(int codigo) throws SQLException {
        dao.eliminarPorCodigo(codigo);
        return dao.listaDeMiniPigs();
    }

    /**
     * Elimina el minipig actualmente en visualización usando su microchip y retorna
     * la lista actualizada para refrescar la vista.
     *
     * @param idMicrochip microchip del minipig a eliminar
     * @return lista actualizada (sin el registro eliminado)
     * @throws SQLException si ocurre un error en BD
     */
    public ArrayList<MiniPigDTO> eliminarPorMicrochipYRefrescar(String idMicrochip) throws SQLException {
        dao.eliminarPorMicrochip(idMicrochip);
        return dao.listaDeMiniPigs();
    }

    /**
     * Obtiene todos los minipigs (útil para refrescar sin eliminar).
     *
     * @return lista de minipigs
     * @throws SQLException si ocurre un error en BD
     */
    public ArrayList<MiniPigDTO> listar() throws SQLException {
        return dao.listaDeMiniPigs();
    }
}