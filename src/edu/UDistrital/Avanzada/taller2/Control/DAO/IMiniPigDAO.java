/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.UDistrital.Avanzada.taller2.Control.DAO;

import edu.UDistrital.Avanzada.taller2.Modelo.MiniPigDTO;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 *
 * @author sebas
 */
public interface IMiniPigDAO {
    ArrayList<MiniPigDTO> listaDeMiniPigs() throws SQLException;

    MiniPigDTO consultarPorCodigo(int codigo) throws SQLException;

    boolean existePorCodigo(int codigo) throws SQLException;

    MiniPigDTO consultarPorMicrochip(String idMicrochip) throws SQLException;

    boolean existePorMicrochip(String idMicrochip) throws SQLException;

    ArrayList<MiniPigDTO> consultarPorRaza(String raza) throws SQLException;

    ArrayList<MiniPigDTO> consultarPorNombre(String nombre) throws SQLException;

    void insertar(MiniPigDTO dto) throws SQLException;

    boolean insertarSiNoExiste(MiniPigDTO dto) throws SQLException;

    boolean actualizar(MiniPigDTO dto) throws SQLException;

    boolean eliminarPorCodigo(int codigo) throws SQLException;

    boolean eliminarPorMicrochip(String idMicrochip) throws SQLException;
}
