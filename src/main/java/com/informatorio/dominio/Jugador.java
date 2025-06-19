package com.informatorio.dominio;

import java.util.Objects;

public abstract class Jugador {
    private String nombre;
    private int edad;
    private int cantidadDeGoles;
    private int numeroRegistro;
    private boolean jugando;
    

    public Jugador(String nombre, int edad, int numeroRegistro) {
        this.nombre = nombre;
        this.edad = edad;
        this.cantidadDeGoles = 0;
        this.numeroRegistro = numeroRegistro;
        this.jugando = false;
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
        this.cantidadDeGoles = getCantidadDeGoles()+1;
    }

    public void quitarGol() {
        if (cantidadDeGoles > 0) {
            this.cantidadDeGoles = this.cantidadDeGoles--;
        }
    }

    public int getNumeroRegistro() {
        return numeroRegistro;
    }
    
    public void setNumeroRegistro(int nuevoNumeroRegistro) {
        this.numeroRegistro = nuevoNumeroRegistro;
    }

    public boolean getEstado() {
        return jugando;
    }

    public void setEstado(boolean estado) {
        this.jugando = estado;
    }

    public abstract boolean esTitular();

    public abstract String getRol();

    public abstract double porcentajeEficiencia();

    public abstract void salirDeLaCancha(int minutos);

    public abstract void entrarALaCancha();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(numeroRegistro, jugador.numeroRegistro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroRegistro); 
    }
}
