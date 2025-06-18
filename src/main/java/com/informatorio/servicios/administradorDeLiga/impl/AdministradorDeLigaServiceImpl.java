package com.informatorio.servicios.administradorDeLiga.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.informatorio.dominio.Equipo;
import com.informatorio.dominio.Jugador;
import com.informatorio.dominio.JugadorTitular;
import com.informatorio.dominio.Partido;
import com.informatorio.entradaUtils.LectorAccionesUsuario;
import com.informatorio.servicios.administradorDeLiga.AdministradorDeLigaService;

public class AdministradorDeLigaServiceImpl implements AdministradorDeLigaService {
    private Set<Equipo> equipos;
    private List<Partido> partidos;
    private LectorAccionesUsuario scannerAcciones;

    public AdministradorDeLigaServiceImpl(Set<Equipo> equipos, List<Partido> partidos, LectorAccionesUsuario scannerAcciones){
        this.equipos = equipos;
        this.partidos = partidos;
        this.scannerAcciones = scannerAcciones;
    };


    @Override
    public void listarPartidos() {
        if (partidos.isEmpty()) {
            System.out.println("No hay partidos cargados en la liga");
            return;
        }
        System.out.println("Los partidos cargados en la liga son:");
        int contador = 1;
        for (Partido partido : partidos) {
            System.out.println("Partido " + contador + ":");
            System.out.println("----Resultados del partido----");
            System.out.printf("%s %d------%d %s%n",
                partido.getEquipo1().getNombre(),
                partido.getResultadoPorEquipo(partido.getEquipo1()),
                partido.getResultadoPorEquipo(partido.getEquipo2()),
                partido.getEquipo2().getNombre());
            System.out.println("----Goles Anotados por Jugador----");
            partido.getGolesAnotadosPorJugadores().forEach((jugador, goles) ->
            System.out.println("- " + jugador.getNombre()+ ": " + goles + " goles")
            );
            System.out.println("----------------------------------");
            contador++;
        }
    };


