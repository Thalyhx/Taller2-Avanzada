/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.UDistrital.Avanzada.taller2.Modelo.Conexion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * La clase Conexion Maneja la conexión a la base de datos usando un archivo properties
 *
 * @author nath
 * @version 1.0
 * @since 2026-03-07
 */
public class Conexion {

    private static Conexion instancia;

    private final String url;
    private final String user;
    private final String password;
    private final String driver;

    /**
     * Constructor privado: carga configuración 
     */
    private Conexion() {
        Properties p = new Properties();

        try (InputStream in = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            if (in == null) {
                throw new IllegalStateException("No se encontró db.properties en el classpath.");
            }
            p.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Error leyendo db.properties: " + e.getMessage(), e);
        }

        this.url = p.getProperty("db.url");
        this.user = p.getProperty("db.user");
        this.password = p.getProperty("db.password");
        this.driver = p.getProperty("db.driver");

        if (url == null || user == null || driver == null) {
            throw new IllegalStateException("Faltan propiedades obligatorias en db.properties (db.url, db.user, db.driver).");
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Driver no encontrado: " + driver, e);
        }
    }

    /**
     * Obtiene la instancia única de la conexión.
     *
     * @return instancia de {@link Conexion}
     */
    public static synchronized Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    /**
     * Abre y retorna una conexión nueva con los datos del properties.
     *
     * @return {@link Connection}
     * @throws SQLException si ocurre un error de conexión
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}