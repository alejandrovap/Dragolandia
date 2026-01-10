package com.dragolandia;

import com.dragolandia.controller.*;
import com.dragolandia.util.JpaUtil;
import com.dragolandia.view.Vista;

/**
 * Clase principal que inicia la aplicación Dragolandia.
 */
public class Main {

    public static void main(String[] args) {

        // Inicializar controladores
        MagoController magoCtrl = new MagoController();
        MonstruoController monstruoCtrl = new MonstruoController();
        DragonController dragonCtrl = new DragonController();
        HechizoController hechizoCtrl = new HechizoController();
        BosqueController bosqueCtrl = new BosqueController();
        BatallaController batallaCtrl = new BatallaController();

        // Inicializar la vista
        Vista vista = new Vista(magoCtrl, monstruoCtrl, dragonCtrl, hechizoCtrl, bosqueCtrl, batallaCtrl);

        // Mostrar menú principal
        vista.mostrarMenu();

        // Cerrar EntityManagerFactory al salir
        JpaUtil.getInstance().cerrar();
    }
}
