package com.dragolandia.model;

import jakarta.persistence.Entity;

@Entity
public class BolaDeFuego extends Hechizo {
    public BolaDeFuego() {}
    
    public BolaDeFuego(int efecto) {
        super("Bola de Fuego", efecto);
    }
}
