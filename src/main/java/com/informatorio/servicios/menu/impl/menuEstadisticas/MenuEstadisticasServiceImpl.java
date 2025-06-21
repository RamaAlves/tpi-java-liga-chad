package com.informatorio.servicios.menu.impl.menuEstadisticas;

import com.informatorio.dominio.Partido;
import com.informatorio.entradaUtils.LectorAccionesUsuario;
import com.informatorio.salidaUtils.MostrarMenu;
import com.informatorio.servicios.evaluadorDeEstadisticas.EvaluadorDeEstadisticasService;
import com.informatorio.servicios.menu.MenuService;

public class MenuEstadisticasServiceImpl implements MenuService {
    private EvaluadorDeEstadisticasService evaluadorDeEstadisticas;
    
    private LectorAccionesUsuario scannerAcciones;

    public MenuEstadisticasServiceImpl(  EvaluadorDeEstadisticasService evaluadorDeEstadisticas,LectorAccionesUsuario scannerAcciones) {
        this.evaluadorDeEstadisticas = evaluadorDeEstadisticas;
        this.scannerAcciones = scannerAcciones;
    }
     
    @Override
    public int seleccionarOpcionMenu() {
        int condition = 0;
        do {
            MostrarMenu.menuEstadisticas();
            condition = scannerAcciones.ingresarOpMenu(7);
            ejecutarOpcion(condition);
        } while (condition != 0);
        return condition;
    }public int seleccionarOpcionMenu(Partido partido) {
        int condition = 0;
        System.out.println("Implementacion no válida");
        return condition;
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                System.out.println("\n");
                evaluadorDeEstadisticas.mostrarGoleadores();
                System.out.println("\n");
                break;
            case 2:
                System.out.println("\n");
                evaluadorDeEstadisticas.mostrarPromedioDeGoles();
                System.out.println("\n");
                break;
            case 3:
                System.out.println("\n");
                evaluadorDeEstadisticas.mostrarRankingDeEquiposPorGoles();
                System.out.println("\n");
                break;
            case 4:
                System.out.println("\n");
                evaluadorDeEstadisticas.listarJugadoresSinJugar();
                System.out.println("\n");
                break;
            case 5:
                System.out.println("\n");
                evaluadorDeEstadisticas.listarTitularesConMasMinutos();
                System.out.println("\n");
                break;
            case 6:
                System.out.println("\n");
                evaluadorDeEstadisticas.reporteGeneral();
                System.out.println("\n");
                break;
            case 7:
                System.out.println("\n");
                evaluadorDeEstadisticas.reporteEquipoPorTerminal();
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
