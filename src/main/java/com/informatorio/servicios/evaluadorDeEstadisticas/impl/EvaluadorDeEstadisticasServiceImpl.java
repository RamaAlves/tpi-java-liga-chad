package com.informatorio.servicios.evaluadorDeEstadisticas.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.informatorio.dominio.Equipo;
import com.informatorio.dominio.Jugador;
import com.informatorio.dominio.JugadorSuplente;
import com.informatorio.dominio.JugadorTitular;
import com.informatorio.dominio.Partido;
import com.informatorio.entradaUtils.LectorAccionesUsuario;
import com.informatorio.servicios.administradorDeLiga.AdministradorDeLigaService;
import com.informatorio.servicios.evaluadorDeEstadisticas.EvaluadorDeEstadisticasService;

public class EvaluadorDeEstadisticasServiceImpl implements EvaluadorDeEstadisticasService {
    private Set<Equipo> equipos;
    private List<Partido> partidos;
    private AdministradorDeLigaService administradorDeLiga;
    private LectorAccionesUsuario scannerAcciones;

    public EvaluadorDeEstadisticasServiceImpl(Set<Equipo> equipos, List<Partido> partidos, AdministradorDeLigaService administradorDeLiga, LectorAccionesUsuario scannerAcciones){
        this.equipos = equipos;
        this.partidos = partidos;
        this.administradorDeLiga = administradorDeLiga;
        this.scannerAcciones = scannerAcciones;
    };

    @Override
    public void reporteGeneral() {
        System.out.println("-------------------------------");
        System.out.println("\tREPORTE GENERAL");
        System.out.println("-------------------------------");
        System.err.println("Total de goles en la liga: " + totalDeGolesDeLiga());
        System.out.println();
        mostrarGoleadores();
        System.out.println();
        List<JugadorTitular> titularesMasEficientes = getTitularesMasEficientes();
        if (titularesMasEficientes.size() > 0) {
            System.out.println(titularesMasEficientes.size() > 1 ? "Los titulares mas eficientes fueron: "
                    : "El titular mas eficiente fue: ");
            int contador = 1;
            for (Jugador jugador : titularesMasEficientes) {
                System.err.println(contador+"- "+jugador.getNombre());
                contador++;
            }
        } else {
            System.out.println("Ningun titular sobresalió!");
        }
        System.out.println();
        List<JugadorSuplente> suplentesMasEficientes = getSuplentesMasEficientes();
        if (suplentesMasEficientes.size() > 0) {
            System.out.println(suplentesMasEficientes.size() > 1 ? "Los suplentes mas eficientes fueron: "
                    : "El suplente mas eficiente fue: ");
            int contador = 1;
            for (Jugador jugador : suplentesMasEficientes) {
                System.err.println(contador+"- "+jugador.getNombre());
            }
        } else {
            System.out.println("Ningún suplente sobresalió!");
        }
        System.out.println();
        System.out.println("El equipo con mayor cantidad de goles fue: "+rankingDeEquiposPorGoles().get(0).getKey().getNombre());
    }

    @Override
    public void reporteEquipo(Equipo equipo) {
        List<JugadorTitular> titularesConMasMinutos = equipo.getJugadoresConMasMinutos();
        List<JugadorSuplente> suplentesMasUsados = equipo.getSuplentesMasUsado();
        System.out.println("---------------------------------------------------");
        System.out.println("Mostrando reporte de equipo: " + equipo.getNombre());
        System.out.println("---------------------------------------------------");
        System.out.println("Promedio de goles de jugadores: ");
        System.out.printf(
                "De un total de %d jugadores y un total de %d goles el promedio de goles por jugador es de %.2f%n",
                equipo.getTotalDeJugadores(), equipo.getTotalDeGolesDeJugadores(),
                equipo.getPromedioGolesPorJugadores());
        if (equipo.getJugadoresSinGoles().size() > 0) {
            System.out.println("De los jugadores registrados en el equipo los siguientes no anotaron goles: ");
            int contador = 1;
            for (Jugador jugador : equipo.getJugadoresSinGoles()) {
                System.out.println(contador+"- " + jugador.getNombre());
                contador++;
            }
        } else {
            System.out.println("Todos los jugadores registrados en este equipo anotaron goles en la liga.");
        }

        if (titularesConMasMinutos.size() > 0) {
            System.out.println(titularesConMasMinutos.size() > 1
            ? "Los jugadores titulares con mas minutos registrados son:"
                    : "El jugador titular con mas minutos registrados del equipo es: ");
            int contador = 1;
            for (Jugador jugador : titularesConMasMinutos) {
                System.out.println(contador + "- " + jugador.getNombre());
                contador++;
            }
        } else {
            System.out.println("No se registraron jugadores con minutos cargados.");
        }
        if (suplentesMasUsados.size() > 0) {
            System.out.println(suplentesMasUsados.size() > 1 ? "Los suplentes mas utilizados fueron: "
                    : "El suplente mas utilizado fue: ");
            int contador = 1;
            for (JugadorSuplente jugador : suplentesMasUsados) {
                System.out.println(contador+"- "+jugador.getNombre());
                contador++;
            }
        } else {
            System.out.println("No se registró el uso de suplentes.");
        }
    }

