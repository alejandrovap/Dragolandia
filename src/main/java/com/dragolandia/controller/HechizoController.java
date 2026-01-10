package com.dragolandia.controller;

import com.dragolandia.model.Hechizo;
import com.dragolandia.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

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
        Hechizo h = new Hechizo(nombre, efecto);

        // Inicia transacción, persiste y confirma cambios
        em.getTransaction().begin();
        em.persist(h);
        em.getTransaction().commit();
        em.close();

        return h;
    }

    /**
     * Lista todos los hechizos existentes en la base de datos.
     *
     * @return lista de hechizos
     */
    public List<Hechizo> listarHechizos() {
        EntityManager em = jpa.getEntityManager();
        TypedQuery<Hechizo> query = em.createQuery("SELECT h FROM Hechizo h", Hechizo.class);
        List<Hechizo> hechizos = query.getResultList(); // obtener resultados
        em.close();
        return hechizos;
    }

    /**
     * Busca un hechizo por su ID.
     *
     * @param id ID del hechizo a buscar
     * @return Optional con el hechizo si existe, o vacío si no
     */
    public Optional<Hechizo> buscarHechizo(int id) {
        EntityManager em = jpa.getEntityManager();
        Hechizo h = em.find(Hechizo.class, id); // buscar en la BD
        em.close();
        return Optional.ofNullable(h);
    }

    /**
     * Actualiza los datos de un hechizo existente.
     *
     * @param id ID del hechizo a actualizar
     * @param nombre Nuevo nombre del hechizo
     * @param efecto Nuevo valor del efecto
     * @return true si se actualizó correctamente, false si el hechizo no existe
     */
    public boolean actualizarHechizo(int id, String nombre, int efecto) {
        EntityManager em = jpa.getEntityManager();
        boolean actualizado = false;

        em.getTransaction().begin();
        Hechizo h = em.find(Hechizo.class, id); // buscar hechizo
        if (h != null) {
            h.setNombre(nombre);
            h.setEfecto(efecto);
            actualizado = true; // indicar éxito
        }
        em.getTransaction().commit();
        em.close();

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
        boolean eliminado = false;

        em.getTransaction().begin();
        Hechizo h = em.find(Hechizo.class, id); // buscar hechizo
        if (h != null) {
            em.remove(h); // eliminar de la BD
            eliminado = true;
        }
        em.getTransaction().commit();
        em.close();

        return eliminado;
    }
}
