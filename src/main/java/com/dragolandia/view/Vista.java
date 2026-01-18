package com.dragolandia.view;

import com.dragolandia.controller.*;
import com.dragolandia.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Vista de consola para interactuar con la aplicación Dragolandia
 */
public class Vista {

    private final Scanner scanner = new Scanner(System.in);

    // Controladores
    private final MagoController magoCtrl;
    private final MonstruoController monstruoCtrl;
    private final DragonController dragonCtrl;
    private final HechizoController hechizoCtrl;
    private final BosqueController bosqueCtrl;
    private final BatallaController batallaCtrl;

    public Vista(MagoController magoCtrl, MonstruoController monstruoCtrl,
            DragonController dragonCtrl, HechizoController hechizoCtrl,
            BosqueController bosqueCtrl, BatallaController batallaCtrl) {
        this.magoCtrl = magoCtrl;
        this.monstruoCtrl = monstruoCtrl;
        this.dragonCtrl = dragonCtrl;
        this.hechizoCtrl = hechizoCtrl;
        this.bosqueCtrl = bosqueCtrl;
        this.batallaCtrl = batallaCtrl;
    }

    /**
     * Menú principal
     */
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- DRAGOLANDIA: MENÚ PRINCIPAL ---");
            System.out.println("1. Crear Elementos");
            System.out.println("2. Listar Elementos");
            System.out.println("3. Buscar Elemento por ID");
            System.out.println("4. Actualizar Elementos");
            System.out.println("5. Eliminar Elementos");
            System.out.println("6. Iniciar Batalla");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> mostrarSubmenuCrear();
                case 2 -> mostrarSubmenuListar();
                case 3 -> mostrarSubmenuBuscar();
                case 4 -> mostrarSubmenuActualizar();
                case 5 -> mostrarSubmenuEliminar();
                case 6 -> batalla();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    /**
     * Muestra el submenú para crear entidades
     */
    private void mostrarSubmenuCrear() {
        System.out.println("\n--- MENÚ CREACIÓN ---");
        System.out.println("¿Qué deseas crear?");
        mostrarOpcionesEntidades();

        int opcion = leerEntero();
        switch (opcion) {
            case 1 -> crearMago();
            case 2 -> crearMonstruo();
            case 3 -> crearDragon();
            case 4 -> crearHechizo();
            case 5 -> crearBosque();
            case 0 -> System.out.println("Volviendo...");
            default -> System.out.println("Opción no válida");
        }
    }

    /**
     * Muestra el submenú para listar entidades
     */
    private void mostrarSubmenuListar() {
        System.out.println("\n--- MENÚ LISTADO ---");
        System.out.println("¿Qué deseas listar?");
        mostrarOpcionesEntidades();

        int opcion = leerEntero();
        switch (opcion) {
            case 1 -> listarMagos();
            case 2 -> listarMonstruos();
            case 3 -> listarDragones();
            case 4 -> listarHechizos();
            case 5 -> listarBosques();
            case 0 -> System.out.println("Volviendo...");
            default -> System.out.println("Opción no válida.");
        }
    }

    /**
     * Muestra el submenú para buscar por ID
     */
    private void mostrarSubmenuBuscar() {
        System.out.println("\n--- MENÚ BÚSQUEDA ---");
        System.out.println("¿Qué deseas buscar?");
        mostrarOpcionesEntidades();

        int opcion = leerEntero();
        if (opcion == 0)
            return;

        System.out.print("Introduce el ID a buscar: ");
        int id = leerEntero();

        switch (opcion) {
            case 1 -> System.out.println(magoCtrl.buscarMago(id));
            case 2 -> System.out.println(monstruoCtrl.buscarMonstruo(id));
            case 3 -> System.out.println(dragonCtrl.buscarDragon(id));
            case 4 -> System.out.println(hechizoCtrl.buscarHechizo(id));
            case 5 -> System.out.println(bosqueCtrl.buscarBosque(id));
            default -> System.out.println("Opción no válida");
        }
    }

    /**
     * Muestra el submenú para actualizar entidades
     */
    private void mostrarSubmenuActualizar() {
        System.out.println("\n--- MENÚ ACTUALIZACIÓN ---");
        System.out.println("¿Qué deseas actualizar?");
        mostrarOpcionesEntidades();

        int opcion = leerEntero();
        if (opcion == 0)
            return;

        switch (opcion) {
            case 1 -> actualizarMago();
            case 2 -> actualizarMonstruo();
            case 3 -> actualizarDragon();
            case 4 -> actualizarHechizo();
            case 5 -> actualizarBosque();
            default -> System.out.println("Opción no válida");
        }
    }

