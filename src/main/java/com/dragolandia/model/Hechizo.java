package com.dragolandia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

/**
 * Representa un hechizo del mundo de Dragolandia.
 * Un hechizo puede ser lanzado por un mago y aplicado sobre un monstruo.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Hechizo {

    /** Identificador único del hechizo, generado automáticamente */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Nombre del hechizo */
    private String nombre;

    /** Intensidad del efecto del hechizo */
    private int efecto;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Hechizo() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param nombre nombre del hechizo
     * @param efecto valor del efecto del hechizo
     */
    public Hechizo(String nombre, int efecto) {
        this.nombre = nombre;
        setEfecto(efecto);
    }

    /** @return el id del hechizo */
    public int getId() {
        return id;
    }

    /** @return el nombre del hechizo */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del hechizo.
     *
     * @param nombre nuevo nombre del hechizo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return el valor del efecto del hechizo */
    public int getEfecto() {
        return efecto;
    }

    /**
     * Establece el efecto del hechizo.
     * Si el valor es negativo, se establece en 0.
     *
     * @param efecto nuevo valor del efecto
     */
    public void setEfecto(int efecto) {
        this.efecto = Math.max(0, efecto);
    }

    /**
     * Aplica el efecto del hechizo sobre un monstruo.
     * Por defecto, reduce la vida del monstruo según el valor del efecto.
     *
     * @param monstruo monstruo objetivo del hechizo
     */
    public void aplicar(Monstruo monstruo) {
        int vidaAnterior = monstruo.getVida();
        monstruo.setVida(vidaAnterior - efecto);
    }

    /**
     * Devuelve una representación en cadena del hechizo.
     *
     * @return descripción del hechizo
     */
    @Override
    public String toString() {
        return String.format(
                "ID: %d | Nombre: %s | Efecto: %d",
                id, nombre, efecto
        );
    }
}