    @Override
    public void reporteEquipoPorTerminal() {
        System.out.println("Elija el equipo del cual quiere obtener el reporte:");
        Equipo equipoElegido = null;
        int contadorErrores = 0;
        while (equipoElegido == null && contadorErrores<3) {
            administradorDeLiga.listarEquipos();
            equipoElegido = administradorDeLiga.getEquipoPorNombre(scannerAcciones.ingresarNombreEquipo());
            if (equipoElegido != null) {
                reporteEquipo(equipoElegido);
            } else {
                System.out.println("El nombre de equipo ingresado no fue encontrado");
                contadorErrores++;
            }
        }
        if(contadorErrores>=3){
            System.out.println("Cantidad maxima de intentos superada. Vuelva a intentarlo");
        }
    }

    @Override
    public List<Jugador> getGoleadores(){
        List<Jugador> goleadores = new ArrayList<Jugador>();
        for (Equipo equipo : equipos) {
            goleadores.addAll(equipo.getMejoresJugadores());
        }
        List<Jugador> mejoresJugadores = new ArrayList<Jugador>(); //seguir aca 
        int maximoDeGoles = 0;
        for (Jugador jugador : goleadores) {
            int golesJugador = jugador.getCantidadDeGoles();
            if (golesJugador > maximoDeGoles) {
                mejoresJugadores.clear();
                mejoresJugadores.add(jugador);
                maximoDeGoles = golesJugador;
            } else if (golesJugador == maximoDeGoles &&maximoDeGoles>0) {
                mejoresJugadores.add(jugador);
            }
        } 
        return mejoresJugadores;
    }

    @Override
    public void mostrarGoleadores() {
        List<Jugador> goleadores = getGoleadores();
        if (goleadores.size()==0) {
            System.out.println("No hay jugadores que hayan registrado goles en la liga.");
            return;
        }
        System.out.println(goleadores.size() == 1 ? "El goleador de la liga fue:" : "Los goleadores de la liga fueron:");
        int contador = 1;
        for (Jugador goleador : goleadores) {
            System.err.println(contador+"- "+goleador.getNombre());
            contador++;
        }
    }
    
