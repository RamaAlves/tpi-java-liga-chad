package com.informatorio.servicios.evaluadorDeEstadisticas;

import java.util.List;
import java.util.Map;

import com.informatorio.dominio.Equipo;
import com.informatorio.dominio.Jugador;
import com.informatorio.dominio.JugadorSuplente;
import com.informatorio.dominio.JugadorTitular;

public interface EvaluadorDeEstadisticasService {
    
    public void reporteGeneral();

    public void reporteEquipo(Equipo equipo);//👍
    
    public List<Jugador> getGoleadores();//👍

    public List<JugadorTitular> getTitularesMasEficientes();
    
    public List<JugadorSuplente> getSuplentesMasEficientes();

    public void mostrarGoleadores();//👍

    public void mostrarPromedioDeGoles(); //👍

    public List<Map.Entry<Equipo, Integer>> rankingDeEquiposPorGoles();//👍

    public int totalDeGolesDeLiga();
    
    public void mostrarRankingDeEquiposPorGoles();//👍
    
    public void listarJugadoresSinJugar();//👍

    public void mostrarSuplenteMasUsado();//👍

    public int cantidadDePartidosJugadosPorUnEquipo(Equipo equipo);//👍

    public int cantidadDeGolesEnLaLiga(Equipo equipo);//👍
}
