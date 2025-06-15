package com.informatorio.servicios.administradorDeLiga;

import java.util.List;

import com.informatorio.dominio.Equipo;
import com.informatorio.dominio.Jugador;
import com.informatorio.dominio.Partido;

public interface AdministradorDeLigaService {

    String listarEquipos(List<Equipo> equipos);

    void registrarEquipo(List<Equipo> equipos);

    void registrarJugador(Equipo equipo);

    void registrarPartido(List<Equipo> equipos, List<Partido> partidos);

    String mostrarJugadores(List<Equipo> equipos);

    void cambiarJugador(Jugador jugadorSuplente, Jugador jugadortitular, int minutos);

    void transferirJugadores(Equipo equipoEmisor, Equipo equipoReceptor);

    //csv exportarJugadores(List<Equipo>)

}
