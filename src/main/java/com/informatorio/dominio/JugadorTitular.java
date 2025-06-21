package com.informatorio.dominio;

public class JugadorTitular extends Jugador {
    private int minutosJugados;
    
    public JugadorTitular(String nombre, int edad, int numeroRegistro) {
        super(nombre, edad, numeroRegistro);
        this.minutosJugados = 0;
    }

    public int getMinutosJugados() {
        return minutosJugados;
    }
    
    public void setMinutosJugados(int minutosJugados) {
        this.minutosJugados = minutosJugados;
    }

    public void addMinutosJugados(int nuevosMinutosJugados) {
        this.minutosJugados = getMinutosJugados() + nuevosMinutosJugados;
    }

    @Override
    public void entrarALaCancha() {
        this.setEstado(true);
        System.out.printf("Jugador titular: %s entra a la cancha.%n", getNombre());
    }

    @Override
    public void salirDeLaCancha(int minutos) {
        addMinutosJugados(minutos);
        setEstado(false);
        System.out.printf("Jugador titular: %s sale de la cancha. Minutos sumados: %d%n", getNombre(), minutos);
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
         int minutos = getMinutosJugados();
         int goles = getCantidadDeGoles();
    
    if (minutos <= 0) {
        return 0.0;
    }
    double partidosEquivalentesJugados = (double) minutos / 90.0;
    if (partidosEquivalentesJugados == 0) {
        return 0.0; 
    }
    return (goles / partidosEquivalentesJugados) * 100.0;
    }
    
}