    @Override
    public void mostrarPromedioDeGoles() {

        System.err.println("Mostrando promedio de goles por equipo:");
        System.err.printf("%-20s%-26s%-26s%-26s%-26s%-26s%n",
                "Equipo",
                "Total goles por equipo",
                "Cantidad jugadores",
                "Promedio goles p/jugador",
                "Cantidad de partidos",
                "Promedio goles p/partidos");
        for (Equipo equipo : equipos) {
            int cantidadDePartidosJugados = cantidadDePartidosJugadosPorUnEquipo(equipo);
            int cantidadDeGolesEquipo = cantidadDeGolesEnLaLiga(equipo);
            double promedioGolesPartidos;
            if (cantidadDePartidosJugados>0){
                promedioGolesPartidos = cantidadDeGolesEquipo / cantidadDePartidosJugados;
            } else {
                promedioGolesPartidos = 0.0;
            }
            System.err.printf("%-20s%-26d%-26d%-26.2f%-26d%-26d%n",
                    equipo.getNombre(),
                    cantidadDeGolesEquipo,
                    equipo.getTotalDeJugadores(),
                    equipo.getPromedioGolesPorJugadores(),
                    cantidadDePartidosJugados,
                    promedioGolesPartidos);
        }
        System.err.println("Mostrando promedio de goles de la liga:");
        System.out.printf("%-40s%-30s%-30s%-30s%n", "Total de equipos", "Partidos jugados", "Goles realizados",
                "Promedio");
                double promedioGolesLiga;
            if ( partidos.size()>0){
                promedioGolesLiga = totalDeGolesDeLiga()  / partidos.size();
            } else {
                promedioGolesLiga = 0.0;
            }
        System.out.printf("%-40d%-30d%-30d%-30.2f%n", equipos.size(), partidos.size(), totalDeGolesDeLiga(),
                promedioGolesLiga);

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

    @Override
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
        double mayorPorcentajeEficiencia = 0.0;
        for (JugadorSuplente jugador : listaDeJugadoresSuplentes) {
            double porcentajeJugador = jugador.porcentajeEficiencia();
            if (porcentajeJugador > mayorPorcentajeEficiencia) {
                listaDeSuplentesEficientes.clear();
                listaDeSuplentesEficientes.add(jugador);
                mayorPorcentajeEficiencia = porcentajeJugador;
            } else if (porcentajeJugador == mayorPorcentajeEficiencia&& mayorPorcentajeEficiencia>0.0) {
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
        
        double mayorPorcentajeEficiencia = 0.0;
        for (JugadorTitular jugador : listaDeJugadoresTitulares) {
            double porcentajeJugador = jugador.porcentajeEficiencia();
            if (porcentajeJugador > mayorPorcentajeEficiencia) {
                listaDeTitularesEficientes.clear();
                listaDeTitularesEficientes.add(jugador);
                mayorPorcentajeEficiencia = porcentajeJugador;
            } else if (porcentajeJugador == mayorPorcentajeEficiencia&&mayorPorcentajeEficiencia>0.0) {
                listaDeTitularesEficientes.add(jugador);
            }
        }
        return listaDeTitularesEficientes;
    }
    @Override
    public List<JugadorTitular> getTitularesConMasMinutos() {
        List<JugadorTitular> listaDeJugadoresTitulares = new ArrayList<JugadorTitular>();

        for (Equipo equipo : equipos) {
            listaDeJugadoresTitulares.addAll(equipo.getJugadoresConMasMinutos());
        }

        List<JugadorTitular> listaDeTitularesConMasMinutos = new ArrayList<JugadorTitular>();
        int minutosMaximos = 0;
        for (JugadorTitular jugador : listaDeJugadoresTitulares) {
            if (jugador.getMinutosJugados() > minutosMaximos) {
                listaDeTitularesConMasMinutos.clear();
                listaDeTitularesConMasMinutos.add(jugador);
                minutosMaximos = jugador.getMinutosJugados();
            } else if (jugador.getMinutosJugados() == minutosMaximos&& minutosMaximos>0) {
                listaDeTitularesConMasMinutos.add(jugador);
            }
        }
        return listaDeTitularesConMasMinutos;
    }
    @Override
    public void listarTitularesConMasMinutos() {
        System.out.println("Listando jugadores con mas minutos de juego en la liga por equipo:");
        int contadorLista = 0;
        for (Jugador jugador : getTitularesConMasMinutos()) {
                    contadorLista++;
                    System.out.println(contadorLista + ". " + jugador.getNombre());
            }
        System.out.println("----------------------Fin de lista de jugadores----------------------");
    }

    @Override
    public void listarJugadoresSinJugar() {
        System.out.println("Listando jugadores sin minutos de juego en la liga por equipo:");
        for (Equipo equipo : equipos) {
            if (equipo.getListaDeJugadoresSinJugar().size() > 0) {
                System.out.println("Equipo: " + equipo.getNombre());
                int contadorLista = 0;
                for (JugadorSuplente jugador : equipo.getListaDeJugadoresSinJugar()) {
                    contadorLista++;
                    System.out.println(contadorLista + ". " + jugador.getNombre());
                }
            }
        }
        System.out.println("----------------------Fin de lista de equipos----------------------");
    }

    @Override
    public void mostrarSuplenteMasUsado() {
        ArrayList<JugadorSuplente> listaDeSuplentesMasUsados = new ArrayList<JugadorSuplente>();
        int maximoPartidosIngresados = -1;
        for (Equipo equipo : equipos) {
            if (equipo.getSuplentesMasUsado().getLast().getPartidosIngresados() > maximoPartidosIngresados) {
                maximoPartidosIngresados = equipo.getSuplentesMasUsado().getLast().getPartidosIngresados();
                listaDeSuplentesMasUsados.clear();
                listaDeSuplentesMasUsados.addAll(equipo.getSuplentesMasUsado());
            } else if(equipo.getSuplentesMasUsado().getLast().getPartidosIngresados()== maximoPartidosIngresados) {
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
