package com.informatorio.servicios.menu.impl.menuPartidos.menuPartidoEnProgreso;

import com.informatorio.dominio.Partido;
import com.informatorio.entradaUtils.LectorAccionesUsuario;
import com.informatorio.salidaUtils.MostrarMenu;
import com.informatorio.servicios.administradorDeLiga.AdministradorDeLigaService;
import com.informatorio.servicios.menu.MenuService;

public class MenuPartidoEnProgresoServiceImpl implements MenuService {
    private AdministradorDeLigaService administradorDeLiga;
    private LectorAccionesUsuario scannerAcciones;

    public MenuPartidoEnProgresoServiceImpl(AdministradorDeLigaService administradorDeLiga,
            LectorAccionesUsuario scannerAcciones) {
        this.administradorDeLiga = administradorDeLiga;
        this.scannerAcciones = scannerAcciones;
    }
     
    @Override
    public int seleccionarOpcionMenu() {
        int condition = 0;
        System.out.println("Implementacion no válida");
        return condition;
    }
    public int seleccionarOpcionMenu(Partido partidoEnJuego) {
        int condition = 0;
        do {
            MostrarMenu.menuPartidoEnProgreso();
            condition = scannerAcciones.ingresarOpMenu(2);
            ejecutarOpcion(condition, partidoEnJuego);
        } while (condition != 0);
        administradorDeLiga.finalizarPartido(partidoEnJuego);
        return condition;
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        System.out.println("Implementacion no válida");
        }
    public void ejecutarOpcion(int opcion, Partido partidoEnJuego) {
        switch (opcion) {
            case 1:
                System.out.println("\n");
                administradorDeLiga.cambiarJugadorPorTerminal(partidoEnJuego);
                System.out.println("\n");
                break;
            case 2:
                System.out.println("\n");
                administradorDeLiga.registrarGolPorTerminal(partidoEnJuego);
                System.out.println("\n");
                break;
            case 3:
                System.out.println("\n");
                administradorDeLiga.eliminarGolPorTerminal(partidoEnJuego);
                System.out.println("\n");
                break;
            default:
                if (opcion != 0) {
                    System.out.println("Opción no válida.");
                }
                break;
        }
    }
}