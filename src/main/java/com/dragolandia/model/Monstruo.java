package com.dragolandia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Monstruos")
public class Monstruo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int vida;

    @Enumerated(EnumType.STRING)
    private TipoMonstruo tipo;

    private int fuerza;

    public Monstruo() {}

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

    public void atacar(Mago mago) {
        int vidaAnterior = mago.getVida();

        mago.setVida(Math.max(0, mago.getVida() - this.fuerza));
        System.out.println(nombre + " ataca a " + mago.getNombre() + ". Vida: " + vidaAnterior + " -> " + mago.getVida());
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %s | Vida: %d | Tipo: %s | Fuerza: %d", id, nombre, vida, tipo, fuerza);
    }
}