package com.dragolandia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa un monstruo del mundo de Dragolandia.
 * Un monstruo posee vida, fuerza y un tipo determinado.
 */
@Entity
@Table(name = "monstruo")
public class Monstruo {

    /** Identificador único del monstruo, generado automáticamente */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Nombre del monstruo */
    private String nombre;

    /** Vida actual del monstruo, nunca menor que 0 */
    private int vida;

    /** Tipo del monstruo */
    @Enumerated(EnumType.STRING)
    private TipoMonstruo tipo;

    /** Fuerza del monstruo, usada para atacar */
    private int fuerza;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Monstruo() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param nombre nombre del monstruo
     * @param vida   vida inicial
     * @param tipo   tipo del monstruo
     * @param fuerza fuerza del monstruo
     */
    public Monstruo(String nombre, int vida, TipoMonstruo tipo, int fuerza) {
        this.nombre = nombre;
        setVida(vida);
        this.tipo = tipo;
        this.fuerza = fuerza;
    }

    /** @return el id del monstruo */
    public int getId() {
        return id;
    }

    /** @return el nombre del monstruo */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del monstruo.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return la vida actual del monstruo */
    public int getVida() {
        return vida;
    }

    /**
     * Establece la vida del monstruo.
     * Si el valor es negativo, se establece en 0.
     *
     * @param vida nueva cantidad de vida
     */
    public void setVida(int vida) {
        this.vida = Math.max(0, vida);
    }

    /** @return la fuerza del monstruo */
    public int getFuerza() {
        return fuerza;
    }

    /**
     * Establece la fuerza del monstruo.
     *
     * @param fuerza nueva fuerza
     */
    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    /** @return el tipo del monstruo */
    public TipoMonstruo getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo del monstruo.
     *
     * @param tipo nuevo tipo
     */
    public void setTipo(TipoMonstruo tipo) {
        this.tipo = tipo;
    }

    /**
     * Ataca a un mago, reduciendo su vida según la fuerza del monstruo.
     *
     * @param mago mago objetivo del ataque
     * @return daño causado al mago
     */
    public int atacar(Mago mago) {

        int vidaAnterior = mago.getVida();
        mago.setVida(vidaAnterior - fuerza);

        int damage = vidaAnterior - mago.getVida();
        return damage;
    }

    /**
     * Devuelve una representación en cadena del monstruo.
     *
     * @return descripción del monstruo
     */
    @Override
    public String toString() {
        return String.format(
                "ID: %d | Nombre: %s | Vida: %d | Tipo: %s | Fuerza: %d",
                id, nombre, vida, tipo, fuerza);
    }
}
