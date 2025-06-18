package com.informatorio.salidaUtils;

import java.util.List;

public class MostrarMenu {
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
    
    public static void menuPrincipal(){
        System.out.println("\n\t *************************************************");
        System.out.println("\t\t  Menu principal");
        System.out.println("\n\t *************************************************");
        System.out.printf("%-40s%-40s%n", "OPCION", "SECCION");
        int contador =1;
        for (String opcion : OPCIONES_MENU_PRINCIPAL) {
            System.out.printf("%-40d%-40s%n",
                    contador, opcion);
            contador++;
        }
        System.out.printf("%nPresione 0 para salir.%n");
    }
    public static void menuEquipos(){
        System.out.println("\n\t *************************************************");
        System.out.println("\t\t  Menu equipos");
        System.out.println("\n\t *************************************************");
        System.out.printf("%-40s%-40s%n", "OPCION", "SECCION");
        int contador =1;
        for (String opcion : OPCIONES_MENU_EQUIPOS) {
            System.out.printf("%-40d%-40s%n",
                    contador, opcion);
            contador++;
        }
        System.out.printf("%nPresione 0 para volver al menu principal.%n");
    }

    public static void menuPartidos() {
        System.out.println("\n\t *************************************************");
        System.out.println("\t\t  Menu partidos");
        System.out.println("\n\t *************************************************");
        System.out.printf("%-40s%-40s%n", "OPCION", "SECCION");
        int contador = 1;
        for (String opcion : OPCIONES_MENU_PARTIDOS) {
            System.out.printf("%-40d%-40s%n",
                    contador, opcion);
            contador++;
        }
        System.out.printf("%nPresione 0 para volver al menu principal.%n");
    }
    public static void menuPartidoEnProgreso(){
        System.out.println("\n\t *************************************************");
        System.out.println("\t\t  Menu partido en progreso");
        System.out.println("\n\t *************************************************");
        System.out.printf("%-40s%-40s%n", "OPCION", "SECCION");
        int contador =1;
        for (String opcion : OPCIONES_MENU_PARTIDO_EN_PROGRESO) {
            System.out.printf("%-40d%-40s%n",
                    contador, opcion);
            contador++;
        }
        System.out.printf("%nPresione 0 para volver al anterior y finalizar el partido.%n");
    }
    public static void menuEstadisticas(){
        System.out.println("\n\t *************************************************");
        System.out.println("\t\t  Menu estadísticas y reportes");
        System.out.println("\n\t *************************************************");
        System.out.printf("%-40s%-40s%n", "OPCION", "SECCION");
        int contador =1;
        for (String opcion : OPCIONES_MENU_ESTADISTICAS_Y_REPORTES) {
            System.out.printf("%-40d%-40s%n",
                    contador, opcion);
            contador++;
        }
        System.out.printf("%nPresione 0 para volver al menu principal.%n");
    }
}
