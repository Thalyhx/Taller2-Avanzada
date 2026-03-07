/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.UDistrital.Avanzada.taller2.Modelo;

/**
 *<p>
 * Esta clase MiniPigDTO se utiliza para transportar datos.
 *</p>
 * 
 * @author nath
 * @version 1.0
 * @since 2026-03-06
 * 
 */
public class MiniPigDTO {
    
    // Atributos
    private int codigo;    //No se puede modificar
    private String nombre;
    private String genero;
    private int idMicrochip;   //No se puede modificar
    private String raza;
    private String color;
    private double peso;
    private double altura;
    private String caracteristica1;
    private String caracteristica2;
    private String foto;
    
    // Constructor 
    public MiniPigDTO(int codigo, String nombre, String genero, int idMicrochip, String raza, String color, double peso, double altura, String caracteristica1, String caracteristica2, String foto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.idMicrochip = idMicrochip;
        this.raza = raza;
        this.color = color;
        this.peso = peso;
        this.altura = altura;
        this.caracteristica1 = caracteristica1;
        this.caracteristica2 = caracteristica2;
        this.foto = foto;
    }
    
    
    // Constructor por sobrecarga 
    
    public MiniPigDTO() {
    }
    

    // Getters

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public int getIdMicrochip() {
        return idMicrochip;
    }

    public String getRaza() {
        return raza;
    }

    public String getColor() {
        return color;
    }

    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }

    public String getCaracteristica1() {
        return caracteristica1;
    }

    public String getCaracteristica2() {
        return caracteristica2;
    }

    public String getFoto() {
        return foto;
    }
    
    // Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setCaracteristica1(String caracteristica1) {
        this.caracteristica1 = caracteristica1;
    }

    public void setCaracteristica2(String caracteristica2) {
        this.caracteristica2 = caracteristica2;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    // metodo to String
    
    public String toString(){
    
    return "codigo= " + codigo + " , nombre= '" + nombre + "' , genero= " + genero +
               " , idMicrochip= " + idMicrochip + " , raza= " + raza + " , color= " + color +
               " , peso= " + peso + " , altura= " + altura + " , caracteristica1= " + caracteristica1 +
               " , caracteristica2= " + caracteristica2 + " , urlFoto= (" + foto + ")";
    }
    
    
}
