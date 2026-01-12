package com.dragolandia.controller;

import com.dragolandia.model.Bosque;
import com.dragolandia.model.Monstruo;
import com.dragolandia.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para la entidad Bosque.
 * Se encarga de realizar las operaciones CRUD.
 */
public class BosqueController {

    // Utilidad singleton para obtener el EntityManager
    private final JpaUtil jpa = JpaUtil.getInstance();

    /**
     * Crea un nuevo bosque y lo persiste en la base de datos.
     * 
     * @param nombre Nombre del bosque
     * @param nivelPeligro Nivel de peligro del bosque
     * @param jefe Monstruo jefe del bosque (puede ser null)
     * @return el bosque creado
     */
    public Bosque crearBosque(String nombre, int nivelPeligro, Monstruo jefe) {
        EntityManager em = jpa.getEntityManager();
        Bosque b = new Bosque(nombre, nivelPeligro, jefe);

        // Inicia la transacción, persiste el bosque y hace commit
        em.getTransaction().begin();
        em.persist(b);
        em.getTransaction().commit();
        em.close();

        return b;
    }

    /**
     * Lista todos los bosques existentes en la base de datos.
     * 
     * @return lista de bosques
     */
    public List<Bosque> listarBosques() {
        EntityManager em = jpa.getEntityManager();
        TypedQuery<Bosque> query = em.createQuery("SELECT b FROM Bosque b", Bosque.class);
        List<Bosque> bosques = query.getResultList();
        em.close();
        return bosques;
    }

    /**
     * Busca un bosque por su ID.
     * 
     * @param id ID del bosque a buscar
     * @return Optional con el bosque si existe, o vacío si no
     */
    public Optional<Bosque> buscarBosque(int id) {
        EntityManager em = jpa.getEntityManager();
        Bosque b = em.find(Bosque.class, id); // busca el bosque en la BD
        em.close();
        return Optional.ofNullable(b);
    }

    /**
     * Actualiza los datos de un bosque existente.
     * 
     * @param id ID del bosque a actualizar
     * @param nombre Nuevo nombre
     * @param nivelPeligro Nuevo nivel de peligro
     * @param jefe Nuevo monstruo jefe
     * @return true si se actualizó correctamente, false si no existe
     */
    public boolean actualizarBosque(int id, String nombre, int nivelPeligro, Monstruo jefe) {
        EntityManager em = jpa.getEntityManager();
        boolean actualizado = false;

        Bosque b = new Bosque();
        if (b != null) {
            b.setNombre(nombre);
            b.setNivelPeligro(nivelPeligro);
            b.setMonstruoJefe(jefe);
            actualizado = true; // indicar que se actualizó
        }
        em.getTransaction().begin();
        em.merge(b);
        em.getTransaction().commit();
        em.close();

        return actualizado;
    }

    /**
     * Elimina un bosque de la base de datos.
     * 
     * @param id ID del bosque a eliminar
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarBosque(int id) {
        EntityManager em = jpa.getEntityManager();
        boolean eliminado = false;

        em.getTransaction().begin();
        Bosque b = em.find(Bosque.class, id); // buscar bosque
        if (b != null) {
            em.remove(b); // eliminar de la BD
            eliminado = true;
        }
        em.getTransaction().commit();
        em.close();

        return eliminado;
    }

    /**
     * Agrega un monstruo a la lista de monstruos de un bosque.
     * 
     * @param bosque Bosque al que se agrega el monstruo
     * @param monstruo Monstruo a agregar
     * @return true si se agregó correctamente, false si el bosque o monstruo son nulos
     */
    public boolean agregarMonstruo(Bosque bosque, Monstruo monstruo) {
        boolean agregado = false;
        if (bosque != null && monstruo != null) {
            EntityManager em = jpa.getEntityManager();
            em.getTransaction().begin();
            Bosque b = em.find(Bosque.class, bosque.getId());
            if (b != null) {
                b.getMonstruos().add(monstruo); // añadir a la lista de monstruos
                agregado = true;
            }
            em.getTransaction().commit();
            em.close();
        }
        return agregado;
    }

    /**
     * Cambia el monstruo jefe de un bosque.
     * 
     * @param bosque Bosque donde se quiere cambiar el jefe
     * @param nuevoJefe Monstruo que será el nuevo jefe
     * @return true si se cambió correctamente, false si alguno es nulo o el bosque no existe
     */
    public boolean cambiarJefe(Bosque bosque, Monstruo nuevoJefe) {
        boolean cambiado = false;
        if (bosque != null && nuevoJefe != null) {
            EntityManager em = jpa.getEntityManager();
            em.getTransaction().begin();
            Bosque b = em.find(Bosque.class, bosque.getId());
            if (b != null) {
                b.setMonstruoJefe(nuevoJefe); // asignar nuevo jefe
                cambiado = true;
            }
            em.getTransaction().commit();
            em.close();
        }
        return cambiado;
    }
}
