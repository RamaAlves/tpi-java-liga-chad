package com.informatorio.servicios.archivos.impl;

import java.io.FileWriter;
import java.io.IOException;

import com.informatorio.dominio.Equipo;
import com.informatorio.dominio.Jugador;
import com.informatorio.servicios.archivos.ArchivosService;
import com.opencsv.CSVWriter;

public class ArchivosServiceImpl implements ArchivosService{
     private final String UBICACION_ARCHIVO = "\\src\\main\\java\\com\\informatorio\\recursos\\";

    CSVWriter csvWriter;

    @Override
    public void exportarEquipoCSV(Equipo equipo) {
        String ruta = System.getProperty("user.dir").concat(UBICACION_ARCHIVO).concat(String.format("reporte-jugadores-%s.csv", equipo.getNombre()));
        try{

            this.csvWriter = new CSVWriter(new FileWriter(ruta));

            String[] encabezado = {"NOMBRE","EDAD","CANTIDAD DE GOLES","ES TITULAR"};
            this.csvWriter.writeNext(encabezado);

            for (Jugador jugador : equipo.getListaDeJugadores()) {
                String[] datos = {
                    jugador.getNombre(),
                    Integer.toString(jugador.getEdad()),
                    Integer.toString(jugador.getCantidadDeGoles()),
                    jugador.esTitular()?"SI":"NO"        
                };
                this.csvWriter.writeNext(datos);
            }

            //Cerrar el csvWriter
            this.cerrarWriter();
            System.out.println("Exportacion exitosa");

        }catch (IOException e){
            System.out.println("Algo salio mal motivo :" + e.getMessage().concat(" Ubicacion archivo : " + ruta));
        }
    }

    private void cerrarWriter() {
        if (this.csvWriter != null){
            try{
                this.csvWriter.close();
            }catch (IOException e){
                System.out.println("Algo salio mal motivo :" + e.getMessage());
            }
        }
    }
}