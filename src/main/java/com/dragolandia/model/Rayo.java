package com.dragolandia.model;

import jakarta.persistence.Entity;

/**
 * Representa un hechizo de tipo Rayo en Dragolandia.
 */
@Entity
public class Rayo extends Hechizo {

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Rayo() {
        super();
    }

    /**
     * Constructor que crea un hechizo Rayo con un efecto específico.
     *
     * @param efecto valor del efecto del hechizo
     */
    public Rayo(int efecto) {
        super("Rayo", efecto);
    }
}
