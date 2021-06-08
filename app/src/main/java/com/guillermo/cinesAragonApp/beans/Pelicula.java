package com.guillermo.cinesAragonApp.beans;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Pelicula {
    private String id;
    private String titulo;
    private String sinopsis;
    private String fecha_estreno;
    private String duracion;
    private String precio;
    private String cartel;
    private String votos;
    private String genero;
    private String trailer;
    private ArrayList<Horarios> horarios;

    public String horariosToString() {
        StringBuilder builder = new StringBuilder();
        for (Horarios hora :
                horarios) {
            builder.append(hora.getHora());
            builder.append(" ");
        }

        return "Sesiones: " + builder.toString();
    }
}





