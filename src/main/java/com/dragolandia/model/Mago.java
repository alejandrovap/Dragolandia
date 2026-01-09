package com.dragolandia.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Magos")
public class Mago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int vida;
    private int nivelMagia;

    private List<Hechizo> conjuros;

    public Mago() {}

    public Mago(String nombre, int vida, int nivelMagia) {
        this.nombre = nombre;
        setVida(vida);
        setNivelMagia(nivelMagia);
        this.conjuros = new ArrayList<>();
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

    public List<Hechizo> getConjuros() {
        return conjuros;
    }

    public void setConjuros(List<Hechizo> conjuros) {
        this.conjuros = conjuros;
    }

    public void lanzarHechizo(Monstruo monstruo) {
        int vidaAnterior = monstruo.getVida();
        monstruo.setVida(Math.max(0, monstruo.getVida() - nivelMagia));
        System.out.println(nombre + " lanza un hechizo a " + monstruo.getNombre() + ". Vida: " + vidaAnterior + " -> " + monstruo.getVida());
    }

    public void lanzarHechizo(Monstruo monstruo, Hechizo hechizo) {
        int vidaAnterior = monstruo.getVida();

        if (conjuros.contains(hechizo)) {
            if (hechizo instanceof BolaNieve) {
                monstruo.setVida(0);
                System.out.println(nombre + " lanza " + hechizo.getNombre() + " a " + monstruo.getNombre() + ". Vida: " + vidaAnterior + " -> 0");
            } else {
                monstruo.setVida(Math.max(0, monstruo.getVida() - hechizo.getEfecto()));
                System.out.println(nombre + " lanza " + hechizo.getNombre() + " a " + monstruo.getNombre() + ". Vida: " + vidaAnterior + " -> " + monstruo.getVida());
            }
        } else {
            vida = Math.max(0, vida - 1);
            System.out.println(nombre + " no conoce " + hechizo.getNombre() + ". Vida: " + vidaAnterior + " -> " + monstruo.getVida());
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %s | Vida: %d | Nivel de Magia: %d | Conjuros: %s", id, nombre, vida, nivelMagia, conjuros);
    }
}