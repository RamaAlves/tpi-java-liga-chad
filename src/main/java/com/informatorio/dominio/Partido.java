package com.informatorio.dominio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Partido {
    private List<Equipo> equipos;
    private Map<Equipo, Integer> resultado;
    private Map<Jugador, Integer> golesAnotadosPorJugador;

    public Partido(List<Equipo> equipos) {
        if (equipos == null || equipos.size() != 2) {
            throw new IllegalArgumentException("Un partido debe tener exactamente dos equipos.");
        }
        this.equipos = equipos;
        this.resultado = new HashMap<>();
        this.golesAnotadosPorJugador = new HashMap<>();

        for (Equipo equipo : equipos) {
            this.resultado.put(equipo, 0);
        }
    }
    
    public List<Equipo> getEquipos() {
        return equipos;
    }

    public Equipo getEquipo1() {
        return equipos.getFirst();
    }

    public Equipo getEquipo2() {
        return equipos.getLast();
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public Map<Equipo, Integer> getResultado() {
        return resultado;
    }

    public int getResultadoPorEquipo(Equipo equipo) {
        return resultado.get(equipo);
    }
    
    public void setResultado(Map<Equipo, Integer> resultado) {
        this.resultado = resultado;
    }

    public Map<Jugador, Integer> getGolesAnotadosPorJugador() {
        return golesAnotadosPorJugador;
    }

    public void setGolesAnotadosPorJugador(Map<Jugador, Integer> golesAnotadosPorJugador) {
        this.golesAnotadosPorJugador = golesAnotadosPorJugador;
    }

    public void addGolesAnotadosPorJugador(Jugador jugador, int goles) {
        int golesActuales = golesAnotadosPorJugador.get(jugador);
        this.golesAnotadosPorJugador.put(jugador, golesActuales+goles);
    }
    
    public void removeGolesAnotadosPorJugador(Jugador jugador) {
        this.golesAnotadosPorJugador.remove(jugador);
    }

}
