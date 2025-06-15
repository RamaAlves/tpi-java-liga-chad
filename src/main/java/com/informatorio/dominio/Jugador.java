package com.informatorio.dominio;

public abstract class Jugador {
    private String nombre;
    private int edad;
    private int cantidadDeGoles;

    public Jugador(String nombre, int edad, int cantidadDeGoles) {
        this.nombre = nombre;
        this.edad = edad;
        this.cantidadDeGoles = cantidadDeGoles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public int getCantidadDeGoles() {
        return cantidadDeGoles;
    }

    public void setCantidadDeGoles(int cantidadDeGoles) {
        this.cantidadDeGoles = cantidadDeGoles;
    }

    public void hacerGol() {
        this.cantidadDeGoles = this.cantidadDeGoles++;
    }

    public abstract boolean esTitular();

    public abstract String getRol();

    public abstract double porcentajeEficiencia();

}
