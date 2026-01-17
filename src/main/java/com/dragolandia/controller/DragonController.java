package com.dragolandia.controller;

import com.dragolandia.model.Dragon;
import com.dragolandia.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * Controlador para la entidad Dragon.
 * Gestiona operaciones CRUD sobre dragones en la base de datos.
 * Utiliza JpaUtil para obtener el EntityManager con patrón singleton.
 */
public class DragonController {

    // Singleton de JpaUtil para obtener EntityManager compartido
    private final JpaUtil jpa = JpaUtil.getInstance();

    /**
     * Crea un nuevo dragón y lo persiste en la base de datos.
     *
     * @param nombre          Nombre del dragón
     * @param intensidadFuego Nivel de fuego que puede exhalar
     * @param resistencia     Resistencia del dragón
     * @return el dragón creado
     */
    public Dragon crearDragon(String nombre, int intensidadFuego, int resistencia) {
        EntityManager em = jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        Dragon dragonCreado = null;

        try {
            // Inicia transacción, persiste el dragón y confirma cambios
            tx.begin();
            Dragon d = new Dragon(nombre, intensidadFuego, resistencia);
            em.persist(d);
            tx.commit();

            dragonCreado = d;
        } catch (Exception e) { // Captura la excepción y hace rollback de la transacción en caso de error
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            System.err.println("Error al crear el dragón: " + e.getMessage());
        } finally { // Cierra el EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return dragonCreado;
    }

    /**
     * Lista todos los dragones existentes en la base de datos.
     *
     * @return lista de dragones
     */
    public List<Dragon> listarDragones() {
        EntityManager em = jpa.getEntityManager();
        List<Dragon> dragones = null;

        try {
            // Crea la query y se obtienen sus resultados
            TypedQuery<Dragon> query = em.createQuery("SELECT d FROM Dragones d", Dragon.class);
            dragones = query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar los dragones: " + e.getMessage());
        } finally { // Cierra el EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return dragones;
    }

    /**
     * Busca un dragón por su ID.
     * * @param id ID del dragón a buscar
     * 
     * @return el dragón si existe, o null si no se encuentra
     */
    public Dragon buscarDragon(int id) {
        EntityManager em = jpa.getEntityManager();
        Dragon dragonEncontrado = null;

        try {
            dragonEncontrado = em.find(Dragon.class, id);

        } catch (Exception e) {
            System.err.println("Error al intentar buscar el dragón con id " + id + ": " + e.getMessage());

        } finally { // Cierra el EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return dragonEncontrado;
    }

    /**
     * Actualiza los datos de un dragón existente.
     * * @param id ID del dragón a actualizar
     * 
     * @param nombre         Nuevo nombre
     * @param intesidadFuego Nueva intesidad de fuego
     * @param resistencia    Nueva resistencia
     * @return true si se actualizó correctamente, false si no existe o hubo error
     */
    public boolean actualizarDragon(int id, String nombre, int intensidadFuego, int resistencia) {
        EntityManager em = jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean actualizado = false;

        try {
            tx.begin();

            // Se busca el dragón existente por su ID
            Dragon d = em.find(Dragon.class, id);

            if (d != null) {
                // Si existe, se modifican sus atributos
                d.setNombre(nombre);
                d.setIntensidadFuego(intensidadFuego);
                d.setResistencia(resistencia);

                // Se guardan los cambios
                em.merge(d);

                // Se confirman los cambios
                tx.commit();
                actualizado = true;
            } else {
                // Si no existe, se cierra la transacción sin hacer ningún cambio
                tx.commit();
            }

        } catch (Exception e) {
            // En caso de error, se deshace cualquier cambio
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            System.err.println("Error al actualizar el dragón: " + e.getMessage());
            actualizado = false;
        } finally {
            // Se cierra el EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return actualizado;
    }

    /**
     * Elimina un dragón de la base de datos.
     *
     * @param id ID del dragón a eliminar
     * @return true si se eliminó correctamente, false si no existe
     */
    public boolean eliminarDragon(int id) {
        EntityManager em = jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean eliminado = false;

        try {
            // Inicia transacción, busca el dragón y lo elimina de la BD
            tx.begin();
            Dragon d = em.find(Dragon.class, id); // buscar dragón

            if (d != null) {
                em.remove(d); // eliminar de la BD
                eliminado = true;
            }

            tx.commit();
        } catch (Exception e) { // Captura la excepción y hace rollback de la transacción en caso de error
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            System.err.println("Error al eliminar el dragón: " + e.getMessage());
        } finally { // Cierra el EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return eliminado;
    }
}
