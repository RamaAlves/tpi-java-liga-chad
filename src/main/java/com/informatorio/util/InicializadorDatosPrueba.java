package com.informatorio.util;

import java.util.ArrayList;
import java.util.List;

import com.informatorio.dominio.Equipo;
import com.informatorio.dominio.JugadorSuplente;
import com.informatorio.dominio.JugadorTitular;
import com.informatorio.dominio.Partido;
import com.informatorio.servicios.administradorDeLiga.AdministradorDeLigaService;

public class InicializadorDatosPrueba {
    public static void cargarDatos(AdministradorDeLigaService administradorDeLiga) {
        // Registro de Equipos
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
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("Independiente"), new JugadorTitular("Esteban", 32, 1020));
        administradorDeLiga.registrarJugador(administradorDeLiga.getEquipoPorNombre("Independiente"), new JugadorSuplente("Gerardo", 32, 1054));

        // Partidos de prueba
        List<Equipo> equiposPartido1 = new ArrayList<>();
        equiposPartido1.add(administradorDeLiga.getEquipoPorNombre("Independiente"));
        equiposPartido1.add(administradorDeLiga.getEquipoPorNombre("Boca"));
        administradorDeLiga.registrarPartido(equiposPartido1);
        Partido partido1 = administradorDeLiga.getPartidoEnCurso();
        administradorDeLiga.registrarGolEnPartido(partido1, administradorDeLiga.getEquipoPorNombre("Independiente").getJugarorPorNombre("Cristian"), 2);
        administradorDeLiga.registrarGolEnPartido(partido1, administradorDeLiga.getEquipoPorNombre("Independiente").getJugarorPorNombre("Franco"), 1);
        administradorDeLiga.registrarGolEnPartido(partido1, administradorDeLiga.getEquipoPorNombre("Boca").getJugarorPorNombre("Diego"), 4);
        administradorDeLiga.registrarGolEnPartido(partido1, administradorDeLiga.getEquipoPorNombre("Boca").getJugarorPorNombre("Facundo"), 1);
        administradorDeLiga.cambiarJugador(administradorDeLiga.getEquipoPorNombre("Boca").getJugarorPorNombre("Suplente1"), administradorDeLiga.getEquipoPorNombre("Boca").getJugarorPorNombre("Diego"), 45);
        administradorDeLiga.registrarGolEnPartido(partido1, administradorDeLiga.getEquipoPorNombre("Boca").getJugarorPorNombre("Suplente1"), 2);
        administradorDeLiga.finalizarPartido(partido1);

        List<Equipo> equiposPartido2 = new ArrayList<>();
        equiposPartido2.add(administradorDeLiga.getEquipoPorNombre("River"));
        equiposPartido2.add(administradorDeLiga.getEquipoPorNombre("San Lorenzo"));
        administradorDeLiga.registrarPartido(equiposPartido2);
        Partido partido2 = administradorDeLiga.getPartidoEnCurso();
        administradorDeLiga.registrarGolEnPartido(partido2, administradorDeLiga.getEquipoPorNombre("River").getJugarorPorNombre("Gonzalo"), 2);
        administradorDeLiga.registrarGolEnPartido(partido2, administradorDeLiga.getEquipoPorNombre("River").getJugarorPorNombre("Federico"), 3);
        administradorDeLiga.registrarGolEnPartido(partido2, administradorDeLiga.getEquipoPorNombre("San Lorenzo").getJugarorPorNombre("Ricardo"), 2);
        administradorDeLiga.registrarGolEnPartido(partido2, administradorDeLiga.getEquipoPorNombre("San Lorenzo").getJugarorPorNombre("Emiliano"), 3);
        administradorDeLiga.cambiarJugador(administradorDeLiga.getEquipoPorNombre("River").getJugarorPorNombre("Alonso"), administradorDeLiga.getEquipoPorNombre("River").getJugarorPorNombre("Federico"), 80);
        administradorDeLiga.registrarGolEnPartido(partido2, administradorDeLiga.getEquipoPorNombre("River").getJugarorPorNombre("Alonso"), 3);
        administradorDeLiga.finalizarPartido(partido2);
    }
}

