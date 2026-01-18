package com.dragolandia.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * Representa un mago del mundo de Dragolandia.
 */
@Entity
public class Mago {

    /** Identificador único del mago, generado automáticamente por la base de datos */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Nombre del mago */
    private String nombre;

    /** Vida actual del mago, nunca menor que 0 */
    private int vida;

    /** Nivel de poder mágico del mago */
    private int nivelMagia;

    /** Hechizos conocidos por el mago */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Hechizo> conjuros;

    /**
     * Constructor por defecto requerido por JPA.
     * Inicializa la lista de conjuros para evitar nulos.
     */
    public Mago() {
        this.conjuros = new ArrayList<>();
    }

    /**
     * Constructor que crea un mago con los valores especificados.
     *
     * @param nombre nombre del mago
     * @param vida puntos de vida iniciales
     * @param nivelMagia nivel de magia inicial
     */
    public Mago(String nombre, int vida, int nivelMagia) {
        this();
        this.nombre = nombre;
        setVida(vida);
        setNivelMagia(nivelMagia);
    }

    /** @return el id del mago */
    public int getId() {
        return id;
    }

    /** @return el nombre del mago */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del mago.
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return la vida actual del mago */
    public int getVida() {
        return vida;
    }

    /**
     * Establece la vida del mago.
     * Si el valor es negativo, se establece en 0.
     * @param vida nueva cantidad de vida
     */
    public void setVida(int vida) {
        this.vida = Math.max(0, vida);
    }

    /** @return el nivel de magia del mago */
    public int getNivelMagia() {
        return nivelMagia;
    }

    /**
     * Establece el nivel de magia del mago.
     * Si el valor es negativo, se establece en 0.
     * @param nivelMagia nuevo nivel de magia
     */
    public void setNivelMagia(int nivelMagia) {
        this.nivelMagia = Math.max(0, nivelMagia);
    }

    /** @return la lista de hechizos conocidos por el mago */
    public List<Hechizo> getConjuros() {
        return conjuros;
    }

    /**
     * Establece la lista de hechizos conocidos por el mago.
     * @param conjuros lista de hechizos
     */
    public void setConjuros(List<Hechizo> conjuros) {
        this.conjuros = conjuros;
    }

    /**
     * Lanza un hechizo básico contra un monstruo.
     * El daño causado es igual al nivel de magia del mago.
     *
     * @param monstruo monstruo objetivo
     * @return daño causado al monstruo
     */
    public int lanzarHechizo(Monstruo monstruo) {
        int vidaAnterior = monstruo.getVida();
        monstruo.setVida(vidaAnterior - nivelMagia);

        int damage = vidaAnterior - monstruo.getVida();
        return damage;
    }

    /**
     * Lanza un hechizo específico contra un monstruo.
     * Si el mago conoce el hechizo, se aplica al monstruo.
     * Si no lo conoce, el mago pierde 1 punto de vida y no causa daño.
     *
     * @param monstruo monstruo objetivo
     * @param hechizo hechizo a lanzar
     * @return daño causado al monstruo (0 si no se lanzó)
     */
    public int lanzarHechizo(Monstruo monstruo, Hechizo hechizo) {

        int damage = 0; 

        if (conjuros.contains(hechizo)) {
            int vidaAnterior = monstruo.getVida();
            hechizo.aplicar(monstruo);
            damage = vidaAnterior - monstruo.getVida();
        } else {
            // penalización de vida por no conocer el hechizo
            vida = Math.max(0, vida - 1);
        }

        return damage;
    }

    /**
     * Devuelve una representación en cadena del mago, incluyendo
     * id, nombre, vida, nivel de magia y hechizos conocidos.
     * @return descripción del mago
     */
    @Override
    public String toString() {
        return String.format(
                "ID: %d | Nombre: %s | Vida: %d | NivelMagia: %d | Conjuros: %s",
                id, nombre, vida, nivelMagia, conjuros
        );
    }
}
