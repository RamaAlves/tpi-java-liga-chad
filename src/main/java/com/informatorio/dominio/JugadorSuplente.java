package com.informatorio.dominio;

public class JugadorSuplente extends Jugador {
    private int partidosIngresados;

    public JugadorSuplente(String nombre, int edad) {
        super(nombre, edad, 0);
        this.partidosIngresados = 0;
    }

    public int getPartidosIngresados() {
        return partidosIngresados;
    }

    public void setPartidosIngresados(int partidosIngresados) {
        this.partidosIngresados = partidosIngresados;
    }
    
    public void addPartidoIngresado() {
        this.partidosIngresados = this.partidosIngresados++;
    }

    @Override
    public boolean esTitular() {
        return false;
    }

    @Override
    public String getRol() {
        return "Suplente";
    }
    
}
