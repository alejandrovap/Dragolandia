package com.dragolandia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Dragones")
public class Dragon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int intesidadFuego;
    private int resistencia;

    public Dragon() {}

    public Dragon(String nombre, int intesidadFuego, int resistencia) {
        this.nombre = nombre;
        this.intesidadFuego = intesidadFuego;
        this.resistencia = resistencia;
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

    public int getIntesidadFuego() {
        return intesidadFuego;
    }

    public void setIntesidadFuego(int intesidadFuego) {
        this.intesidadFuego = intesidadFuego;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public void exhalar(Monstruo monstruo) {
        int vidaAnterior = monstruo.getVida();

        monstruo.setVida(Math.max(0, monstruo.getVida() - intesidadFuego));
        System.out.println(nombre + " exhala una llamarada a " + monstruo.getNombre() + ". Vida: " + vidaAnterior + " -> " + monstruo.getVida());
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %s | Intesidad de Fuego: %d | Resistencia: %d", id, nombre, intesidadFuego, resistencia);
    }
}