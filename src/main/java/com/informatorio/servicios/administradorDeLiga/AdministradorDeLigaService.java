package com.informatorio.servicios.administradorDeLiga;


import java.util.List;

import com.informatorio.dominio.Equipo;
import com.informatorio.dominio.Jugador;
import com.informatorio.dominio.Partido;

public interface AdministradorDeLigaService {

    public void listarEquipos();

    public void listarEquipos(List<Equipo> equiposAListar, String mensajePersonalizado);

    public void listarPartidos();
    
    public void listarJugadoresEnLaLiga();

    public void listarJugadoresDeUnEquipo();

    public Equipo getEquipoPorNombre(String nombreEquipo);

    public void registrarEquipo(Equipo equipo);

    public void registrarEquipoPorTerminal();

    public void registrarJugador(Equipo equipo, Jugador jugador);

    public void registrarJugadorPorTerminal();

    public void registrarPartido(List<Equipo> equipos);

    public Partido getPartidoEnCurso();

    public void registrarPartidoPorTerminal();

    public void registrarGolEnPartido(Partido partido, Jugador jugador, int goles);

    public void registrarGolPorTerminal(Partido partido);
    
    public void eliminarGolEnPartido(Partido partido, Jugador jugador);
    
    public void eliminarGolPorTerminal(Partido partido);

    public void finalizarPartido(Partido partido);

    public void mostrarJugadores(Equipo equipo);

    public void cambiarJugador(Jugador jugadorSuplente, Jugador jugadortitular, int minutos);

    public void cambiarJugadorPorTerminal(Partido partido);
    
    public void transferirJugadores(Equipo equipoEmisor, Equipo equipoReceptor, Jugador JugadorSaliente,
            Jugador JugadorEntrante);

    public void transferirJugadoresPorTerminal();

    public void cargarMinutosJugadosATitulares(Equipo equipo, int minutos);
    //csv exportarJugadores(List<Equipo>)

}
