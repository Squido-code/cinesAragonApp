package com.guillermo.cinesAragonApp.beans;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ficha {

    private String nombre;
    private String sinopsis;
    private String cartel;
    private String duracion;
    private String fecha_Estreno;
    private String genero;

}
