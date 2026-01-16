package com.dragolandia.controller;

import com.dragolandia.model.Mago;
import com.dragolandia.util.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para la entidad Mago.
 * Gestiona operaciones CRUD sobre magos en la base de datos.
 * Utiliza JpaUtil para obtener el EntityManager con patrón singleton.
 */
public class MagoController {

    // Singleton de JpaUtil para obtener un EntityManager compartido
    private final JpaUtil jpa = JpaUtil.getInstance();

    /**
     * Crea un nuevo mago y lo persiste en la base de datos.
     *
     * @param nombre     Nombre del mago
     * @param vida       Vida inicial del mago
     * @param nivelMagia Nivel de magia del mago
     * @return el mago creado
     */
    public Mago crearMago(String nombre, int vida, int nivelMagia) {
        EntityManager em = jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        Mago magoCreado = null;

        try {
            // Inicia transacción, persiste el mago y confirma cambios
            tx.begin();
            Mago m = new Mago(nombre, vida, nivelMagia);
            em.persist(m);
            tx.commit();

            magoCreado = m;
        } catch (Exception e) { // Captura la excepción y hace rollback de la transacción en caso de error
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            System.err.println("Error al crear el mago: " + e.getMessage());
        } finally { // Cierra el EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return magoCreado;
    }

    /**
     * Lista todos los magos existentes en la base de datos.
     *
     * @return lista de magos
     */
    public List<Mago> listarMagos() {
        EntityManager em = jpa.getEntityManager();
        TypedQuery<Mago> query = em.createQuery("SELECT m FROM Mago m", Mago.class);
        List<Mago> magos = query.getResultList(); // obtener resultados
        em.close();
        return magos;
    }

    /**
     * Busca un mago por su ID.
     *
     * @param id ID del mago a buscar
     * @return Optional con el mago si existe, o vacío si no
     */
    public Optional<Mago> buscarMago(int id) {
        EntityManager em = jpa.getEntityManager();
        Mago mago = em.find(Mago.class, id); // buscar en la BD
        em.close();
        return Optional.ofNullable(mago);
    }

    /**
     * Actualiza los datos de un mago existente.
     *
     * @param id         ID del mago a actualizar
     * @param nombre     Nuevo nombre
     * @param vida       Nueva vida
     * @param nivelMagia Nuevo nivel de magia
     * @return true si se actualizó correctamente, false si el mago no existe
     */
    public boolean actualizarMago(int id, String nombre, int vida, int nivelMagia) {
        EntityManager em = jpa.getEntityManager();
        boolean actualizado = false;

        em.getTransaction().begin();
        Mago mago = em.find(Mago.class, id); // buscar mago
        if (mago != null) {
            mago.setNombre(nombre);
            mago.setVida(vida);
            mago.setNivelMagia(nivelMagia);
            actualizado = true; // indicar éxito
        }
        em.getTransaction().commit();
        em.close();

        return actualizado;
    }

    /**
     * Elimina un mago de la base de datos.
     *
     * @param id ID del mago a eliminar
     * @return true si se eliminó correctamente, false si no existe
     */
    public boolean eliminarMago(int id) {
        EntityManager em = jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean eliminado = false;

        try {
            // Inicia transacción, busca el mago y lo elimina de la BD
            tx.begin();
            Mago m = em.find(Mago.class, id); // buscar mago

            if (m != null) {
                em.remove(m); // eliminar de la BD
                eliminado = true;
            }

            tx.commit();
        } catch (Exception e) { // Captura la excepción y hace rollback de la transacción en caso de error
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            System.err.println("Error al eliminar el monstruo: " + e.getMessage());
        } finally { // Cierra el EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return eliminado;
    }
}
