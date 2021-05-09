package com.guillermo.videojuegos.beans;

import java.util.List;

import lombok.Data;

@Data
public class VideojuegoApiResult {
    private String count;
    private String next;
    private String previous;
    private List<Videojuego> results;


}
