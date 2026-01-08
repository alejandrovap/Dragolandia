package com.dragolandia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Monstruo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int vida;
    private int fuerza;

    @Enumerated(EnumType.STRING)
    private TipoMonstruo tipo;

    @ManyToOne
    private Bosque bosque;

    public Monstruo() {
    }

    public Monstruo(String nombre, int vida, TipoMonstruo tipo, int fuerza) {
        this.nombre = nombre;
        setVida(vida);
        this.fuerza = fuerza;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if (vida < 0) {
            this.vida = 0;
        } else {
            this.vida = vida;
        }
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public TipoMonstruo getTipo() {
        return tipo;
    }

    public void setTipo(TipoMonstruo tipo) {
        this.tipo = tipo;
    }

    public Bosque getBosque() {
        return bosque;
    }

    public void setBosque(Bosque bosque) {
        this.bosque = bosque;
    }

    public void atacar(Mago mago) {
        int daño = this.fuerza;

        mago.setVida(mago.getVida() - daño);
        System.out.println(nombre + " ha atacado a " + mago.getNombre() + " haciendo " + daño + " de daño ");
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %s | Vida: %d | Fuerza: %d | Tipo: %s", id, nombre, vida, fuerza, tipo);
    }
}