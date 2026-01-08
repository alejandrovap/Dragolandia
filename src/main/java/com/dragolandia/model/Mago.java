package com.dragolandia.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Mago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int vida;
    private int nivelMagia;

    @ManyToMany
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
        int daño = this.nivelMagia;

        monstruo.setVida(monstruo.getVida() - daño);
        System.out.println(
                nombre + " ha lanzado un hechizo a " + monstruo.getNombre() + " haciendo " + daño + " de daño ");
    }

    public void lanzarHechizo(Monstruo monstruo, Hechizo hechizo) {
       if (conjuros.contains(hechizo)) {
            int vidaAnterior = monstruo.getVida();
            
            if (hechizo instanceof BolaNieve) {
                monstruo.setVida(0);
                System.out.println(nombre + " lanza " + hechizo.getNombre() + " a " + 
                                 monstruo.getNombre() + ". ¡Congelado! Vida: " + vidaAnterior + " -> 0");
            } else {
                int danio = hechizo.getEfecto();
                monstruo.setVida(Math.max(0, monstruo.getVida() - danio));
                System.out.println(nombre + " lanza " + hechizo.getNombre() + " a " + 
                                 monstruo.getNombre() + ". Daño: " + danio + 
                                 ". Vida: " + vidaAnterior + " -> " + monstruo.getVida());
            }
        } else {
            vida = Math.max(0, vida - 1);
            System.out.println("¡" + nombre + " no conoce " + hechizo.getNombre() + 
                             "! Pierde 1 punto de vida. Vida: " + vida);
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %s | Vida: %d | Nivel de Magia: %d", id, nombre, vida, nivelMagia);
    }
}