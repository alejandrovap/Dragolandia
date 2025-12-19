package com.dragolandia.controller;

import java.util.Scanner;

import com.dragolandia.model.Monstruo;
import com.dragolandia.model.TipoMonstruo;

public class ControladorMonstruo {
    public static Monstruo crearMonstruo(){
        Scanner sc = new Scanner(System.in);

        String nombreMonstruo = sc.nextLine();
        int vidaMonstruo = sc.nextInt();
        sc.nextLine();
        TipoMonstruo tipoMonstruo = sc.nextLine();
        int fuerza = sc.nextInt();
        sc.nextLine();

        return new Monstruo(nombreMonstruo, vidaMonstruo, tipoMonstruo, fuerza);
    }
}