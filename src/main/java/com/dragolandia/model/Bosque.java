package com.dragolandia.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Bosque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int nivelPeligro;

    @OneToOne
    private Monstruo monstruoJefe;

    @OneToMany(targetEntity = Monstruo.class)
    private List<Monstruo> monstruos = new ArrayList<>();

    public Bosque() {}

    public Bosque(String nombre, int nivelPeligro, Monstruo monstruoJefe) {
        this.nombre = nombre;
        this.nivelPeligro = nivelPeligro;
        this.monstruoJefe = monstruoJefe;
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

    public int getNivelPeligro() {
        return nivelPeligro;
    }

    public void setNivelPeligro(int nivelPeligro) {
        this.nivelPeligro = nivelPeligro;
    }

    public Monstruo getMonstruoJefe() {
        return monstruoJefe;
    }

    public void setMonstruoJefe(Monstruo monstruoJefe) {
        this.monstruoJefe = monstruoJefe;
    }

    public List<Monstruo> getMonstruos() {
        return this.monstruos;
    }

    public void mostrarJefe() {
        System.out.println("Jefe del bosque: " + monstruoJefe.getNombre());
    }

    public void cambiarJefe(Monstruo nuevoJefe) {
        this.monstruoJefe = nuevoJefe;
        System.out.println("El nuevo jefe del bosque es: " + nuevoJefe.getNombre());
    }

    public void addMonstruo(Monstruo monstruo) {
        monstruos.add(monstruo);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %s | Nivel de Peligro: %d | Monstruo Jefe: %s", id, nombre, nivelPeligro, monstruoJefe.getNombre());
    }
}