    /**
     * Muestra el submenú para borrar entidades
     */
    private void mostrarSubmenuEliminar() {
        System.out.println("\n--- MENÚ ELIMINACIÓN ---");
        System.out.println("¿Qué deseas eliminar?");
        mostrarOpcionesEntidades();

        int opcion = leerEntero();
        if (opcion == 0)
            return;

        System.out.print("Introduce el ID del elemento a eliminar: ");
        int id = leerEntero();
        boolean resultado = false;

        switch (opcion) {
            case 1 -> resultado = magoCtrl.eliminarMago(id);
            case 2 -> resultado = monstruoCtrl.eliminarMonstruo(id);
            case 3 -> resultado = dragonCtrl.eliminarDragon(id);
            case 4 -> resultado = hechizoCtrl.eliminarHechizo(id);
            case 5 -> resultado = bosqueCtrl.eliminarBosque(id);
            default -> System.out.println("Opción no válida");
        }

        if (resultado)
            System.out.println("Elemento eliminado correctamente");
        else
            System.out.println("No se pudo eliminar");
    }

    private void mostrarOpcionesEntidades() {
        System.out.println("1. Mago");
        System.out.println("2. Monstruo");
        System.out.println("3. Dragón");
        System.out.println("4. Hechizo");
        System.out.println("5. Bosque");
        System.out.println("0. Volver");
        System.out.print("Selecciona una entidad: ");
    }

    /**
     * Métodos CRUD para Mago
     */
    private void crearMago() {
        System.out.println("\n--- Nuevo Mago ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Vida: ");
        int vida = leerEntero();

        System.out.print("Nivel de magia: ");
        int nivel = leerEntero();

        Mago m = magoCtrl.crearMago(nombre, vida, nivel);
        System.out.println((m != null) ? "Mago creado: " + m : "Error al crear mago");
    }

