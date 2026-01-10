package com.dragolandia.controller;

import com.dragolandia.model.Dragon;
import com.dragolandia.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

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
     * @param nombre Nombre del dragón
     * @param intensidadFuego Nivel de fuego que puede exhalar
     * @param resistencia Resistencia del dragón
     * @return el dragón creado
     */
    public Dragon crearDragon(String nombre, int intensidadFuego, int resistencia) {
        EntityManager em = jpa.getEntityManager();
        Dragon d = new Dragon(nombre, intensidadFuego, resistencia);

        // Inicia la transacción, persiste y confirma cambios
        em.getTransaction().begin();
        em.persist(d);
        em.getTransaction().commit();
        em.close();

        return d;
    }

    /**
     * Lista todos los dragones existentes en la base de datos.
     *
     * @return lista de dragones
     */
    public List<Dragon> listarDragones() {
        EntityManager em = jpa.getEntityManager();
        TypedQuery<Dragon> query = em.createQuery("SELECT d FROM Dragon d", Dragon.class);
        List<Dragon> dragones = query.getResultList(); // obtener resultados
        em.close();
        return dragones;
    }

    /**
     * Busca un dragón por su ID.
     *
     * @param id ID del dragón a buscar
     * @return Optional con el dragón si existe, o vacío si no
     */
    public Optional<Dragon> buscarDragon(int id) {
        EntityManager em = jpa.getEntityManager();
        Dragon d = em.find(Dragon.class, id); // búsqueda en la base de datos
        em.close();
        return Optional.ofNullable(d);
    }

    /**
     * Actualiza los datos de un dragón existente.
     *
     * @param id ID del dragón a actualizar
     * @param nombre Nuevo nombre
     * @param intensidadFuego Nueva intensidad de fuego
     * @param resistencia Nueva resistencia
     * @return true si se actualizó correctamente, false si el dragón no existe
     */
    public boolean actualizarDragon(int id, String nombre, int intensidadFuego, int resistencia) {
        EntityManager em = jpa.getEntityManager();
        boolean actualizado = false;

        em.getTransaction().begin();
        Dragon d = em.find(Dragon.class, id); // buscar dragón
        if (d != null) {
            d.setNombre(nombre);
            d.setIntensidadFuego(intensidadFuego);
            d.setResistencia(resistencia);
            actualizado = true; // indicar éxito
        }
        em.getTransaction().commit();
        em.close();

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
        boolean eliminado = false;

        em.getTransaction().begin();
        Dragon d = em.find(Dragon.class, id); // buscar dragón
        if (d != null) {
            em.remove(d); // eliminar de la BD
            eliminado = true;
        }
        em.getTransaction().commit();
        em.close();

        return eliminado;
    }
}
