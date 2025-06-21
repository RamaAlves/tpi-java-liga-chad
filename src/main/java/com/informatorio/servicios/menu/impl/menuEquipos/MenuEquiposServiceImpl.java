package com.informatorio.servicios.menu.impl.menuEquipos;

import com.informatorio.dominio.Partido;
import com.informatorio.entradaUtils.LectorAccionesUsuario;
import com.informatorio.salidaUtils.MostrarMenu;
import com.informatorio.servicios.administradorDeLiga.AdministradorDeLigaService;
import com.informatorio.servicios.menu.MenuService;

public class MenuEquiposServiceImpl implements MenuService {
    
    private AdministradorDeLigaService administradorDeLiga;
    private LectorAccionesUsuario scannerAcciones;

    public MenuEquiposServiceImpl( AdministradorDeLigaService administradorDeLiga,LectorAccionesUsuario scannerAcciones) {
        this.administradorDeLiga = administradorDeLiga;
        this.scannerAcciones = scannerAcciones;
    }
     
    @Override
    public int seleccionarOpcionMenu() {
        int condition = 0;
        do {
            MostrarMenu.menuEquipos();
            condition = scannerAcciones.ingresarOpMenu(6);
            ejecutarOpcion(condition);
        } while (condition != 0);
        return condition;
    }
    public int seleccionarOpcionMenu(Partido partido) {
        int condition = 0;
        System.out.println("Implementacion no válida");
        return condition;
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                System.out.println("\n");
                administradorDeLiga.listarEquipos();
                System.out.println("\n");
                break;
            case 2:
                System.out.println("\n");
                administradorDeLiga.registrarEquipoPorTerminal();
                System.out.println("\n");
                break;
            case 3:
                System.out.println("\n");
                administradorDeLiga.listarJugadoresEnLaLiga();
                break;
            case 4:
                System.out.println("\n");
                administradorDeLiga.listarJugadoresDeUnEquipo();
                System.out.println("\n");
                break;
            case 5:
                System.out.println("\n");
                administradorDeLiga.registrarJugadorPorTerminal();
                System.out.println("\n");
                break;
            case 6:
                System.out.println("\n");
                administradorDeLiga.transferirJugadoresPorTerminal();
                System.out.println("\n");
                break;
            default:
                if (opcion != 0) {
                    System.out.println("Opción no válida.");
                }
                break;
        }
    }
    public void ejecutarOpcion(int opcion, Partido partido) {
        System.out.println("Implementacion no válida");
    }
}
