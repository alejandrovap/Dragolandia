package com.dragolandia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa un dragón del mundo de Dragolandia.
 * Un dragón puede atacar mediante fuego y posee resistencia.
 */
@Entity
@Table(name = "dragon")
public class Dragon {

    /** Identificador único del dragón, generado automáticamente */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Nombre del dragón */
    private String nombre;

    /** Intensidad del fuego que exhala el dragón */
    private int intensidadFuego;

    /** Resistencia del dragón */
    private int resistencia;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Dragon() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param nombre nombre del dragón
     * @param intensidadFuego intensidad del fuego
     * @param resistencia resistencia del dragón
     */
    public Dragon(String nombre, int intensidadFuego, int resistencia) {
        this.nombre = nombre;
        setIntensidadFuego(intensidadFuego);
        setResistencia(resistencia);
    }

    /** @return el id del dragón */
    public int getId() {
        return id;
    }

    /** @return el nombre del dragón */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del dragón.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return la intensidad del fuego del dragón */
    public int getIntensidadFuego() {
        return intensidadFuego;
    }

    /**
     * Establece la intensidad del fuego.
     * Si el valor es negativo, se establece en 0.
     *
     * @param intensidadFuego nueva intensidad del fuego
     */
    public void setIntensidadFuego(int intensidadFuego) {
        this.intensidadFuego = Math.max(0, intensidadFuego);
    }

    /** @return la resistencia del dragón */
    public int getResistencia() {
        return resistencia;
    }

    /**
     * Establece la resistencia del dragón.
     * Si el valor es negativo, se establece en 0.
     *
     * @param resistencia nueva resistencia
     */
    public void setResistencia(int resistencia) {
        this.resistencia = Math.max(0, resistencia);
    }

    /**
     * Exhala fuego contra un monstruo, reduciendo su vida
     * según la intensidad del fuego.
     *
     * @param monstruo monstruo objetivo del ataque
     * @return daño causado al monstruo
     */
    public int exhalar(Monstruo monstruo) {

        int vidaAnterior = monstruo.getVida();
        monstruo.setVida(vidaAnterior - intensidadFuego);

        int damage = vidaAnterior - monstruo.getVida();
        return damage;
    }

    /**
     * Devuelve una representación en cadena del dragón.
     *
     * @return descripción del dragón
     */
    @Override
    public String toString() {
        return String.format(
                "ID: %d | Nombre: %s | Intensidad de Fuego: %d | Resistencia: %d",
                id, nombre, intensidadFuego, resistencia
        );
    }
}