    @Override
    public void listarEquipos() {
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos inscriptos en la liga");
            return;
        }
        System.out.println("Los equipos inscriptos en la liga son:");
        int contador = 1;
        for (Equipo equipo : equipos) {
            System.out.println(contador + " - " + equipo.getNombre());
            contador++;
        }
    };

    @Override
    public void listarEquipos(List<Equipo> equiposAListar, String mensajePersonalizado) {
        String mensajeFinal;

        if (mensajePersonalizado == null || mensajePersonalizado.trim().isEmpty()) {
            mensajeFinal = "Los equipos disponibles son:"; 
        } else {
            mensajeFinal = mensajePersonalizado;
        }
        
        if (equiposAListar.isEmpty()) {
            System.out.println("No hay equipos que listar.");
            return;
        }

        System.out.println(mensajeFinal);

        for (Equipo equipo : equiposAListar) {
            System.out.println(equipo.getNombre());
        }
    };

    @Override
    public void listarJugadoresEnLaLiga() {
        System.out.println("Listando jugadores en la liga por equipo:");
        for (Equipo equipo : equipos) {
            mostrarJugadores(equipo);
        }
        System.out.println("----------------------Fin de lista de equipos----------------------");
    }

    @Override
    public void listarJugadoresDeUnEquipo() {
        if (equipos.size() > 0) {
            listarEquipos();
            System.out.println("Elija el equipo:");
            Equipo equipoElegido=null;
            while (equipoElegido == null) {
                equipoElegido = getEquipoPorNombre(scannerAcciones.ingresarNombreEquipo());
                if (equipoElegido != null) {
                    mostrarJugadores(equipoElegido);
                } else {
                    System.out.println("No se encontro el equipo indicado");
                }
            }
        } else {
            System.out.println("Para ver jugadores de un equipo primero debe cargar un equipo");
        }
    }
    @Override
    public Equipo getEquipoPorNombre(String nombreEquipo) {
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equals(nombreEquipo)) {
                return equipo;
            }
        }
        return null;
    }

    @Override
    public void registrarEquipo(Equipo equipo) {
        if (equipo == null) {
            System.out.println("No se puede registrar un equipo nulo.");
        }
        if (equipos.add(equipo)) {
            System.out.printf("Equipo: %s registrado con exito en la liga!", equipo.getNombre());
        } else {
            System.out.printf("Equipo %s ya existe en la liga y no se puede registrar nuevamente", equipo.getNombre());
        }
    };

    @Override
    public void registrarEquipoPorTerminal() {
        System.out.println("Registrando equipo:");
        Equipo nuevoEquipo = scannerAcciones.crearEquipo();
        registrarEquipo(nuevoEquipo);
    }

    @Override
    public void registrarJugador(Equipo equipo, Jugador jugador) {
        if (equipo == null || jugador == null) {
            System.out.println("No se puede registrar judagor si equipo o jugador es nulo.");
        }
        equipo.registrarJugador(jugador);
    };

    @Override
    public void registrarJugadorPorTerminal() {
        if (equipos.size() > 0) {
            listarEquipos();
            System.out.println("Elija el equipo:");
            Equipo equipoElegido = null;
            while (equipoElegido == null) {
                equipoElegido = getEquipoPorNombre(scannerAcciones.ingresarNombreEquipo());
                if (equipoElegido != null) {
                    System.out.println("Registrando jugador:");
                    Jugador nuevoJugador = scannerAcciones.crearJugador();
                    registrarJugador(equipoElegido, nuevoJugador);
                } else {
                    System.out.println("No se encontro el equipo indicado. Carga cancelada.");
                    break;
                }
            }
        } else {
            System.out.println("Para cargar jugadores en un equipo primero debe cargar un equipo");
        }

    }

    @Override
    public Partido getPartidoEnCurso() {
        Partido partidoEnCurso = null;
        for (Partido partido : partidos) {
            if (!partido.getFinalizado()) {
                partidoEnCurso = partido;
            }
        }
        return partidoEnCurso;
    }
    
    @Override
    public void registrarPartido(List<Equipo> equipos) {
        System.out.println("Registrando partido:");
        Partido nuevoPartido = new Partido(equipos);
        partidos.add(nuevoPartido);
    };

    @Override
    public void registrarPartidoPorTerminal() {
        List<Equipo> equiposEnJuego = new ArrayList<Equipo>();
        listarEquipos();
        int contador = 1;
        while (equiposEnJuego.size() < 2&&contador<=3) {
            Equipo nuevoEquipo = getEquipoPorNombre(scannerAcciones.ingresarNombreEquipo());
            if (nuevoEquipo != null && nuevoEquipo.listoParaJugar()) {
                if (equiposEnJuego.size() > 0 && equiposEnJuego.get(0) == nuevoEquipo) {
                        System.out.println("No puede seleccionar 2 veces el mismo equipo.");
                        contador++;
                    } else {
                        equiposEnJuego.add(nuevoEquipo);
                    }
            } else {
                System.out.println(
                        "El nombre de equipo ingresado no fue encontrado o el equipo no esta listo para jugar.");
                        contador++;
            }
        }
        if (contador > 3) {
            System.out.println("Cantidad de intentos superada. Operación cancelada.");
        } else {
            registrarPartido(equiposEnJuego);
        }
    }

    @Override
    public void registrarGolEnPartido(Partido partido, Jugador jugador, int goles) {
        partido.addGolesAnotadosPorJugador(jugador, goles);
        for (int i = 0; i < goles; i++) {
            jugador.hacerGol();
        }
    }

    @Override
    public void registrarGolPorTerminal(Partido partido) {
        System.out.println("Elija el equipo que hizo el gol:");
        Equipo equipoElegido = null;
        Jugador jugadorElegido = null;
        int goles;
        int contadorErrores = 1;
        while (equipoElegido == null) {
            listarEquipos(partido.getEquipos(), "Equipos en juego:");
            equipoElegido = getEquipoPorNombre(scannerAcciones.ingresarNombreEquipo());
            if (equipoElegido != null) {
                while (jugadorElegido == null && contadorErrores <= 3) {
                    System.out.println("Elija el jugador que hizo el gol:");
                    for (Jugador jugador : equipoElegido.getListaDeJugadores()) {
                        if (jugador.getEstado()) {
                            System.out.println(jugador.getNombre());
                        }
                    }
                    jugadorElegido = equipoElegido.getJugarorPorNombre(scannerAcciones.ingresarNombreJugador());
                    if (jugadorElegido != null && jugadorElegido.getEstado()) {
                        goles = scannerAcciones.ingresarGoles();
                        registrarGolEnPartido(partido, jugadorElegido, goles);
                    } else {
                        System.out.println("Elija un jugador valido.");
                    }
                    contadorErrores++;
                }
                if (contadorErrores > 3) {
                    System.out.println("Operacion cancelada cantidad máxima de errores permitidos superados");
                    return;
                }
            } else {
                System.out.println("El nombre de equipo ingresado no fue encontrado");
                contadorErrores++;
            }
            if (contadorErrores > 3) {
                System.out.println("Operacion cancelada cantidad máxima de errores permitidos superados");
                return;
            }
        }
    }
    
    @Override
    public void eliminarGolEnPartido(Partido partido, Jugador jugador) {
        int goles = partido.getGolesAnotadosPorJugador(jugador);
        partido.removeGolesAnotadosPorJugador(jugador);
        for (int i = 0; i < goles; i++) {
            jugador.quitarGol();
        }
    }

    @Override
    public void eliminarGolPorTerminal(Partido partido) {
        System.out.println("Elija el equipo al que quiere quitar goles:");
        Equipo equipoElegido = null;
        Jugador jugadorElegido = null;
        List<Jugador> jugadoresConGoles;
        while (equipoElegido == null) {
            listarEquipos(partido.getEquipos(), "Equipos en juego:");
            equipoElegido = getEquipoPorNombre(scannerAcciones.ingresarNombreEquipo());
            if (equipoElegido != null) {
                jugadoresConGoles = jugadoresConGolesDeEquipoEnPartido(partido, equipoElegido);
                if (jugadoresConGoles.size() > 0) {
                    while (jugadorElegido == null) {
                        System.out.println("Elija el jugador al que quiere quitarle los goles:");
                        for (Jugador jugador : jugadoresConGoles) {
                            System.out.println(jugador.getNombre());
                        }
                        jugadorElegido = equipoElegido.getJugarorPorNombre(scannerAcciones.ingresarNombreJugador());
                        if (jugadorElegido != null && jugadoresConGoles.contains(jugadorElegido)) {
                            partido.removeGolesAnotadosPorJugador(jugadorElegido);
                        } else {
                            System.out.println("Elija un jugador valido.");
                        }
                    }
                } else {
                    System.out.println("No hay jugadores que hayan anotado goles.");
                }
            } else {
                System.out.println("El nombre de equipo ingresado no fue encontrado");
            }
        }
    }
    

    
    public List<Jugador> jugadoresConGolesDeEquipoEnPartido(Partido partido,Equipo equipo) {
        List<Jugador> listaJugadoresConGoles = new ArrayList<Jugador>();
        for (Jugador jugador : equipo.getListaDeJugadores()) {
            if (jugador.getEstado() && partido.getGolesAnotadosPorJugador(jugador) > 0) {
                listaJugadoresConGoles.add(jugador);
            }
        }
        return listaJugadoresConGoles;
    }

    @Override
    public void mostrarJugadores(Equipo equipo) {
        if (equipo.getListaDeJugadores().size() == 0) {
            System.out.println("Equipo sin jugadores");
            return;
        }
        System.out.printf("Los jugadores del equipo %s son:%n", equipo.getNombre());
        int contador = 0;
        System.out.printf("%-10s|%-15s|%-10s%n", "POSICION","NOMBRE","ROL");
        for (Jugador jugador : equipo.getListaDeJugadores()) {
            contador++;
            System.out.printf("%-10s|%-15s|%-10s%n", contador, jugador.getNombre(), jugador.getRol());
        }
    };

    @Override
    public void cambiarJugador(Jugador jugadorEntrante, Jugador jugadorSaliente, int minutos) {
        jugadorSaliente.salirDeLaCancha(minutos);
        jugadorEntrante.entrarALaCancha();
    };

    @Override
    public void cambiarJugadorPorTerminal(Partido partido) {
        System.out.println("Elija el equipo en el que quiere intercambiar jugadores:");
        Equipo equipoElegido = null;
        Jugador jugadorSaliente = null;
        Jugador jugadorEntrante = null;
        int minutos = 0;
        while (equipoElegido == null) {
            listarEquipos(partido.getEquipos(), "Equipos en juego:");
            equipoElegido = getEquipoPorNombre(scannerAcciones.ingresarNombreEquipo());
            if (equipoElegido != null) {
                while (jugadorSaliente == null) {
                    System.out.println("Elija el jugador que saldrá de la cancha:");
                    for (Jugador jugador : equipoElegido.getListaDeJugadores()) {
                        if (jugador.getEstado()) {
                            System.out.println(jugador.getNombre());
                        }
                    }
                    jugadorSaliente = equipoElegido.getJugarorPorNombre(scannerAcciones.ingresarNombreJugador());
                    if (jugadorSaliente == null || !jugadorSaliente.getEstado()) {
                        System.out.println(
                                "Elija un jugador válido. No puede seleccionar un jugador que no está en juego.");
                    }
                }
                while (jugadorEntrante == null) {
                    System.out.println("Elija el jugador que ingresará a la cancha:");
                    for (Jugador jugador : equipoElegido.getListaDeJugadores()) {
                        if (!jugador.getEstado()) {
                            System.out.println(jugador.getNombre());
                        }
                    }
                    jugadorEntrante = equipoElegido.getJugarorPorNombre(scannerAcciones.ingresarNombreJugador());
                    if (jugadorEntrante == null || jugadorEntrante.getEstado()) {
                        System.out.println(
                                "Elija un jugador válido. No puede seleccionar un jugador que ya está en juego.");
                    }
                }
                minutos = scannerAcciones.ingresarMinutosDelCambio();
                cambiarJugador(jugadorEntrante, jugadorSaliente, minutos);
            } else {
                System.out.println("El nombre de equipo ingresado no fue encontrado");
            }
        }
    }

    @Override
    public void finalizarPartido(Partido partido) {
        Map<Equipo, Integer> resultadoEquipos = new HashMap<Equipo, Integer>();
        for (Equipo equipo : partido.getEquipos()) {
            cargarMinutosJugadosATitulares(equipo, 90);
            int acumuladorEquipo = 0;
            for (Jugador jugador : equipo.getListaDeJugadores()) {
                acumuladorEquipo += partido.getGolesAnotadosPorJugador(jugador);
            }
            resultadoEquipos.put(equipo, acumuladorEquipo);
        }
        partido.setResultado(resultadoEquipos);
        partido.setFinalizado(true);
    }
    
    @Override
    public void cargarMinutosJugadosATitulares(Equipo equipo, int minutos) {
        for (JugadorTitular jugador : equipo.getListaDeJugadoresTitulares()) {
            if (jugador.getEstado()) {
                jugador.salirDeLaCancha(minutos);
            }
        }
    }


    @Override
    public void transferirJugadores(Equipo equipoEmisor, Equipo equipoReceptor, Jugador jugadorSaliente,
            Jugador jugadorEntrante) {
        System.out.println("Comenzando transferencia...");
        System.out.printf("Se intercambiará a %s del equipo %s por %s del equipo %s%n",
                jugadorSaliente.getNombre(),
                equipoEmisor.getNombre(),
                jugadorEntrante.getNombre(),
                equipoReceptor.getNombre());
        equipoEmisor.eliminarJugador(jugadorSaliente);
        equipoEmisor.registrarJugador(jugadorEntrante);
        equipoReceptor.eliminarJugador(jugadorEntrante);
        equipoReceptor.registrarJugador(jugadorSaliente);
    }

    @Override
    public void transferirJugadoresPorTerminal() {
        Equipo equipoEmisor = null;
        Equipo equipoReceptor= null;
        Jugador jugadorSaliente= null; 
        Jugador jugadorEntrante= null;

        System.out.println("Al transferir jugadores a otro equipo deberá recibir un jugador en su equipo.");
        System.out.println(
                "Tenga en cuenta que para jugar partidos a la hora de comenzar debe tener al menos 5 jugadores titulares en su nomina.");
        System.out.println("Tenga en cuenta que el equipo emisor no puede ser igual que el receptor");
        System.out.println("Seleccione el equipo del cual se transferirá el jugador.");
        listarEquipos();
        if (equipos.size() < 2) {
            System.out.println("Para continuar primero debe cargar al menos 2 equipos.");
            return;
        }
        while (equipoEmisor == null) {
            Equipo nuevoEquipo = getEquipoPorNombre(scannerAcciones.ingresarNombreEquipo());
            if (nuevoEquipo != null) {
                equipoEmisor = nuevoEquipo;
            } else {
                System.out.println("El nombre de equipo ingresado no fue encontrado o no está disponible.");
            }
        }
        System.out.println("Seleccione el equipo al cual se transferirá el jugador.");
        while (equipoReceptor == null) {
            Equipo nuevoEquipo = getEquipoPorNombre(scannerAcciones.ingresarNombreEquipo());
            if (nuevoEquipo != null && nuevoEquipo != equipoEmisor) {
                equipoReceptor = nuevoEquipo;
            } else {
                System.out.println("El nombre de equipo ingresado no fue encontrado o no está disponible.");
            }
        }
        if (equipoEmisor.getListaDeJugadores().size() == 0) {
            System.out.printf("El equipo %s no cuenta con jugadores suficientes para continuar%n", equipoEmisor.getNombre());
            return;
        }
        if (equipoReceptor.getListaDeJugadores().size() == 0) {
            System.out.printf("El equipo %s no cuenta con jugadores suficientes para continuar%n", equipoReceptor.getNombre());
            return;
        }
        while (jugadorSaliente == null) {
            mostrarJugadores(equipoEmisor);
            System.out.println("Elija el jugador que será transferido:");
            jugadorSaliente = equipoEmisor.getJugarorPorNombre(scannerAcciones.ingresarNombreJugador());
            if (jugadorSaliente == null) {
                System.out.println("Elija un jugador válido.");
            }
        }
        while (jugadorEntrante == null) {
            mostrarJugadores(equipoReceptor);
            System.out.println("Elija el jugador que será recibido:");
            jugadorEntrante = equipoReceptor.getJugarorPorNombre(scannerAcciones.ingresarNombreJugador());
            if (jugadorEntrante == null) {
                System.out.println(
                        "Elija un jugador válido.");
            }
        }

        transferirJugadores(equipoEmisor, equipoReceptor, jugadorSaliente, jugadorEntrante);
        System.out.println("Jugadores transferidos con éxito");
    };
}
