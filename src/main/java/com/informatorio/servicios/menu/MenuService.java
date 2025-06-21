package com.informatorio.servicios.menu;

import com.informatorio.dominio.Partido;

public interface MenuService {
    int seleccionarOpcionMenu();
    int seleccionarOpcionMenu(Partido partido);
    void ejecutarOpcion(int opcion);
    void ejecutarOpcion(int opcion, Partido partido);
}
