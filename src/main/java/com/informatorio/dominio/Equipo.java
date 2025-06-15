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

    public ArrayList<JugadorSuplente> getListaDeJugadoresSinJugar() {
        ArrayList<JugadorSuplente> listaDeJugadoresSinJugar = new ArrayList<JugadorSuplente>();
         for (JugadorSuplente jugadorSuplente : getListaDeJugadoresSuplentes()) {
            if (jugadorSuplente.getPartidosIngresados()==0) {
                listaDeJugadoresSinJugar.add(jugadorSuplente);
            }
        }
        return listaDeJugadoresSinJugar;
    }
    
    public List<Jugador> getMejoresJugadores() {
        List<Jugador> mejoresJugadores = new ArrayList<Jugador>();
        mejoresJugadores.add(listaDeJugadores.get(0));
        for (Jugador jugador : listaDeJugadores) {
            if (jugador.getCantidadDeGoles() > mejoresJugadores.getLast().getCantidadDeGoles()) {
                mejoresJugadores.clear();
                mejoresJugadores.add(jugador);
            } else if (jugador.getCantidadDeGoles() == mejoresJugadores.getLast().getCantidadDeGoles()) {
                mejoresJugadores.add(jugador);
            }
        }
        return mejoresJugadores;
    }

    public List<JugadorSuplente> getSuplentesMasEficiente() {
        List<JugadorSuplente> jugadoresMasEficientes = new ArrayList<JugadorSuplente>();
        jugadoresMasEficientes.add(getListaDeJugadoresSuplentes().get(0));
        for (JugadorSuplente jugador : getListaDeJugadoresSuplentes()) {
            if (jugador.porcentajeEficiencia() > jugadoresMasEficientes.getLast().porcentajeEficiencia()) {
                jugadoresMasEficientes.clear();
                jugadoresMasEficientes.add(jugador);
            } else if (jugador.porcentajeEficiencia() == jugadoresMasEficientes.getLast().porcentajeEficiencia()) {
                jugadoresMasEficientes.add(jugador);
            }
        }
        return jugadoresMasEficientes;
    }

    public List<JugadorTitular> getTitularesMasEficiente() {
        List<JugadorTitular> jugadoresMasEficientes = new ArrayList<JugadorTitular>();
        jugadoresMasEficientes.add(getListaDeJugadoresTitulares().get(0));
        for (JugadorTitular jugador : getListaDeJugadoresTitulares()) {
            if (jugador.porcentajeEficiencia() > jugadoresMasEficientes.getLast().porcentajeEficiencia()) {
                jugadoresMasEficientes.clear();
                jugadoresMasEficientes.add(jugador);
            } else if (jugador.porcentajeEficiencia() == jugadoresMasEficientes.getLast().porcentajeEficiencia()) {
                jugadoresMasEficientes.add(jugador);
            }
        }
        return jugadoresMasEficientes;
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
    
    public List<JugadorSuplente> getSuplentesMasUsado() {
        ArrayList<JugadorSuplente> listaDeSuplentesMasUsados = new ArrayList<JugadorSuplente>();
        listaDeSuplentesMasUsados.add(getListaDeJugadoresSuplentes().get(0));
        for (JugadorSuplente jugador : getListaDeJugadoresSuplentes()) {
            if (jugador.getPartidosIngresados() > listaDeSuplentesMasUsados.getLast().getPartidosIngresados()) {
                listaDeSuplentesMasUsados.clear();
                listaDeSuplentesMasUsados.add(jugador);
            }else if(jugador.getPartidosIngresados() == listaDeSuplentesMasUsados.getLast().getPartidosIngresados()){
                listaDeSuplentesMasUsados.add(jugador);
            }
        }
        return listaDeSuplentesMasUsados;
    }

    public int getTotalDeGolesDeJugadores() {
        int totalDeGoles = 0;
        for (Jugador jugador : listaDeJugadores) {
            totalDeGoles = totalDeGoles + jugador.getCantidadDeGoles();
        }
        return totalDeGoles;
    }

    public int getTotalDeJugadores() {
        return listaDeJugadores.size();
    }
    
    public double getPromedioGolesPorJugadores() {
        return getTotalDeGolesDeJugadores() / getTotalDeJugadores();
    }

    public boolean listoParaJugar(){
        return getListaDeJugadoresTitulares().size() > 5;
    }

}
