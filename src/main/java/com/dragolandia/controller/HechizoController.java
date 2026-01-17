package com.dragolandia.controller;

import com.dragolandia.model.Hechizo;
import com.dragolandia.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * Controlador para la entidad Hechizo.
 * Gestiona operaciones CRUD sobre hechizos en la base de datos.
 * Utiliza JpaUtil para obtener el EntityManager con patrón singleton.
 */
public class HechizoController {

    // Singleton de JpaUtil para obtener un EntityManager compartido
    private final JpaUtil jpa = JpaUtil.getInstance();

    /**
     * Crea un nuevo hechizo y lo persiste en la base de datos.
     *
     * @param nombre Nombre del hechizo
     * @param efecto Valor del efecto del hechizo
     * @return el hechizo creado
     */
    public Hechizo crearHechizo(String nombre, int efecto) {
        EntityManager em = jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        Hechizo hechizoCreado = null;

        try {
            // Inicia transacción, persiste el hechizo y confirma cambios
            tx.begin();
            Hechizo h = new Hechizo(nombre, efecto);
            em.persist(h);
            tx.commit();

            hechizoCreado = h;
        } catch (Exception e) { // Captura la excepción y hace rollback de la transacción en caso de error
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            System.err.println("Error al crear el hechizo: " + e.getMessage());
        } finally { // Cierra el EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return hechizoCreado;
    }

    /**
     * Lista todos los hechizos existentes en la base de datos.
     *
     * @return lista de hechizos
     */
    public List<Hechizo> listarHechizos() {
        EntityManager em = jpa.getEntityManager();
        List<Hechizo> hechizos = null;

        try {
            // Crea la query y se obtienen sus resultados
            TypedQuery<Hechizo> query = em.createQuery("SELECT h FROM Hechizos h", Hechizo.class);
            hechizos = query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar los hechizos: " + e.getMessage());
        } finally { // Cierra el EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return hechizos;
    }

    /**
     * Busca un hechizo por su ID.
     * * @param id ID del hechizo a buscar
     * 
     * @return el hechizo si existe, o null si no se encuentra
     */
    public Hechizo buscarHechizo(int id) {
        EntityManager em = jpa.getEntityManager();
        Hechizo hechizoEncontrado = null;

        try {
            hechizoEncontrado = em.find(Hechizo.class, id);

        } catch (Exception e) {
            System.err.println("Error al intentar buscar el hechizo con id " + id + ": " + e.getMessage());

        } finally { // Cierra el EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return hechizoEncontrado;
    }

    /**
     * Actualiza los datos de un hechizo existente.
     * * @param id ID del hechizo a actualizar
     * 
     * @param nombre Nuevo nombre
     * @param efecto Nuevo efecto
     * @return true si se actualizó correctamente, false si no existe o hubo error
     */
    public boolean actualizarHechizo(int id, String nombre, int efecto) {
        EntityManager em = jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean actualizado = false;

        try {
            tx.begin();

            // Se busca el hechizo existente por su ID
            Hechizo h = em.find(Hechizo.class, id);

            if (h != null) {
                // Si existe, se modifican sus atributos
                h.setNombre(nombre);
                h.setEfecto(efecto);

                // Se guardan los cambios
                em.merge(h);

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

            System.err.println("Error al actualizar el hechizo: " + e.getMessage());
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
     * Elimina un hechizo de la base de datos.
     *
     * @param id ID del hechizo a eliminar
     * @return true si se eliminó correctamente, false si no existe
     */
    public boolean eliminarHechizo(int id) {
        EntityManager em = jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean eliminado = false;

        try {
            // Inicia transacción, busca el hechizo y lo elimina de la BD
            tx.begin();
            Hechizo h = em.find(Hechizo.class, id); // buscar hechizo

            if (h != null) {
                em.remove(h); // eliminar de la BD
                eliminado = true;
            }

            tx.commit();
        } catch (Exception e) { // Captura la excepción y hace rollback de la transacción en caso de error
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            System.err.println("Error al eliminar el hechizo: " + e.getMessage());
        } finally { // Cierra el EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return eliminado;
    }
}
