package com.informatorio.servicios.menu.impl;

import com.informatorio.dominio.Equipo;
import com.informatorio.dominio.Partido;
import com.informatorio.entradaUtils.LectorAccionesUsuario;
import com.informatorio.salidaUtils.MostrarMenu;
import com.informatorio.servicios.administradorDeLiga.AdministradorDeLigaService;
import com.informatorio.servicios.archivos.ArchivosService;
import com.informatorio.servicios.evaluadorDeEstadisticas.EvaluadorDeEstadisticasService;
import com.informatorio.servicios.menu.MenuService;
import com.informatorio.servicios.menu.impl.menuEquipos.MenuEquiposServiceImpl;
import com.informatorio.servicios.menu.impl.menuEstadisticas.MenuEstadisticasServiceImpl;
import com.informatorio.servicios.menu.impl.menuPartidos.MenuPartidosServiceImpl;

public class MenuPrincipalServiceImpl implements MenuService {
    
    private AdministradorDeLigaService administradorDeLiga;
    private ArchivosService generadorDeCSV; 
    private LectorAccionesUsuario scannerAcciones;
    private MenuService menuEquipos, menuPartidos, menuEstadisticas;
    
    public MenuPrincipalServiceImpl( AdministradorDeLigaService administradorDeLiga, EvaluadorDeEstadisticasService evaluadorDeEstadisticas, ArchivosService generadorDeCSV, LectorAccionesUsuario scannerAcciones) {
        this.administradorDeLiga = administradorDeLiga;
        this.generadorDeCSV = generadorDeCSV;
        this.scannerAcciones = scannerAcciones;
        this.menuEquipos = new MenuEquiposServiceImpl(administradorDeLiga,scannerAcciones);
        this.menuPartidos = new MenuPartidosServiceImpl(administradorDeLiga, scannerAcciones);
        this.menuEstadisticas = new MenuEstadisticasServiceImpl(evaluadorDeEstadisticas, scannerAcciones);
        
    }

    @Override
    public int seleccionarOpcionMenu() {
        int condition = 0;
        do {
            MostrarMenu.menuPrincipal();
            condition = scannerAcciones.ingresarOpMenu(4);
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
                menuEquipos.seleccionarOpcionMenu();
                System.out.println("\n");
                break;
            case 2:
                System.out.println("\n");
                menuPartidos.seleccionarOpcionMenu();
                System.out.println("\n");
                break;
            case 3:
                System.out.println("\n");
                menuEstadisticas.seleccionarOpcionMenu();
                System.out.println("\n");
                break;
            case 4:
                System.out.println("\n");
                System.out.println("Exportando datos en CSV");
                Equipo equipoElegido = administradorDeLiga.getEquipoPorNombre(scannerAcciones.ingresarNombreEquipo());
                generadorDeCSV.exportarEquipoCSV(equipoElegido);
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
