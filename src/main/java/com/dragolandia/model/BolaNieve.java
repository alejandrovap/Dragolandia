package com.dragolandia.model;

import jakarta.persistence.Entity;

@Entity
public class BolaNieve extends Hechizo {
    public BolaNieve() {
        super("Bola de Nieve", 0);
    }
}
