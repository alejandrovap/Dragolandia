package com.dragolandia.controller;

import java.util.Scanner;

import com.dragolandia.model.Bosque;

public class ControladorBosque {
    public static Bosque crearBosque(){
        Scanner sc = new Scanner(System.in);

        String nombreBosque = sc.nextLine();
        int nivelPeligro = sc.nextInt();
        sc.nextLine();

        return new Bosque(nombreBosque, nivelPeligro, null);
    }
}