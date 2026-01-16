package com.dragolandia.view;

import com.dragolandia.controller.*;
import com.dragolandia.model.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Vista de consola para interactuar con la aplicación Dragolandia.
 */
public class Vista {

    // Scanner para leer entrada de consola
    private final Scanner scanner = new Scanner(System.in);

    // Controladores de la aplicación, se aplican desde Main
    private final MagoController magoCtrl;
    private final MonstruoController monstruoCtrl;
    private final DragonController dragonCtrl;
    private final HechizoController hechizoCtrl;
    private final BosqueController bosqueCtrl;
    private final BatallaController batallaCtrl;

    /**
     * Constructor de la Vista.
     * Recibe todos los controladores para poder interactuar con los datos.
     */
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
     * Menú principal de la aplicación.
     * Permite al usuario elegir qué acción realizar: CRUD de entidades o iniciar batalla.
     */
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Crear elementos");
            System.out.println("2. Listar elementos");
            System.out.println("3. Buscar elementos por id");
            System.out.println("4. Actualizar elementos");
            System.out.println("5. Eliminar elementos");
            System.out.println("6. Batalla");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            
            // Leer opción del usuario
            opcion = Integer.parseInt(scanner.nextLine());

            // Ejecutar acción según opción
            switch (opcion) {
                case 1 -> crearMago();
                case 2 -> listarMagos();
                case 3 -> crearMonstruo();
                case 4 -> listarMonstruos();
                case 5 -> crearHechizo();
                case 6 -> listarHechizos();
                case 7 -> crearDragon();
                case 8 -> listarDragones();
                case 9 -> crearBosque();
                case 10 -> listarBosques();
                case 11 -> batalla();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 0); // Repetir hasta que el usuario salga
    }

    /**
     * Crear un nuevo mago leyendo los datos desde consola y usando el controlador.
     */
    private void crearMago() {
        System.out.print("Nombre del mago: ");
        String nombre = scanner.nextLine();
        System.out.print("Vida: ");
        int vida = Integer.parseInt(scanner.nextLine());
        System.out.print("Nivel de magia: ");
        int nivelMagia = Integer.parseInt(scanner.nextLine());

        Mago mago = magoCtrl.crearMago(nombre, vida, nivelMagia);
        System.out.println("Mago creado: " + mago);
    }

    /**
     * Listar todos los magos existentes en la base de datos.
     */
    private void listarMagos() {
        List<Mago> magos = magoCtrl.listarMagos();
        if (magos.isEmpty()) System.out.println("No hay magos.");
        else magos.forEach(System.out::println);
    }

    /**
     * Crear un nuevo monstruo con datos ingresados por consola.
     */
    private void crearMonstruo() {
        System.out.print("Nombre del monstruo: ");
        String nombre = scanner.nextLine();
        System.out.print("Vida: ");
        int vida = Integer.parseInt(scanner.nextLine());
        System.out.print("Fuerza: ");
        int fuerza = Integer.parseInt(scanner.nextLine());

        TipoMonstruo tipo = TipoMonstruo.OGRO;
        Monstruo m = monstruoCtrl.crearMonstruo(nombre, vida, tipo, fuerza);
        System.out.println("Monstruo creado: " + m);
    }

    /**
     * Listar todos los monstruos existentes en la base de datos.
     */
    private void listarMonstruos() {
        List<Monstruo> monstruos = monstruoCtrl.listarMonstruos();
        if (monstruos.isEmpty()) System.out.println("No hay monstruos.");
        else monstruos.forEach(System.out::println);
    }

    /**
     * Crear un nuevo hechizo usando nombre y efecto ingresados por consola.
     */
    private void crearHechizo() {
        System.out.print("Nombre del hechizo: ");
        String nombre = scanner.nextLine();
        System.out.print("Efecto: ");
        int efecto = Integer.parseInt(scanner.nextLine());

        Hechizo h = hechizoCtrl.crearHechizo(nombre, efecto);
        System.out.println("Hechizo creado: " + h);
    }

    /**
     * Listar todos los hechizos existentes en la base de datos.
     */
    private void listarHechizos() {
        List<Hechizo> hechizos = hechizoCtrl.listarHechizos();
        if (hechizos.isEmpty()) System.out.println("No hay hechizos.");
        else hechizos.forEach(System.out::println);
    }

    /**
     * Crear un nuevo dragón con datos de intensidad de fuego y resistencia.
     */
    private void crearDragon() {
        System.out.print("Nombre del dragón: ");
        String nombre = scanner.nextLine();
        System.out.print("Intensidad de fuego: ");
        int fuego = Integer.parseInt(scanner.nextLine());
        System.out.print("Resistencia: ");
        int resistencia = Integer.parseInt(scanner.nextLine());

        Dragon d = dragonCtrl.crearDragon(nombre, fuego, resistencia);
        System.out.println("Dragón creado: " + d);
    }

    /**
     * Listar todos los dragones existentes.
     */
    private void listarDragones() {
        List<Dragon> dragones = dragonCtrl.listarDragones();
        if (dragones.isEmpty()) System.out.println("No hay dragones.");
        else dragones.forEach(System.out::println);
    }

    /**
     * Crear un nuevo bosque con nombre y nivel de peligro.
     */
    private void crearBosque() {
        System.out.print("Nombre del bosque: ");
        String nombre = scanner.nextLine();
        System.out.print("Nivel de peligro: ");
        int nivel = Integer.parseInt(scanner.nextLine());

        Bosque b = bosqueCtrl.crearBosque(nombre, nivel, null);
        System.out.println("Bosque creado: " + b);
    }

    /**
     * Listar todos los bosques existentes.
     */
    private void listarBosques() {
        List<Bosque> bosques = bosqueCtrl.listarBosques();
        if (bosques.isEmpty()) System.out.println("No hay bosques.");
        else bosques.forEach(System.out::println);
    }

    /**
     * Ejecuta una batalla de ejemplo entre el primer mago y el primer monstruo.
     * Muestra por consola el daño infligido y la vida restante del monstruo.
     */
    private void batalla() {
        // Tomar el primer mago y el primer monstruo disponibles
        Optional<Mago> opcM = magoCtrl.listarMagos().stream().findFirst();
        Optional<Monstruo> opcMon = monstruoCtrl.listarMonstruos().stream().findFirst();
        Optional<Hechizo> opcH = hechizoCtrl.listarHechizos().stream().findFirst();

        if (opcM.isPresent() && opcMon.isPresent()) {
            Mago mago = opcM.get();
            Monstruo monstruo = opcMon.get();
            Hechizo hechizo = opcH.orElse(null);

            // Ejecutar ataque usando el BatallaController
            int damage = batallaCtrl.magoAtacaMonstruo(mago, monstruo, hechizo);

            // Mostrar resultado
            System.out.println(mago.getNombre() + " atacó a " + monstruo.getNombre() +
                    " con " + (hechizo != null ? hechizo.getNombre() : "ataque básico") +
                    ". Daño: " + damage + ". Vida restante del monstruo: " + monstruo.getVida());
        } else {
            System.out.println("No hay mago o monstruo disponible para la batalla.");
        }
    }
}
