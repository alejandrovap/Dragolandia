package com.dragolandia.model;

import jakarta.persistence.Entity;

/**
 * Representa un hechizo de tipo Bola de Fuego en Dragolandia.
 */
@Entity
public class BolaDeFuego extends Hechizo {

    /**
     * Constructor por defecto requerido por JPA.
     */
    public BolaDeFuego() {
        super();
    }

    /**
     * Constructor que crea un hechizo Bola de Fuego con un efecto específico.
     *
     * @param efecto valor del efecto del hechizo
     */
    public BolaDeFuego(int efecto) {
        super("Bola de Fuego", efecto);
    }
}