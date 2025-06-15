package com.informatorio.servicios.evaluadorDeEstadisticas.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.informatorio.dominio.Equipo;
import com.informatorio.dominio.Jugador;
import com.informatorio.dominio.JugadorSuplente;
import com.informatorio.dominio.JugadorTitular;
import com.informatorio.dominio.Partido;
import com.informatorio.servicios.evaluadorDeEstadisticas.EvaluadorDeEstadisticasService;

public class EvaluadorDeEstadisticasServiceImpl implements EvaluadorDeEstadisticasService {
    private List<Equipo> equipos;
    private List<Partido> partidos;

    public EvaluadorDeEstadisticasServiceImpl(List<Equipo> equipos, List<Partido> partidos){
        this.equipos = equipos;
        this.partidos = partidos;
    };

    @Override
    public void reporteGeneral() {
        System.err.println("Total de goles en la liga: " + totalDeGolesDeLiga());
        System.out.println("El jugador titular mas eficiente fue:");
        mostrarGoleadores();
        System.out.println(getTitularesMasEficientes().size() > 1 ? "Los titulares mas eficientes fueron: "
                : "El titular mas eficiente fue: ");
        for (Jugador jugador : getTitularesMasEficientes()) {
            System.err.println(jugador.getNombre());
        }
        System.out.println(getSuplentesMasEficientes().size() > 1 ? "Los suplentes mas eficientes fueron: "
                : "El suplente mas eficiente fue: ");
        for (Jugador jugador : getSuplentesMasEficientes()) {
            System.err.println(jugador.getNombre());
        }
        System.out.println("El equipo con mayor cantidad de goles fue: "+rankingDeEquiposPorGoles().get(0));
    }

    @Override
    public void reporteEquipo(Equipo equipo) {
        System.out.println("Mostrando reporte de equipo: " + equipo.getNombre());
        System.out.println("Promedio de goles de jugadores: ");
        System.out.printf(
                "De un total de %d jugadores y un total de %d goles el promedio de goles por jugador es de %d",
                equipo.getTotalDeJugadores(), equipo.getTotalDeGolesDeJugadores(),
                equipo.getPromedioGolesPorJugadores());
        if (equipo.getJugadoresSinGoles().size() > 0) {
            System.out.println("De los jugadores registrados en el equipo los siguientes no anotaron goles: ");
            for (Jugador jugador : equipo.getJugadoresSinGoles()) {
                System.out.println(jugador.getNombre());
            }
        } else {
            System.out.println("Todos los jugadores registrados en este equipo anotaron goles en la liga");
        }
        System.out.println("El jugador titular con mas minutos registrados del equipo es: "
                + equipo.getJugadorConMasMinutos().getNombre());
        System.out.println(equipo.getSuplentesMasUsado().size() > 1 ? "Los suplentes mas utilizados fueron: "
                : "El suplente mas utilizado fue: ");

        for (JugadorSuplente jugador : equipo.getSuplentesMasUsado()) {
            System.out.println(jugador.getNombre());
        }
    }

    @Override
    public List<Jugador> getGoleadores(){
        List<Jugador> goleadores = new ArrayList<Jugador>();
        goleadores.addAll(equipos.get(0).getMejoresJugadores());
        for (Equipo equipo : equipos) {
            if (equipo.getMejoresJugadores().getLast().getCantidadDeGoles() > goleadores.getLast()
                    .getCantidadDeGoles()) {
                goleadores.clear();
                goleadores.addAll(equipo.getMejoresJugadores());
            } else if (equipo.getMejoresJugadores().getLast().getCantidadDeGoles() == goleadores.getLast()
                    .getCantidadDeGoles()) {
                goleadores.addAll(equipo.getMejoresJugadores());
            }
        }
        return goleadores;
    }

    @Override
    public void mostrarGoleadores() {
        List<Jugador> goleadores = getGoleadores();
        System.out.println(goleadores.size() == 1 ? "El goleador de la liga fue:" : "Los goleadores de la liga fueron:");
        for (Jugador goleador : goleadores) {
            System.err.println(goleador.getNombre());
        }
    }
    
