package com.informatorio.constantes;

import java.util.List;

public class OpcionesMenusConstantes {
    public static final List<String> OPCIONES_MENU_PRINCIPAL = List.of(
        "Administrar equipos",
        "Administrar partidos",
        "Ir a estadisticas y reportes",
        "Exportar CSV");
    
    public static final List<String> OPCIONES_MENU_EQUIPOS = List.of(
        "Listar equipos",
        "Cargar equipo",
        "Listar todos los jugadores",
        "Listar jugadores de un equipo",
        "Cargar jugador",
        "Transferir jugador (Intercambiar)"
    );
    
    public static final List<String> OPCIONES_MENU_PARTIDOS = List.of(
        "Listar partidos",
        "Cargar partido"
    );
    public static final List<String> OPCIONES_MENU_PARTIDO_EN_PROGRESO = List.of(
        "Cambiar jugador",
        "Registrar gol",
        "Eliminar gol"
    );

    public static final List<String> OPCIONES_MENU_ESTADISTICAS_Y_REPORTES = List.of(
        "Goleador de la liga",
        "Promedio de goles",
        "Ranking de equipos por goles",
        "Listar suplentes sin ingresos",
        "Jugadores con mas minutos jugados",
        "Reporte general de liga",
        "Reporte de equipo"
    );
}
