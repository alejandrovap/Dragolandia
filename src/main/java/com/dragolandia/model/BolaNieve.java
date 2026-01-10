package com.dragolandia.model;

import jakarta.persistence.Entity;

/**
 * Representa un hechizo de tipo Bola de Nieve en Dragolandia.
 */
@Entity
public class BolaNieve extends Hechizo {

    /**
     * Constructor por defecto.
     * Crea un hechizo con nombre "Bola de Nieve" y efecto 0.
     */
    public BolaNieve() {
        super("Bola de Nieve", 0);
    }

    // se aplica en el método aplicar(Monstruo) de la instancia de Hechizo en Mago.
}
