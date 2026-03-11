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
import java.util.ArrayList;

/**
 * DAO de la tabla  minipig
 * Permite validar duplicados e insertar registros.
 *
 * @author nath
 * @version 1.3
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
    public ArrayList<MiniPigDTO> listaDeMiniPigs() throws SQLException {
        ArrayList<MiniPigDTO> misMiniPigs = new ArrayList<>();
        String consulta = "SELECT codigo, nombre, genero, idMicrochip, raza, color, peso, altura, "
                + "caracteristica1, caracteristica2, foto "
                + "FROM minipig";

        try (Connection cn = conexion.getConnection();
                PreparedStatement ps = cn.prepareStatement(consulta);
                ResultSet rs = ps.executeQuery()) {

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
     * Consulta minipigs por raza.
     *
     * @param raza raza a buscar
     * @return lista de minipigs (puede venir vacía)
     * @throws SQLException si ocurre un error SQL
     */
    public ArrayList<MiniPigDTO> consultarPorRaza(String raza) throws SQLException {
        ArrayList<MiniPigDTO> lista = new ArrayList<>();
        if (raza == null) return lista;

        String sql = "SELECT codigo, nombre, genero, idMicrochip, raza, color, peso, altura, "
                   + "caracteristica1, caracteristica2, foto "
                   + "FROM minipig WHERE raza = ?";

        try (Connection cn = conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, raza);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new MiniPigDTO(
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
                    ));
                }
            }
        }
        return lista;
    }

    /**
     * Consulta minipigs por nombre.
     *
     * @param nombre nombre a buscar
     * @return lista de minipigs (puede venir vacía)
     * @throws SQLException si ocurre un error SQL
     */
    public ArrayList<MiniPigDTO> consultarPorNombre(String nombre) throws SQLException {
        ArrayList<MiniPigDTO> lista = new ArrayList<>();
        if (nombre == null) return lista;

        String sql = "SELECT codigo, nombre, genero, idMicrochip, raza, color, peso, altura, "
                   + "caracteristica1, caracteristica2, foto "
                   + "FROM minipig WHERE nombre = ?";

        try (Connection cn = conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new MiniPigDTO(
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
                    ));
                }
            }
        }
        return lista;
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
    
    /**
     * Actualiza un minipig en la base de datos.
     *
     * @param dto DTO con los nuevos valores 
     * @return true si actualizó 1 registro; false si no existía el código
     * @throws SQLException si ocurre un error SQL
     * @throws IllegalArgumentException si dto es null
     */
    public boolean actualizar(MiniPigDTO dto) throws SQLException {
        if (dto == null) {
            throw new IllegalArgumentException("dto no puede ser null");
        }

        String sql = "UPDATE minipig SET "
                + "nombre = ?, genero = ?, raza = ?, color = ?, peso = ?, altura = ?, "
                + "caracteristica1 = ?, caracteristica2 = ?, foto = ? "
                + "WHERE codigo = ?";

        try (Connection cn = conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
                        ps.setString(1, dto.getNombre());
            ps.setString(2, dto.getGenero());
            ps.setString(3, dto.getRaza());
            ps.setString(4, dto.getColor());
            ps.setString(5, dto.getPeso());
            ps.setString(6, dto.getAltura());
            ps.setString(7, dto.getCaracteristica1());
            ps.setString(8, dto.getCaracteristica2());
            ps.setString(9, dto.getFoto());

            // Identificador: no cambia
            ps.setInt(10, dto.getCodigo());

            return ps.executeUpdate() > 0;
        }
    }
    
     /**
     * Elimina un minipig por código.
     *
     * @param codigo PK del minipig
     * @return true si eliminó 1 registro; false si no existía
     * @throws SQLException si ocurre un error SQL
     */
    public boolean eliminarPorCodigo(int codigo) throws SQLException {
        String sql = "DELETE FROM minipig WHERE codigo = ?";

        try (Connection cn = conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, codigo);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Elimina un minipig por idMicrochip.
     *
     * @param idMicrochip UNIQUE del minipig
     * @return true si eliminó 1 registro; false si no existía
     * @throws SQLException si ocurre un error SQL
     */
    public boolean eliminarPorMicrochip(String idMicrochip) throws SQLException {
        if (idMicrochip == null) return false;

        String sql = "DELETE FROM minipig WHERE idMicrochip = ?";

        try (Connection cn = conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, idMicrochip);
            return ps.executeUpdate() > 0;
        }
    }
}