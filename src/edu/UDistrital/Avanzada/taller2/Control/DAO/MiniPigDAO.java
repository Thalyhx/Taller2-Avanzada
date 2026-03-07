/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.UDistrital.Avanzada.taller2.Control.DAO;

import edu.UDistrital.Avanzada.taller2.Modelo.Conexion.Conexion;
import edu.UDistrital.Avanzada.taller2.Modelo.MiniPigDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO de la tabla  minipig
 * Permite validar duplicados e insertar registros.
 *
 * @author nath
 * @version 1.0
 * @since 2026-03-07
 */
public class MiniPigDAO {

    private final Conexion conexion;

    /**
     * Crea el DAO con la instancia de conexión.
     */
    public MiniPigDAO() {
        this.conexion = Conexion.getInstancia();
    }
    
     /**
     * Lista todos los minipigs almacenados en la tabla {@code minipig}.
     *
     * @return lista de {@link MiniPigDTO}
     * @throws SQLException si ocurre un error en BD
     */
    public java.util.ArrayList<MiniPigDTO> listaDeMiniPigs() throws SQLException {
        java.util.ArrayList<MiniPigDTO> misMiniPigs = new java.util.ArrayList<>();
        String consulta = "SELECT codigo, nombre, genero, idMicrochip, raza, color, peso, altura, "
                + "caracteristica1, caracteristica2, foto "
                + "FROM minipig";

        try (java.sql.Connection cn = conexion.getConnection();
             java.sql.PreparedStatement ps = cn.prepareStatement(consulta);
             java.sql.ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MiniPigDTO miniPig = new MiniPigDTO(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getString("genero"),
                        rs.getString("idMicrochip"),
                        rs.getString("raza"),
                        rs.getString("color"),
                        rs.getString("peso"),
                        rs.getString("altura"),
                        rs.getString("caracteristica1"),
                        rs.getString("caracteristica2"),
                        rs.getString("foto")
                );
                misMiniPigs.add(miniPig);
            }
        }

        return misMiniPigs;
    }
    
    /**
     * Consulta un minipig por su código.
     *
     * @param codigo código 
     * @return {@link MiniPigDTO} si existe; si no existe retorna {@code null}
     * @throws SQLException si ocurre un error SQL
     */
    public MiniPigDTO consultarPorCodigo(int codigo) throws SQLException {
        String sql = "SELECT codigo, nombre, genero, idMicrochip, raza, color, peso, altura, "
                   + "caracteristica1, caracteristica2, foto "
                   + "FROM minipig WHERE codigo = ?";

        try (Connection cn = conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                return new MiniPigDTO(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getString("genero"),
                        rs.getString("idMicrochip"),
                        rs.getString("raza"),
                        rs.getString("color"),
                        rs.getString("peso"),
                        rs.getString("altura"),
                        rs.getString("caracteristica1"),
                        rs.getString("caracteristica2"),
                        rs.getString("foto")
                );
            }
        }
    }

    /**
     * Verifica si ya existe un registro con el código dado.
     *
     * @param codigo del minipig
     * @return true si existe
     * @throws SQLException error SQL
     */
    public boolean existePorCodigo(int codigo) throws SQLException {
        String sql = "SELECT 1 FROM minipig WHERE codigo = ? LIMIT 1";

        try (Connection cn = conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    
    /**
     * Consulta un minipig por su id de microchip.
     *
     * @param idMicrochip idMicrochip (UNIQUE)
     * @return {@link MiniPigDTO} si existe; si no existe retorna {@code null}
     * @throws SQLException si ocurre un error SQL
     */
    public MiniPigDTO consultarPorMicrochip(String idMicrochip) throws SQLException {
        if (idMicrochip == null) {
            return null;
        }

        String sql = "SELECT codigo, nombre, genero, idMicrochip, raza, color, peso, altura, "
                   + "caracteristica1, caracteristica2, foto "
                   + "FROM minipig WHERE idMicrochip = ?";

        try (Connection cn = conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, idMicrochip);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                return new MiniPigDTO(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getString("genero"),
                        rs.getString("idMicrochip"),
                        rs.getString("raza"),
                        rs.getString("color"),
                        rs.getString("peso"),
                        rs.getString("altura"),
                        rs.getString("caracteristica1"),
                        rs.getString("caracteristica2"),
                        rs.getString("foto")
                );
            }
        }
    }

    /**
     * Verifica si ya existe un registro con el microchip dado.
     *
     * @param idMicrochip UNIQUE del minipig
     * @return true si existe
     * @throws SQLException error SQL
     */
    public boolean existePorMicrochip(String idMicrochip) throws SQLException {
        if (idMicrochip == null) return false;

        String sql = "SELECT 1 FROM minipig WHERE idMicrochip = ? LIMIT 1";

        try (Connection cn = conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, idMicrochip);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    /**
     * Inserta un minipig en la tabla.
     *
     * @param dto datos a insertar
     * @throws SQLException error SQL
     */
    public void insertar(MiniPigDTO dto) throws SQLException {
        String sql = "INSERT INTO minipig "
                + "(codigo, nombre, genero, idMicrochip, raza, color, peso, altura, caracteristica1, caracteristica2, foto) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, dto.getCodigo());
            ps.setString(2, dto.getNombre());
            ps.setString(3, dto.getGenero());
            ps.setString(4, dto.getIdMicrochip());
            ps.setString(5, dto.getRaza());
            ps.setString(6, dto.getColor());
            ps.setString(7, dto.getPeso());
            ps.setString(8, dto.getAltura());
            ps.setString(9, dto.getCaracteristica1());
            ps.setString(10, dto.getCaracteristica2());
            ps.setString(11, dto.getFoto());

            ps.executeUpdate();
        }
    }

    /**
     * Inserta evitando duplicados por {@code codigo} o {@code idMicrochip}.
     *
     * @param dto datos a insertar
     * @return true si se insertó, false si ya existía
     * @throws SQLException error SQL
     */
    public boolean insertarSiNoExiste(MiniPigDTO dto) throws SQLException {
        if (dto == null) {
            throw new IllegalArgumentException("dto no puede ser null");
        }
        if (existePorCodigo(dto.getCodigo())) return false;
        if (existePorMicrochip(dto.getIdMicrochip())) return false;

        insertar(dto);
        return true;
    }
}