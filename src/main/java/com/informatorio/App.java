package com.informatorio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.informatorio.dominio.Equipo;
import com.informatorio.dominio.Partido;
import com.informatorio.entradaUtils.LectorAccionesUsuario;
import com.informatorio.servicios.administradorDeLiga.AdministradorDeLigaService;
import com.informatorio.servicios.administradorDeLiga.impl.AdministradorDeLigaServiceImpl;
import com.informatorio.servicios.archivos.ArchivosService;
import com.informatorio.servicios.archivos.impl.ArchivosServiceImpl;
import com.informatorio.servicios.evaluadorDeEstadisticas.EvaluadorDeEstadisticasService;
import com.informatorio.servicios.evaluadorDeEstadisticas.impl.EvaluadorDeEstadisticasServiceImpl;
import com.informatorio.servicios.menu.MenuService;
import com.informatorio.servicios.menu.impl.MenuPrincipalServiceImpl;
import com.informatorio.util.InicializadorDatosPrueba;

public class App {
    public static void main(String[] args) {
        Set<Equipo> equipos = new HashSet<Equipo>();
        List<Partido> partidos = new ArrayList<>();
        LectorAccionesUsuario lectorUsuario = new LectorAccionesUsuario(new Scanner(System.in));
        AdministradorDeLigaService administradorDeLiga = new AdministradorDeLigaServiceImpl(equipos, partidos,
        lectorUsuario);
        EvaluadorDeEstadisticasService evaluadorDeEstadisticas = new EvaluadorDeEstadisticasServiceImpl(equipos, partidos,administradorDeLiga,lectorUsuario);
        ArchivosService generadorDeCSV = new ArchivosServiceImpl();
        MenuService menuPrincipal = new MenuPrincipalServiceImpl(administradorDeLiga,
                evaluadorDeEstadisticas, generadorDeCSV, lectorUsuario);
        InicializadorDatosPrueba.cargarDatos(administradorDeLiga);
        System.out.println("Bienvenido a la Liga Chad!!!");
        menuPrincipal.seleccionarOpcionMenu();
        System.out.println("Gracias por usar la App!");
    }
}
