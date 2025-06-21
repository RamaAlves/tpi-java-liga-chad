package com.informatorio.servicios.menu.impl.menuPartidos;

import com.informatorio.dominio.Partido;
import com.informatorio.entradaUtils.LectorAccionesUsuario;
import com.informatorio.salidaUtils.MostrarMenu;
import com.informatorio.servicios.administradorDeLiga.AdministradorDeLigaService;
import com.informatorio.servicios.menu.MenuService;
import com.informatorio.servicios.menu.impl.menuPartidos.menuPartidoEnProgreso.MenuPartidoEnProgresoServiceImpl;

public class MenuPartidosServiceImpl implements MenuService {
    private AdministradorDeLigaService administradorDeLiga;
    private LectorAccionesUsuario scannerAcciones;
    private MenuService menuPartidoEnProgreso;

    public MenuPartidosServiceImpl( AdministradorDeLigaService administradorDeLiga,LectorAccionesUsuario scannerAcciones) {
        this.administradorDeLiga = administradorDeLiga;
        this.scannerAcciones = scannerAcciones;
        this.menuPartidoEnProgreso = new MenuPartidoEnProgresoServiceImpl(administradorDeLiga,scannerAcciones);
    }
     
    @Override
    public int seleccionarOpcionMenu() {
        int condition = 0;
        do {
            MostrarMenu.menuPartidos();
            condition = scannerAcciones.ingresarOpMenu(2);
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
                administradorDeLiga.listarPartidos();
                System.out.println("\n");
                break;
            case 2:
                System.out.println("\n");
                administradorDeLiga.registrarEquipoPorTerminal();
                Partido partidoEnJuego = administradorDeLiga.getPartidoEnCurso();
                if (partidoEnJuego != null) {
                    ((MenuPartidoEnProgresoServiceImpl) menuPartidoEnProgreso).seleccionarOpcionMenu(partidoEnJuego);
                }
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
