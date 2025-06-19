package com.informatorio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.informatorio.dominio.Equipo;
import com.informatorio.dominio.JugadorSuplente;
import com.informatorio.dominio.JugadorTitular;
import com.informatorio.dominio.Partido;
import com.informatorio.entradaUtils.LectorAccionesUsuario;
import com.informatorio.salidaUtils.MostrarMenu;
import com.informatorio.servicios.administradorDeLiga.AdministradorDeLigaService;
import com.informatorio.servicios.administradorDeLiga.impl.AdministradorDeLigaServiceImpl;
import com.informatorio.servicios.evaluadorDeEstadisticas.EvaluadorDeEstadisticasService;
import com.informatorio.servicios.evaluadorDeEstadisticas.impl.EvaluadorDeEstadisticasServiceImpl;

public class App {
    public static void main(String[] args) {
        Set<Equipo> equipos = new HashSet<Equipo>();
        List<Partido> partidos = new ArrayList<>();
        LectorAccionesUsuario lectorUsuario = new LectorAccionesUsuario(new Scanner(System.in));
        AdministradorDeLigaService administradorDeLiga = new AdministradorDeLigaServiceImpl(equipos, partidos,
        lectorUsuario);
        EvaluadorDeEstadisticasService evaluadorDeEstadisticas = new EvaluadorDeEstadisticasServiceImpl(equipos, partidos,administradorDeLiga,lectorUsuario);
        
        
        administradorDeLiga.registrarEquipo(new Equipo("Boca"));
        administradorDeLiga.registrarEquipo(new Equipo("River"));
        administradorDeLiga.registrarEquipo(new Equipo("San Lorenzo"));
        administradorDeLiga.registrarEquipo(new Equipo("Independiente"));
        // Jugadores para Boca
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("Boca"), new JugadorTitular("Facundo", 25, 1001));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("Boca"), new JugadorTitular("Martín", 28, 1002));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("Boca"), new JugadorTitular("Pablo", 22, 1003));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("Boca"), new JugadorTitular("Lucas", 30, 1004));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("Boca"), new JugadorTitular("Diego", 27, 1005));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("Boca"), new JugadorSuplente("Suplente1", 27, 1089));

        // Jugadores para River
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("River"), new JugadorTitular("Gonzalo", 26, 1006));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("River"), new JugadorTitular("Ignacio", 24, 1007));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("River"), new JugadorTitular("Federico", 29, 1008));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("River"), new JugadorTitular("Sebastián", 23, 1009));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("River"), new JugadorTitular("Nicolás", 31, 1010));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("River"), new JugadorSuplente("Alonso", 31, 1097));

        // Jugadores para San Lorenzo
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("San Lorenzo"), new JugadorTitular("Alejandro", 27, 1011));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("San Lorenzo"), new JugadorTitular("Javier", 25, 1012));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("San Lorenzo"), new JugadorTitular("Gustavo", 28, 1013));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("San Lorenzo"), new JugadorTitular("Emiliano", 22, 1014));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("San Lorenzo"), new JugadorTitular("Ricardo", 29, 1015));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("San Lorenzo"), new JugadorSuplente("Raul", 29, 1094));

        // Jugadores para Independiente
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("Independiente"), new JugadorTitular("Andrés", 26, 1016));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("Independiente"), new JugadorTitular("Gastón", 24, 1017));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("Independiente"), new JugadorTitular("Franco", 30, 1018));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("Independiente"), new JugadorTitular("Cristian", 23, 1019));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("Independiente"),
                new JugadorTitular("Esteban", 32, 1020));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("Independiente"),
                new JugadorSuplente("Gerardo", 32, 1054));
        
        List<Equipo> equiposPartido1 = new ArrayList<>();
        equiposPartido1.add(administradorDeLiga.getEquipoPorNombre("Independiente"));
        equiposPartido1.add(administradorDeLiga.getEquipoPorNombre("Boca"));
        administradorDeLiga.registrarPartido(equiposPartido1);
        Partido partido1 = administradorDeLiga.getPartidoEnCurso();
        administradorDeLiga.registrarGolEnPartido(partido1, administradorDeLiga.getEquipoPorNombre("Independiente").getJugarorPorNombre("Cristian"), 2);
        administradorDeLiga.registrarGolEnPartido(partido1, administradorDeLiga.getEquipoPorNombre("Independiente").getJugarorPorNombre("Franco"), 1);
        administradorDeLiga.registrarGolEnPartido(partido1, administradorDeLiga.getEquipoPorNombre("Boca").getJugarorPorNombre("Diego"), 4);
        administradorDeLiga.registrarGolEnPartido(partido1,
        administradorDeLiga.getEquipoPorNombre("Boca").getJugarorPorNombre("Facundo"), 1);
        administradorDeLiga.cambiarJugador(administradorDeLiga.getEquipoPorNombre("Boca").getJugarorPorNombre("Suplente1"),administradorDeLiga.getEquipoPorNombre("Boca").getJugarorPorNombre("Diego"), 45);
        administradorDeLiga.registrarGolEnPartido(partido1,
        administradorDeLiga.getEquipoPorNombre("Boca").getJugarorPorNombre("Suplente1"), 2);
        administradorDeLiga.finalizarPartido(partido1);

        List<Equipo> equiposPartido2 = new ArrayList<>();
        equiposPartido2.add(administradorDeLiga.getEquipoPorNombre("River"));
        equiposPartido2.add(administradorDeLiga.getEquipoPorNombre("San Lorenzo"));
        administradorDeLiga.registrarPartido(equiposPartido2);
        Partido partido2 = administradorDeLiga.getPartidoEnCurso();
        administradorDeLiga.registrarGolEnPartido(partido2, administradorDeLiga.getEquipoPorNombre("River").getJugarorPorNombre("Gonzalo"), 2);
        administradorDeLiga.registrarGolEnPartido(partido2, administradorDeLiga.getEquipoPorNombre("River").getJugarorPorNombre("Federico"), 3);
        administradorDeLiga.registrarGolEnPartido(partido2, administradorDeLiga.getEquipoPorNombre("San Lorenzo").getJugarorPorNombre("Ricardo"), 2);
        administradorDeLiga.registrarGolEnPartido(partido2,
        administradorDeLiga.getEquipoPorNombre("San Lorenzo").getJugarorPorNombre("Emiliano"), 3);
        administradorDeLiga.cambiarJugador(administradorDeLiga.getEquipoPorNombre("River").getJugarorPorNombre("Alonso"),administradorDeLiga.getEquipoPorNombre("River").getJugarorPorNombre("Federico"), 80);
        administradorDeLiga.registrarGolEnPartido(partido2,
        administradorDeLiga.getEquipoPorNombre("River").getJugarorPorNombre("Alonso"), 3);
        administradorDeLiga.finalizarPartido(partido2);
        
        System.out.println("Bienvenido a la Liga Chad!!!");
        int opMenuPrincipal = 0;
        int opSubMenu = 0;
        int opSubMenuPartido = 0;
        do {
            MostrarMenu.menuPrincipal();
            opMenuPrincipal = lectorUsuario.ingresarOpMenu(4);
            //equipos
            if (opMenuPrincipal == 1) {
                do {
                    MostrarMenu.menuEquipos();
                    opSubMenu = lectorUsuario.ingresarOpMenu(6);
                    if (opSubMenu == 1) {
                        administradorDeLiga.listarEquipos();
                    }
                    if (opSubMenu == 2) {
                        administradorDeLiga.registrarEquipoPorTerminal();
                    }
                    if (opSubMenu == 3) {
                        administradorDeLiga.listarJugadoresEnLaLiga();
                    }
                    if (opSubMenu == 4) {
                        administradorDeLiga.listarJugadoresDeUnEquipo();
                    }
                    if (opSubMenu == 5) {
                        administradorDeLiga.registrarJugadorPorTerminal();
                    }
                    if (opSubMenu == 6) {
                        administradorDeLiga.transferirJugadoresPorTerminal();
                    }
                } while (opSubMenu!=0);
            }
            //partidos
            if (opMenuPrincipal == 2) {
                do {
                    MostrarMenu.menuPartidos();
                    opSubMenu = lectorUsuario.ingresarOpMenu(2);                    
                    if (opSubMenu == 1) {
                        administradorDeLiga.listarPartidos();
                    }
                    if (opSubMenu == 2) {
                        administradorDeLiga.registrarPartidoPorTerminal();
                        Partido partidoEnJuego = administradorDeLiga.getPartidoEnCurso();
                        if (partidoEnJuego != null) {
                            do {
                                MostrarMenu.menuPartidoEnProgreso();
                                opSubMenuPartido = lectorUsuario.ingresarOpMenu(3);
                                if (opSubMenuPartido == 1) {
                                    administradorDeLiga.cambiarJugadorPorTerminal(partidoEnJuego);
                                }
                                if (opSubMenuPartido == 2) {
                                    administradorDeLiga.registrarGolPorTerminal(partidoEnJuego);
                                }
                                if (opSubMenuPartido == 3) {
                                    administradorDeLiga.eliminarGolPorTerminal(partidoEnJuego);
                                }
                            } while (opSubMenuPartido != 0);
                            administradorDeLiga.finalizarPartido(partidoEnJuego);
                        }
                    }
                } while (opSubMenu!=0);
            }
            if (opMenuPrincipal == 3) {
                do {
                    MostrarMenu.menuEstadisticas();
                    opSubMenu = lectorUsuario.ingresarOpMenu(7);
                    if (opSubMenu == 1) {
                        evaluadorDeEstadisticas.mostrarGoleadores();
                    }
                    if (opSubMenu == 2) {
                        evaluadorDeEstadisticas.mostrarPromedioDeGoles();
                    }
                    if (opSubMenu == 3) {
                        evaluadorDeEstadisticas.mostrarRankingDeEquiposPorGoles();
                    }
                    if (opSubMenu == 4) {
                        evaluadorDeEstadisticas.listarJugadoresSinJugar();
                    }
                    if (opSubMenu == 5) {
                        evaluadorDeEstadisticas.listarTitularesConMasMinutos();
                    }
                    if (opSubMenu == 6) {
                        evaluadorDeEstadisticas.reporteGeneral();
                    }
                    if (opSubMenu == 7) {
                        evaluadorDeEstadisticas.reporteEquipoPorTerminal();
                    }
                } while (opSubMenu!=0);
            }
            if (opMenuPrincipal == 4) {
                System.out.println("Exportando datos en CSV");
                //llamar a exportar CSV
            }
        } while (opMenuPrincipal != 0);
        System.out.println("Gracias por usar la App!");
    }
}
