package com.dragolandia.controller;

import com.dragolandia.model.Monstruo;
import com.dragolandia.model.TipoMonstruo;
import com.dragolandia.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para la entidad Monstruo.
 * Gestiona operaciones CRUD sobre monstruos en la base de datos.
 * Utiliza JpaUtil para obtener el EntityManager con patrón singleton.
 */
public class MonstruoController {

    // Singleton de JpaUtil para obtener un EntityManager compartido
    private final JpaUtil jpa = JpaUtil.getInstance();

    /**
     * Crea un nuevo monstruo y lo persiste en la base de datos.
     *
     * @param nombre Nombre del monstruo
     * @param vida   Vida inicial del monstruo
     * @param tipo   Tipo del monstruo (OGRO, TROLL, ESPECTRO)
     * @param fuerza Fuerza del monstruo
     * @return el monstruo creado
     */
    public Monstruo crearMonstruo(String nombre, int vida, TipoMonstruo tipo, int fuerza) {
        EntityManager em = jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        Monstruo monstruoCreado = null;

        try {
            // Inicia transacción, persiste el monstruo y confirma cambios
            tx.begin();
            Monstruo m = new Monstruo(nombre, vida, tipo, fuerza);
            em.persist(m);
            tx.commit();

            monstruoCreado = m;
        } catch (Exception e) { // Captura la excepción y hace rollback de la transacción en caso de error
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            System.err.println("Error al crear el monstruo: " + e.getMessage());
        } finally { // Cierra el EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return monstruoCreado;
    }

    /**
     * Lista todos los monstruos existentes en la base de datos.
     *
     * @return lista de monstruos
     */
    public List<Monstruo> listarMonstruos() {
        EntityManager em = jpa.getEntityManager();
        TypedQuery<Monstruo> query = em.createQuery("SELECT m FROM Monstruo m", Monstruo.class);
        List<Monstruo> monstruos = query.getResultList(); // obtener resultados
        em.close();
        return monstruos;
    }

    /**
     * Busca un monstruo por su ID.
     *
     * @param id ID del monstruo a buscar
     * @return Optional con el monstruo si existe, o vacío si no
     */
    public Optional<Monstruo> buscarMonstruo(int id) {
        EntityManager em = jpa.getEntityManager();
        Monstruo m = em.find(Monstruo.class, id); // buscar en la BD
        em.close();
        return Optional.ofNullable(m);
    }

    /**
     * Actualiza los datos de un monstruo existente.
     *
     * @param id     ID del monstruo a actualizar
     * @param nombre Nuevo nombre
     * @param vida   Nueva vida
     * @param fuerza Nueva fuerza
     * @return true si se actualizó correctamente, false si el monstruo no existe
     */
    public boolean actualizarMonstruo(int id, String nombre, int vida, int fuerza) {
        EntityManager em = jpa.getEntityManager();
        boolean actualizado = false;

        em.getTransaction().begin();
        Monstruo m = em.find(Monstruo.class, id); // buscar monstruo
        if (m != null) {
            m.setNombre(nombre);
            m.setVida(vida);
            m.setFuerza(fuerza);
            actualizado = true; // indicar éxito
        }
        em.getTransaction().commit();
        em.close();

        return actualizado;
    }

    /**
     * Elimina un monstruo de la base de datos.
     *
     * @param id ID del monstruo a eliminar
     * @return true si se eliminó correctamente, false si no existe
     */
    public boolean eliminarMonstruo(int id) {
        EntityManager em = jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean eliminado = false;

        try {
            // Inicia transacción, busca el monstruo y lo elimina de la BD
            tx.begin();
            Monstruo m = em.find(Monstruo.class, id); // buscar monstruo

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
