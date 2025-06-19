package com.informatorio.dominio;

public class JugadorSuplente extends Jugador {
    private int partidosIngresados;

    public JugadorSuplente(String nombre, int edad, int numeroRegistro) {
        super(nombre, edad, numeroRegistro);
        this.partidosIngresados = 0;
    }

    public int getPartidosIngresados() {
        return partidosIngresados;
    }

    public void setPartidosIngresados(int nuevosPartidosIngresados) {
        this.partidosIngresados = nuevosPartidosIngresados;
    }
    
    public void addPartidoIngresado() {
        this.partidosIngresados = getPartidosIngresados()+1;
    }

    @Override
    public boolean esTitular() {
        return false;
    }

    @Override
    public String getRol() {
        return "Suplente";
    }

    @Override
    public double porcentajeEficiencia() {
        int goles = getCantidadDeGoles();
        int partidosJugados = getPartidosIngresados();
        if (partidosJugados <= 0) {
            return 0.0;
        }
        double porcentajeEficiencia=(goles / partidosJugados) * 100;
        return porcentajeEficiencia;
    }
    
    @Override
    public void salirDeLaCancha(int minutos) {
        salirDeLaCancha();
    }

    public void salirDeLaCancha() {
        setEstado(false);
        System.out.printf("Jugador suplente: %s sale de la cancha.", getNombre());
    }
    
    @Override
    public void entrarALaCancha() {
        addPartidoIngresado();
        this.setEstado(true);
        System.out.printf("Jugador suplente: %s entra a la cancha.", getNombre());
    }    

}
