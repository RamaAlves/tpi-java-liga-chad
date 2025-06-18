package com.informatorio.entradaUtils;

import java.util.Scanner;

import com.informatorio.dominio.Equipo;
import com.informatorio.dominio.Jugador;
import com.informatorio.dominio.JugadorSuplente;
import com.informatorio.dominio.JugadorTitular;

public class LectorAccionesUsuario {
    private Scanner scanner;

    public LectorAccionesUsuario(Scanner scanner) {
        this.scanner = scanner;
    }

    public Jugador crearJugador() {
        System.out.println("Ingrese el nombre del jugador a registrar: ");
        String nombre = leerNombreValido();
        System.out.println("Ingrese la edad del jugador a registrar: ");
        int edad = leerEdadValida();
        System.out.println("Ingrese el número de registro del jugador. (Mínimo 4 digitos)");
        int numeroRegistro = leerNumeroRegistroValido();
        System.out.printf("Seleccione el tipo de jugador a cargar:%n1. Titular%n2. Suplente%n");
        System.out.println("Eliga un numero.");
        int tipo = leerTipoJugadorValido();
        if (tipo == 1) {
            return new JugadorTitular(nombre, edad,numeroRegistro);
        } else {
            return new JugadorSuplente(nombre, edad, numeroRegistro);
        }
    }

    public Equipo crearEquipo() {
        System.out.println("Ingrese el nombre del equipo a registrar");
        String nombre = leerNombreValido();
        return new Equipo(nombre);
    }
    
    public String ingresarNombreEquipo() {
        System.out.println("Ingrese el nombre de un equipo registrado:");
        return leerNombreValido();
    }

    public String ingresarNombreJugador() {
        System.out.println("Ingrese el nombre del jugador:");
        return leerNombreValido();
    }

    public int ingresarGoles() {
        System.out.println("Ingrese los goles anotados:");
        return leerGolValido();
    }

    public int ingresarMinutosDelCambio() {
        System.out.println("Ingrese los minutos a los que se realiza el cambio:");
        return leerMinutosValido();
    }

    public int ingresarOpMenu(int maximo) {
        System.out.println("Elija la opcion del menu a la que desea ingresar:");
        return leerOpcionMenu(maximo);
    }

    private int leerTipoJugadorValido() {
        int tipo = 0;
        while (tipo != 1 && tipo != 2) {
            try {
                tipo = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Debe ingresar un tipo valido: ");
            }
        }
        return tipo;
    }

    private int leerEdadValida() {
        int edad = -1;

        while (edad < 16) {
            try {
                edad = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(
                        "Entrada invalida.");
            }
            if (edad < 16) {
                System.out.println("Ingrese una edad válida. Los jugadores deben tener al menos 16 años.");
            }
        }
        return edad;
    }

    private int leerNumeroRegistroValido() {
        int numeroRegistro = -1;
        String ingresoUsuario;
        while (numeroRegistro < 999) {
            try {
                ingresoUsuario = scanner.nextLine();
                if (ingresoUsuario.length() < 4) {
                    continue;
                }
                numeroRegistro = Integer.parseInt(ingresoUsuario);
                 if (numeroRegistro < 0) { 
                    System.out.printf("Entrada no válida. El número de registro no puede ser negativo.%n");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.printf(
                        "Entrada no válida. Ingrese una numero registro valido.%n El número de registro debe tener 4 dígitos como mínimo.%n");
            }
        }
        return numeroRegistro;
    }
    
    private String leerNombreValido() {
        String nombre = "";

        while (nombre.length() < 3) {
            try {
                nombre = scanner.nextLine();
                if (nombre.length() < 3) {
                    System.out.printf(
                            "Entrada no válida. Ingrese un nombre válido.%n Los nombres deben tener al menos 3 caracteres.%n");
                }
            } catch (Exception e) { // Captura cualquier tipo de excepción
                System.out.printf(
                        "Ocurrió un error al leer la entrada. Por favor, intente de nuevo.%n Los nombres deben tener al menos 3 caracteres.%n");
                nombre = "";
            }
        }
        return nombre;
    }
    
    private int leerGolValido() {
        int gol = 0;

        while (gol <= 0) {
            try {
                gol = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(
                        "Entrada no válida.");
            }
            if (gol <= 0) {
                System.out.println("Para acumular goles debe ingresar un numero mayor a 0.");
            }
        }
        return gol;
    }

    private int leerMinutosValido() {
        int minutos = 0;

        while (minutos <= 0) {
            try {
                minutos = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(
                        "Entrada no válida. Para acumular minutos debe ingresar un numero mayor a 0.");
            }
        }
        return minutos;
    }
    
    private int leerOpcionMenu(int maximo) {
        int opMenu = -1;

        while (opMenu < 0 || opMenu>maximo) {
            try {
                opMenu = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(
                        "Entrada no válida.");
            }
            if (opMenu < 0 && opMenu > maximo) {
                System.out.println("Elija una opción de menú válida");
            }
        }
        return opMenu;
    }
}
