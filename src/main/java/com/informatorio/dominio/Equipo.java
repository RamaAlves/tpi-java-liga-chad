package com.informatorio.dominio;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private List<Jugador> listaDeJugadores;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.listaDeJugadores = new ArrayList<Jugador>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Jugador> getListaDeJugadores() {
        return listaDeJugadores;
    }

    public void setListaDeJugadores(List<Jugador> listaDeJugadores) {
        this.listaDeJugadores = listaDeJugadores;
    }

    public void registrarJugador(Jugador jugador) {
        listaDeJugadores.add(jugador);
    }

    public Jugador getJugarorPorNombre(String nombre) {
        for (Jugador jugador : listaDeJugadores) {
            if (jugador.getNombre().equals(nombre)) {
                return jugador;
            }
        }
        return null;
    }

    public void eliminarJugador(Jugador jugador) {
        listaDeJugadores.remove(jugador);
    }

    public ArrayList<JugadorTitular> getListaDeJugadoresTitulares() {
        ArrayList<JugadorTitular> listaDeJugadoresTitulares = new ArrayList<JugadorTitular>();

        for (Jugador jugador : listaDeJugadores) {
            if (jugador.esTitular()) {
                listaDeJugadoresTitulares.add((JugadorTitular) jugador);
            }
        }
        return listaDeJugadoresTitulares;
    }
    
    public ArrayList<JugadorSuplente> getListaDeJugadoresSuplentes() {
        ArrayList<JugadorSuplente> listaDeJugadoresSuplentes = new ArrayList<JugadorSuplente>();

        for (Jugador jugador : listaDeJugadores) {
            if (!jugador.esTitular()) {
                listaDeJugadoresSuplentes.add((JugadorSuplente) jugador);
            }
        }
        return listaDeJugadoresSuplentes;
    }
    
    public Jugador getMejorJugador() {
        Jugador mejorJugador = listaDeJugadores.get(0);
        for (Jugador jugador : listaDeJugadores) {
            if (jugador.getCantidadDeGoles() > mejorJugador.getCantidadDeGoles()) {
                mejorJugador = jugador;
            }
        }
        return mejorJugador;
    }
    
    public JugadorTitular getJugadorConMasMinutos() {
        ArrayList<JugadorTitular> listaDeJugadoresTitulares = getListaDeJugadoresTitulares();
        JugadorTitular jugadorConMasMinutos = listaDeJugadoresTitulares.get(0);

        for (JugadorTitular jugador : listaDeJugadoresTitulares) {
            if (jugador.getMinutosJugados() > jugadorConMasMinutos.getMinutosJugados()) {
                jugadorConMasMinutos = jugador;
            }
        }
        return jugadorConMasMinutos;
    }

    public ArrayList<Jugador> getJugadoresSinGoles() {
        ArrayList<Jugador> jugadoresSinGoles = new ArrayList<Jugador>();
        for (Jugador jugador : listaDeJugadores) {
            if (jugador.getCantidadDeGoles() == 0) {
                jugadoresSinGoles.add(jugador);
            }
        }
        return jugadoresSinGoles;
    }
    
    public JugadorSuplente getSuplenteMasUsado() {
        ArrayList<JugadorSuplente> listaDeJugadoresSuplentes = getListaDeJugadoresSuplentes();
        JugadorSuplente suplenteMasUsado = listaDeJugadoresSuplentes.get(0);
        for (JugadorSuplente jugador : listaDeJugadoresSuplentes) {
            if (jugador.getPartidosIngresados() > suplenteMasUsado.getPartidosIngresados()) {
                suplenteMasUsado = jugador;
            }
        }
        return suplenteMasUsado;
    }

    public int getTotalDeGoles() {
        int totalDeGoles = 0;
        for (Jugador jugador : listaDeJugadores) {
            totalDeGoles = totalDeGoles + jugador.getCantidadDeGoles();
        }
        return totalDeGoles;
    }

    public int getTotalDeJugadores() {
        return listaDeJugadores.size();
    }
    
    public double getPromedioGoles() {
        return getTotalDeGoles() / getTotalDeJugadores();
    }

}