    private void listarMagos() {
        List<Mago> lista = magoCtrl.listarMagos();

        if (lista.isEmpty()) {
            System.out.println("No hay magos registrados");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private void actualizarMago() {
        System.out.print("ID del Mago a actualizar: ");
        int id = leerEntero();

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Nueva vida: ");
        int vida = leerEntero();

        System.out.print("Nuevo nivel magia: ");
        int nivel = leerEntero();

        boolean actualizado = magoCtrl.actualizarMago(id, nombre, vida, nivel);
        System.out.println(actualizado ? "Mago actualizado" : "No se encontró el mago o hubo error");
    }

    /**
     * Métodos CRUD para Monstruo
     */
    private void crearMonstruo() {
        System.out.println("\n--- Nuevo Monstruo ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Vida: ");
        int vida = leerEntero();

        System.out.print("Fuerza: ");
        int fuerza = leerEntero();

        // Se asigna OGRO por defecto
        TipoMonstruo tipo = TipoMonstruo.OGRO;

        Monstruo m = monstruoCtrl.crearMonstruo(nombre, vida, tipo, fuerza);
        System.out.println("Monstruo creado: " + m);
    }

    private void listarMonstruos() {
        List<Monstruo> lista = monstruoCtrl.listarMonstruos();
        if (lista.isEmpty()) {
            System.out.println("No hay monstruos");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private void actualizarMonstruo() {
        System.out.print("ID del Monstruo a actualizar: ");
        int id = leerEntero();

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Nueva vida: ");
        int vida = leerEntero();

        System.out.print("Nueva fuerza: ");
        int fuerza = leerEntero();

        boolean actualizado = monstruoCtrl.actualizarMonstruo(id, nombre, vida, TipoMonstruo.OGRO, fuerza);
        System.out.println(actualizado ? "Actualizado" : "Error");
    }

    /**
     * Métodos CRUD para Dragon
     */
    private void crearDragon() {
        System.out.println("\n--- Nuevo Dragón ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Intensidad Fuego: ");
        int fuego = leerEntero();

        System.out.print("Resistencia: ");
        int resistencia = leerEntero();

        Dragon d = dragonCtrl.crearDragon(nombre, fuego, resistencia);
        System.out.println("Dragón creado: " + d);
    }

    private void listarDragones() {
        List<Dragon> lista = dragonCtrl.listarDragones();
        if (lista.isEmpty()) {
            System.out.println("No hay dragones");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private void actualizarDragon() {
        System.out.print("ID del Dragón a actualizar: ");
        int id = leerEntero();

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Nueva intensidad fuego: ");
        int fuego = leerEntero();

        System.out.print("Nueva resistencia: ");
        int res = leerEntero();

        boolean actualizado = dragonCtrl.actualizarDragon(id, nombre, fuego, res);
        System.out.println(actualizado ? "Actualizado" : "Error");
    }

    /**
     * Métodos CRUD para Hechizo
     */
    private void crearHechizo() {
        System.out.println("\n--- Nuevo Hechizo ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Efecto: ");
        int efecto = leerEntero();

        Hechizo h = hechizoCtrl.crearHechizo(nombre, efecto);
        System.out.println("Hechizo creado: " + h);
    }

    private void listarHechizos() {
        List<Hechizo> lista = hechizoCtrl.listarHechizos();
        if (lista.isEmpty()) {
            System.out.println("No hay hechizos");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private void actualizarHechizo() {
        System.out.print("ID del Hechizo a actualizar: ");
        int id = leerEntero();

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Nuevo efecto: ");
        int efecto = leerEntero();

        boolean actualizado = hechizoCtrl.actualizarHechizo(id, nombre, efecto);
        System.out.println(actualizado ? "Actualizado" : "Error");
    }

    /**
     * Métodos CRUD para Bosque
     */
    private void crearBosque() {
        System.out.println("\n--- Nuevo Bosque ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Nivel de peligro: ");
        int nivel = leerEntero();

        Bosque b = bosqueCtrl.crearBosque(nombre, nivel, null);
        System.out.println("Bosque creado: " + b);
    }

    private void listarBosques() {
        List<Bosque> lista = bosqueCtrl.listarBosques();
        if (lista.isEmpty()) {
            System.out.println("No hay bosques");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private void actualizarBosque() {
        System.out.print("ID del Bosque a actualizar: ");
        int id = leerEntero();

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Nuevo nivel de peligro: ");
        int nivel = leerEntero();

        boolean actualizado = bosqueCtrl.actualizarBosque(id, nombre, nivel, null);
        System.out.println(actualizado ? "Bosque actualizado (Jefe reseteado a null)." : "Error.");
    }

    // Método para la batalla
    private void batalla() {

    Hechizo hFuego = hechizoCtrl.crearHechizo("Fuego", 20);
    Hechizo hHielo = hechizoCtrl.crearHechizo("Hielo", 15);
    List<Hechizo> listaHechizos = hechizoCtrl.listarHechizos();


    Mago m1 = magoCtrl.crearMago("Gandalf", 100, 10);
    m1.getConjuros().add(hFuego); 
    
    Mago m2 = magoCtrl.crearMago("Magicus", 90, 12);
    m2.getConjuros().add(hHielo);

    List<Mago> magos = new ArrayList<>();
    magos.add(m1);
    magos.add(m2);

    Bosque bosque = bosqueCtrl.crearBosque("Bosque Antiguo", 5, null);
    Monstruo mon1 = monstruoCtrl.crearMonstruo("Orco", 40, TipoMonstruo.OGRO, 10);
    Monstruo mon2 = monstruoCtrl.crearMonstruo("Orco de piedra", 30, TipoMonstruo.OGRO, 5);
    Monstruo mon3 = monstruoCtrl.crearMonstruo("Troll", 60, TipoMonstruo.OGRO, 15);

    List<Monstruo> monstruos = new ArrayList<>();
    monstruos.add(mon1);
    monstruos.add(mon2);
    monstruos.add(mon3);
    
    bosqueCtrl.agregarMonstruo(bosque, mon1);
    bosqueCtrl.agregarMonstruo(bosque, mon2);
    bosqueCtrl.agregarMonstruo(bosque, mon3);
    bosqueCtrl.cambiarJefe(bosque, mon3);

    Dragon dragon = dragonCtrl.crearDragon("Dragón venenoso", 50, 100);
    Random rand = new Random();

    while (!magos.isEmpty() && !monstruos.isEmpty()) {
        System.out.println("\n--- NUEVA RONDA ---");

        for (Mago mago : magos) {
            if (monstruos.isEmpty()){
                break; 
            }

            Hechizo hechizo = listaHechizos.get(rand.nextInt(listaHechizos.size()));
            Monstruo objetivo = monstruos.get(rand.nextInt(monstruos.size()));

            if (mago.getConjuros().contains(hechizo)) {
                System.out.println(mago.getNombre() + " ataca a " + objetivo.getNombre() + " con " + hechizo.getNombre());
                batallaCtrl.recibirDano(objetivo, hechizo.getEfecto());
            } else {
                System.out.println(mago.getNombre() + " falla el conjuro " + hechizo.getNombre());
                batallaCtrl.recibirDano(mago, 1);
            }
        }

        for (Monstruo mon : monstruos) {
            if (mon.getVida() > 0 && !magos.isEmpty()) {
                Mago objetivo = magos.get(rand.nextInt(magos.size()));
                System.out.println(mon.getNombre() + " golpea a " + objetivo.getNombre());
                batallaCtrl.recibirDano(objetivo, mon.getFuerza());
            }
        }

        batallaCtrl.asignarJefe(bosque, monstruos);
        Monstruo jefe = bosque.getMonstruoJefe();
        
        if (jefe != null && jefe.getVida() > 0) {
            System.out.println("Dragón ataca al Jefe " + jefe.getNombre());
            batallaCtrl.recibirDano(jefe, dragon.getIntensidadFuego());
        }

        magos.removeIf(m -> m.getVida() <= 0);
        monstruos.removeIf(m -> m.getVida() <= 0);
    }

    System.out.println("\n--- FIN: " + (magos.isEmpty() ? "GANAN MONSTRUOS" : "GANAN MAGOS") + " ---");
}

    /**
     * Método auxiliar para leer enteros y
     * evitar el bug del salto de línea de Scanner
     */
    private int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Por favor, introduce un número válido");
            return -1;
        }
    }
}