    @Override
    public void mostrarPromedioDeGoles() {

        System.err.println("Mostrando promedio de goles por equipo:");
        System.err.printf("%-40s%-30d%-30d%-30d%-30d%-30d%n",
                "Equipo",
                "Total de goles por equipo",
                "Cantidad de jugadores",
                "Promedio de goles por jugador",
                "Cantidad de partidos",
                "Promedio de goles por partidos");
        for (Equipo equipo : equipos) {
            int cantidadDePartidosJugados = cantidadDePartidosJugadosPorUnEquipo(equipo);
            int cantidadDeGolesEquipo = cantidadDeGolesEnLaLiga(equipo);
            System.err.printf("%-40s%-30d%-30d%-30d%-30d%-30d%n",
                    equipo.getNombre(),
                    cantidadDeGolesEquipo,
                    equipo.getTotalDeJugadores(),
                    equipo.getPromedioGolesPorJugadores(),
                    cantidadDePartidosJugados,
                    cantidadDeGolesEquipo / cantidadDePartidosJugados);
        }
        System.err.println("Mostrando promedio de goles de la liga:");
        System.out.printf("%-40s%-30d%-30d%-30d%n", "Total de equipos", "Partidos jugados", "Goles realizados",
                "Promedio");
        System.out.printf("%-40s%-30d%-30d%-30d%n", equipos.size(), partidos.size(), totalDeGolesDeLiga(),
                totalDeGolesDeLiga() / partidos.size());

    }
    
    @Override
    public int totalDeGolesDeLiga(){
        int totalDeGolesDeLiga = 0;
        for (Equipo equipo : equipos) {
            totalDeGolesDeLiga += equipo.getTotalDeGolesDeJugadores();
        }
        return totalDeGolesDeLiga;
    }
    
    @Override
    public void mostrarRankingDeEquiposPorGoles() {
        System.out.println("\nRanking de equipos por goles anotados:");
        for (Map.Entry<Equipo, Integer> entry : rankingDeEquiposPorGoles()) {
            System.out.println(entry.getKey().getNombre() + ": " + entry.getValue() + " goles");
        }
    }

    public List<Map.Entry<Equipo, Integer>> rankingDeEquiposPorGoles() {
        Map<Equipo, Integer> rankingPorGoles = new HashMap<Equipo, Integer>();
        for (Equipo equipo : equipos) {
            rankingPorGoles.put(equipo, cantidadDeGolesEnLaLiga(equipo));
        }
        List<Map.Entry<Equipo, Integer>> rankingPorGolesOrdenado = rankingPorGoles.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
        return rankingPorGolesOrdenado;
    }

    @Override
    public List<JugadorSuplente> getSuplentesMasEficientes() {
        List<JugadorSuplente> listaDeJugadoresSuplentes = new ArrayList<JugadorSuplente>();

        for (Equipo equipo : equipos) {
            listaDeJugadoresSuplentes.addAll(equipo.getSuplentesMasEficiente());
        }

        List<JugadorSuplente> listaDeSuplentesEficientes = new ArrayList<JugadorSuplente>();
        listaDeSuplentesEficientes.add(listaDeJugadoresSuplentes.getFirst());
        for (JugadorSuplente jugador : listaDeJugadoresSuplentes) {
            if (jugador.porcentajeEficiencia() > listaDeSuplentesEficientes.getLast().porcentajeEficiencia()) {
                listaDeSuplentesEficientes.clear();
                listaDeSuplentesEficientes.add(jugador);
            } else if (jugador.porcentajeEficiencia() == listaDeSuplentesEficientes.getLast().porcentajeEficiencia()) {
                listaDeSuplentesEficientes.add(jugador);
            }
        }
        return listaDeSuplentesEficientes;
    }
    
