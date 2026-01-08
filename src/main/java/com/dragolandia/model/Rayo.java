package com.dragolandia.model;

import jakarta.persistence.Entity;

@Entity
public class Rayo extends Hechizo {
    public Rayo() {}
    
    public Rayo(int efecto) {
        super("Rayo", efecto);
    }
}
