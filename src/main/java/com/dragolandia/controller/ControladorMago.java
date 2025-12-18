package com.dragolandia.controller;

import java.util.Scanner;

import com.dragolandia.model.Mago;

public class ControladorMago {
    public static Mago crearMago(){
        Scanner sc = new Scanner(System.in);

        String nombreMago = sc.nextLine();
        int vidaMago = sc.nextInt();
        sc.nextLine();
        int nivelMagia = sc.nextInt();
        sc.nextLine();

        return new Mago(nombreMago, vidaMago, nivelMagia);
    }
}