package com.dragolandia.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Singleton que provee un EntityManagerFactory para toda la aplicación.
 */
public class JpaUtil {

    private static final JpaUtil instance = new JpaUtil();
    private EntityManagerFactory emf;

    private JpaUtil() {
        emf = Persistence.createEntityManagerFactory("dragolandiaServicio");
    }

    /** Obtener la instancia singleton */
    public static JpaUtil getInstance() {
        return instance;
    }

    /** Crear un EntityManager */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /** Cerrar el EntityManagerFactory al terminar la aplicación */
    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
