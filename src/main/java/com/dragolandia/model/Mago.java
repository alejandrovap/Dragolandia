package com.dragolandia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Mago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int vida;
    private int nivelMagia;

    public Mago() {
    }

    public Mago(String nombre, int vida, int nivelMagia) {
        this.nombre = nombre;
        setVida(vida);
        setNivelMagia(nivelMagia);
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

    public int getNivelMagia() {
        return nivelMagia;
    }

    public void setNivelMagia(int nivelMagia) {
        if (nivelMagia < 0) {
            this.nivelMagia = 0;
        } else {
            this.nivelMagia = nivelMagia;
        }
    }

    public void lanzarHechizo(Monstruo monstruo) {
        int daño = this.nivelMagia;

        monstruo.setVida(monstruo.getVida() - daño);
        System.out.println(
                nombre + " ha lanzado un hechizo a " + monstruo.getNombre() + " haciendo " + daño + " de daño ");
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %s | Vida: %d | Nivel de Magia: %d", id, nombre, vida, nivelMagia);
    }
}