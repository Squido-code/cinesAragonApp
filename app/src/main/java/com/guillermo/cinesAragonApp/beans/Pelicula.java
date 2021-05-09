package com.guillermo.cinesAragonApp.beans;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Pelicula {
    private String id;
    private String titulo;
    private String sinopsis;
    private String fecha_Estreno;
    private String duracion;
    private String precio;
    private String cartel;
    private String votos;
    private String genero;
    private ArrayList<Horarios> horarios;
}