    @Override
    public List<JugadorTitular> getTitularesMasEficientes() {
        List<JugadorTitular> listaDeJugadoresTitulares = new ArrayList<JugadorTitular>();

        for (Equipo equipo : equipos) {
            listaDeJugadoresTitulares.addAll(equipo.getTitularesMasEficiente());
        }

        List<JugadorTitular> listaDeTitularesEficientes = new ArrayList<JugadorTitular>();
        listaDeTitularesEficientes.add(listaDeJugadoresTitulares.getFirst());
        for (JugadorTitular jugador : listaDeJugadoresTitulares) {
            if (jugador.porcentajeEficiencia() > listaDeTitularesEficientes.getLast().porcentajeEficiencia()) {
                listaDeTitularesEficientes.clear();
                listaDeTitularesEficientes.add(jugador);
            } else if (jugador.porcentajeEficiencia() == listaDeTitularesEficientes.getLast().porcentajeEficiencia()) {
                listaDeTitularesEficientes.add(jugador);
            }
        }
        return listaDeTitularesEficientes;
    }

    @Override
    public void listarJugadoresSinJugar() {
        System.out.println("Listando jugadores sin minutos de juego en la liga por equipo:");
        for (Equipo equipo : equipos) {
            System.out.println("Equipo: " + equipo.getNombre());
            int contadorLista = 0;//controlar si lista mal
            for (JugadorSuplente jugador : equipo.getListaDeJugadoresSinJugar()) {
                contadorLista++;
                System.out.println(contadorLista + ". " + jugador.getNombre());
            }
        }
        System.out.println("----------------------Fin de lista de equipos----------------------");
    }

    @Override
    public void mostrarSuplenteMasUsado() {
        ArrayList<JugadorSuplente> listaDeSuplentesMasUsados = new ArrayList<JugadorSuplente>();
        listaDeSuplentesMasUsados.addAll(equipos.getFirst().getSuplentesMasUsado());
        for (Equipo equipo : equipos) {
            if (equipo.getSuplentesMasUsado().getLast().getPartidosIngresados() > listaDeSuplentesMasUsados.getLast()
                    .getPartidosIngresados()) {
                listaDeSuplentesMasUsados.clear();
                listaDeSuplentesMasUsados.addAll(equipo.getSuplentesMasUsado());
            } else if(equipo.getSuplentesMasUsado().getLast().getPartidosIngresados()== listaDeSuplentesMasUsados.getLast()
                    .getPartidosIngresados()) {
                listaDeSuplentesMasUsados.addAll(equipo.getSuplentesMasUsado()); 
            }
        }
        System.out.println(
                listaDeSuplentesMasUsados.size() == 1? "El suplente mas usado de la liga fue:" : "Los suplentes mas usados de la liga fueron:");
        for (Jugador jugadorSuplente : listaDeSuplentesMasUsados) {
            System.err.println(jugadorSuplente.getNombre());
        }
    }

    @Override
    public int cantidadDePartidosJugadosPorUnEquipo( Equipo equipo) {
        int cantidadDePartidos = 0;
        if (equipo == null) {
            return 0;
        }

        for (Partido partido : partidos) {
            if (partido.getEquipo1().equals(equipo) || partido.getEquipo2().equals(equipo)) {
                cantidadDePartidos++;
            }
        }
        return cantidadDePartidos;
    }
    
    @Override
    public int cantidadDeGolesEnLaLiga(Equipo equipo) {
        int cantidadDeGolesEnLaLiga = 0;
        if (equipo == null) {
            return 0;
        }

        for (Partido partido : partidos) {
            if (partido.getEquipo1().equals(equipo)) {
                cantidadDeGolesEnLaLiga += partido.getResultadoPorEquipo(equipo);
            } else if (partido.getEquipo2().equals(equipo)) {
                cantidadDeGolesEnLaLiga += partido.getResultadoPorEquipo(equipo);
            }
        }
        
        return cantidadDeGolesEnLaLiga;
    }
}
