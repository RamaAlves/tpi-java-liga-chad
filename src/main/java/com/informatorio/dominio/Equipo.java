package com.informatorio.dominio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Equipo {
    private String nombre;
    private Set<Jugador> listaDeJugadores;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.listaDeJugadores = new HashSet<Jugador>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Jugador> getListaDeJugadores() {
        return listaDeJugadores;
    }

    public void setListaDeJugadores(Set<Jugador> listaDeJugadores) {
        this.listaDeJugadores = listaDeJugadores;
    }

    public void registrarJugador(Jugador jugador) {
        if (jugador == null) {
            System.out.println("No se puede registrar un jugador nulo.");
        }
        if (listaDeJugadores.add(jugador)) {
            System.out.printf("%s registrado con exito en el equipo!%nSu número de registro es %d%n", jugador.getNombre(), jugador.getNumeroRegistro());
        } else {
            System.out.printf("Ya existe en el equipo un jugador registrado con el numero %d.%n No se puede registrar a %s%n",jugador.getNumeroRegistro(), jugador.getNombre());
        }
    }

    public Jugador getJugarorPorNombre(String nombre) {
        for (Jugador jugador : listaDeJugadores) {
            if (jugador.getNombre().equals(nombre)) {
                return jugador;
            }
        }
        return null;
    }

    public void eliminarJugador(Jugador jugador) {
        listaDeJugadores.remove(jugador);
    }

    public ArrayList<JugadorTitular> getListaDeJugadoresTitulares() {
        ArrayList<JugadorTitular> listaDeJugadoresTitulares = new ArrayList<JugadorTitular>();

        for (Jugador jugador : listaDeJugadores) {
            if (jugador.esTitular()) {
                listaDeJugadoresTitulares.add((JugadorTitular) jugador);
            }
        }
        return listaDeJugadoresTitulares;
    }
    
    public ArrayList<JugadorSuplente> getListaDeJugadoresSuplentes() {
        ArrayList<JugadorSuplente> listaDeJugadoresSuplentes = new ArrayList<JugadorSuplente>();

        for (Jugador jugador : listaDeJugadores) {
            if (!jugador.esTitular()) {
                listaDeJugadoresSuplentes.add((JugadorSuplente) jugador);
            }
        }
        return listaDeJugadoresSuplentes;
    }

    public ArrayList<JugadorSuplente> getListaDeJugadoresSinJugar() {
        ArrayList<JugadorSuplente> listaDeJugadoresSinJugar = new ArrayList<JugadorSuplente>();
        for (JugadorSuplente jugadorSuplente : getListaDeJugadoresSuplentes()) {
            if (jugadorSuplente.getPartidosIngresados()==0) {
                listaDeJugadoresSinJugar.add(jugadorSuplente);
            }
        }
        return listaDeJugadoresSinJugar;
    }
    
    public List<Jugador> getMejoresJugadores() {
        List<Jugador> mejoresJugadores = new ArrayList<Jugador>();
        int maximoDeGoles = 0;
        for (Jugador jugador : listaDeJugadores) {
            int golesJugador = jugador.getCantidadDeGoles();
            if (golesJugador > maximoDeGoles) {
                mejoresJugadores.clear();
                mejoresJugadores.add(jugador);
                maximoDeGoles = golesJugador;
            } else if (golesJugador == maximoDeGoles &&maximoDeGoles>0) {
                mejoresJugadores.add(jugador);
            }
        }
        return mejoresJugadores;
    }

    public List<JugadorSuplente> getSuplentesMasEficiente() {
        List<JugadorSuplente> jugadoresMasEficientes = new ArrayList<JugadorSuplente>();
        double mayorPorcentajeEficiencia = 0.0;
        for (JugadorSuplente jugador : getListaDeJugadoresSuplentes()) {
            double porcentajeJugador = jugador.porcentajeEficiencia();
            if (porcentajeJugador > mayorPorcentajeEficiencia) {
                jugadoresMasEficientes.clear();
                jugadoresMasEficientes.add(jugador);
                mayorPorcentajeEficiencia = porcentajeJugador;
            } else if (porcentajeJugador == mayorPorcentajeEficiencia&&mayorPorcentajeEficiencia>0.0) {
                jugadoresMasEficientes.add(jugador);
            }
        }
        return jugadoresMasEficientes;
    }

    public List<JugadorTitular> getTitularesMasEficiente() {
        List<JugadorTitular> jugadoresMasEficientes = new ArrayList<JugadorTitular>();
        double mayorPorcentajeEficiencia = 0.0;
        for (JugadorTitular jugador : getListaDeJugadoresTitulares()) {
            double porcentajeJugador = jugador.porcentajeEficiencia();
            if (porcentajeJugador > mayorPorcentajeEficiencia) {
                jugadoresMasEficientes.clear();
                jugadoresMasEficientes.add(jugador);
                mayorPorcentajeEficiencia = porcentajeJugador;
            } else if (porcentajeJugador == mayorPorcentajeEficiencia&&mayorPorcentajeEficiencia>0.0) {
                jugadoresMasEficientes.add(jugador);
            }
        }
        return jugadoresMasEficientes;
    }
    
    public List<JugadorTitular> getJugadoresConMasMinutos() {
        ArrayList<JugadorTitular> listaDeJugadoresTitulares = getListaDeJugadoresTitulares();
        ArrayList<JugadorTitular> jugadoresConMasMinutos = new ArrayList<>();
        int minutosMaximos = 0;
        for (JugadorTitular jugador : listaDeJugadoresTitulares) {
            if (jugador.getMinutosJugados() > minutosMaximos) {
                jugadoresConMasMinutos.clear();
                jugadoresConMasMinutos.add(jugador);
                minutosMaximos = jugador.getMinutosJugados();
            } else if (jugador.getMinutosJugados() == minutosMaximos&&minutosMaximos>0) {
                jugadoresConMasMinutos.add(jugador);
            }
        }
        return jugadoresConMasMinutos;
    }

    public ArrayList<Jugador> getJugadoresSinGoles() {
        ArrayList<Jugador> jugadoresSinGoles = new ArrayList<Jugador>();
        for (Jugador jugador : listaDeJugadores) {
            if (jugador.getCantidadDeGoles() == 0) {
                jugadoresSinGoles.add(jugador);
            }
        }
        return jugadoresSinGoles;
    }
    //corregir
    public List<JugadorSuplente> getSuplentesMasUsado() {
        ArrayList<JugadorSuplente> listaDeSuplentesMasUsados = new ArrayList<JugadorSuplente>();
        int masIngresos = 0;
        for (JugadorSuplente jugador : getListaDeJugadoresSuplentes()) {
            int ingresosJugador = jugador.getPartidosIngresados();
            if (ingresosJugador > masIngresos) {
                listaDeSuplentesMasUsados.clear();
                listaDeSuplentesMasUsados.add(jugador);
                masIngresos = ingresosJugador;
            } else if (ingresosJugador == masIngresos && masIngresos>0) {
                listaDeSuplentesMasUsados.add(jugador);
            }
        }
        return listaDeSuplentesMasUsados;
    }
    
    public void comienzaElJuego() {
        for (Jugador jugador : getListaDeJugadoresTitulares()) {
            jugador.entrarALaCancha();
        }
    }

    public int getTotalDeGolesDeJugadores() {
        int totalDeGoles = 0;
        for (Jugador jugador : listaDeJugadores) {
            totalDeGoles = totalDeGoles + jugador.getCantidadDeGoles();
        }
        return totalDeGoles;
    }

    public int getTotalDeJugadores() {
        return listaDeJugadores.size();
    }
    
    public double getPromedioGolesPorJugadores() {
        return getTotalDeGolesDeJugadores() / getTotalDeJugadores();
    }

    public boolean listoParaJugar() {
        return getListaDeJugadoresTitulares().size() >= 5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return Objects.equals(nombre, equipo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre); 
    }
}
