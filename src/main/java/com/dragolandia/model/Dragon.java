package com.dragolandia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Dragon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    private int intesidadFuego;
    private int resistencia;

    @OneToOne
    private Bosque dragonBosque;

    public Dragon() {
    }

    public Dragon(String nombre, int intesidadFuego, int resistencia) {
        this.nombre = nombre;
        this.intesidadFuego = intesidadFuego;
        this.resistencia = resistencia;
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
        monstruo.setVida(monstruo.getVida() - intesidadFuego);
    }
}