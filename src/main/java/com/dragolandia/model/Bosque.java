package com.dragolandia.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Representa un bosque del mundo de Dragolandia.
 * Un bosque puede contener varios monstruos y tener un monstruo jefe.
 */
@Entity
@Table(name = "Bosques")
public class Bosque {

    /** Identificador único del bosque, generado automáticamente */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Nombre del bosque */
    private String nombre;

    /** Nivel de peligro del bosque */
    private int nivelPeligro;

    /** Monstruo jefe del bosque */
    @OneToOne
    private Monstruo monstruoJefe;

    /** Lista de monstruos que habitan el bosque */
    @OneToMany(targetEntity = Monstruo.class)
    private List<Monstruo> monstruos;

    /**
     * Constructor por defecto requerido por JPA.
     * Inicializa la lista de monstruos.
     */
    public Bosque() {
        this.monstruos = new ArrayList<>();
    }

    /**
     * Constructor con parámetros.
     *
     * @param nombre       nombre del bosque
     * @param nivelPeligro nivel de peligro
     * @param monstruoJefe monstruo jefe del bosque
     */
    public Bosque(String nombre, int nivelPeligro, Monstruo monstruoJefe) {
        this();
        this.nombre = nombre;
        setNivelPeligro(nivelPeligro);
        this.monstruoJefe = monstruoJefe;
    }

    /** @return el id del bosque */
    public int getId() {
        return id;
    }

    /** @return el nombre del bosque */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del bosque.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return el nivel de peligro del bosque */
    public int getNivelPeligro() {
        return nivelPeligro;
    }

    /**
     * Establece el nivel de peligro del bosque.
     * Si el valor es negativo, se establece en 0.
     *
     * @param nivelPeligro nuevo nivel de peligro
     */
    public void setNivelPeligro(int nivelPeligro) {
        this.nivelPeligro = Math.max(0, nivelPeligro);
    }

    /** @return el monstruo jefe del bosque */
    public Monstruo getMonstruoJefe() {
        return monstruoJefe;
    }

    /**
     * Establece el monstruo jefe del bosque.
     *
     * @param monstruoJefe nuevo monstruo jefe
     */
    public void setMonstruoJefe(Monstruo monstruoJefe) {
        this.monstruoJefe = monstruoJefe;
    }

    /** @return la lista de monstruos del bosque */
    public List<Monstruo> getMonstruos() {
        return monstruos;
    }

    /**
     * Devuelve el nombre del monstruo jefe del bosque.
     * Si no hay jefe, devuelve un mensaje indicando que no hay.
     *
     * @return nombre del jefe o mensaje de ausencia
     */
    public String mostrarJefe() {
        String resultado;

        if (monstruoJefe != null) {
            resultado = "Jefe del bosque: " + monstruoJefe.getNombre();
        } else {
            resultado = "El bosque no tiene jefe asignado";
        }

        return resultado;
    }

    /**
     * Añade un monstruo al bosque.
     *
     * @param monstruo monstruo a añadir
     * @return true si el monstruo se añadió correctamente
     */
    public boolean addMonstruo(Monstruo monstruo) {
        boolean agregado = false;

        if (monstruo != null) {
            agregado = monstruos.add(monstruo);
        }

        return agregado;
    }

    /**
     * Cambia el monstruo jefe del bosque.
     *
     * @param nuevoJefe nuevo monstruo jefe
     * @return true si el jefe se cambió correctamente
     */
    public boolean cambiarJefe(Monstruo nuevoJefe) {
        boolean cambiado = false;

        if (nuevoJefe != null) {
            this.monstruoJefe = nuevoJefe;
            cambiado = true;
        }

        return cambiado;
    }

    /**
     * Devuelve una representación en cadena del bosque.
     *
     * @return descripción del bosque
     */
    @Override
    public String toString() {
        String nombreJefe = (monstruoJefe != null) ? monstruoJefe.getNombre() : "Sin jefe";

        return String.format(
                "ID: %d | Nombre: %s | Nivel de Peligro: %d | Monstruo Jefe: %s",
                id, nombre, nivelPeligro, nombreJefe);
    }
}
