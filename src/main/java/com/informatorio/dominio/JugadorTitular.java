package com.informatorio.dominio;

public class JugadorTitular extends Jugador {
    private int minutosJugados;

    public JugadorTitular(String nombre, int edad) {
        super(nombre, edad, 0);
        this.minutosJugados = 0;
    }

    public int getMinutosJugados() {
        return minutosJugados;
    }
    
    public void setMinutosJugados(int minutosJugados) {
        this.minutosJugados = minutosJugados;
    }

    public void addMinutosJugados(int nuevosMinutosJugados) {
        this.minutosJugados = minutosJugados + nuevosMinutosJugados;
    }

    @Override
    public boolean esTitular() {
        return true;
    }

    @Override
    public String getRol() {
        return "Titular";
    }

    @Override
    public double porcentajeEficiencia() {
        return (this.getCantidadDeGoles() / minutosJugados)*100;
    }
    
}
