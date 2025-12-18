package com.dragolandia.controller;

import com.dragolandia.model.Mago;
import com.dragolandia.model.Monstruo;

public class ControladorBatalla {
    public static void batalla(Mago mago, Monstruo monstruo) {
        System.out.println("----- Comienza la batalla -----\n");

        while (mago.getVida() > 0 && monstruo.getVida() > 0) {
            mago.lanzarHechizo(monstruo);

            if (monstruo.getVida() > 0) {
                monstruo.atacar(mago);
            }
        }

        System.out.println("\n----- Fin de la batalla -----\n");
        if (mago.getVida() > 0) {
            System.out.println(mago.getNombre() + " ha ganado la batalla!");
        } else {
            System.out.println(monstruo.getNombre() + " ha ganado la batalla!");
        }
    }
}