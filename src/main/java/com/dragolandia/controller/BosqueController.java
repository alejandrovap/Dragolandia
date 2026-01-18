package com.dragolandia.controller;

import com.dragolandia.model.Bosque;
import com.dragolandia.model.Monstruo;
import com.dragolandia.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * Controlador para la entidad Bosque
 * Se encarga de realizar las operaciones CRUD
 */
public class BosqueController {

    // Utilidad singleton para obtener el EntityManager
    private final JpaUtil jpa = JpaUtil.getInstance();

    /**
     * Crea un nuevo bosque y lo persiste en la base de datos
     * 
     * @param nombre       Nombre del bosque
     * @param nivelPeligro Nivel de peligro del bosque
     * @param jefe         Monstruo jefe del bosque (puede ser null)
     * @return el bosque creado
     */
    public Bosque crearBosque(String nombre, int nivelPeligro, Monstruo jefe) {
        EntityManager em = jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        Bosque bosqueCreado = null;

        try {
            // Inicia transacción, persiste el bosque y confirma cambios
            tx.begin();
            Bosque b = new Bosque(nombre, nivelPeligro, jefe);
            em.persist(b);
            tx.commit();

            bosqueCreado = b;
        } catch (Exception e) { // Captura la excepción y hace rollback de la transacción en caso de error
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            System.err.println("Error al crear el bosque: " + e.getMessage());
        } finally { // Cierra el EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return bosqueCreado;
    }

    /**
     * Lista todos los bosques existentes en la base de datos
     * 
     * @return lista de bosques
     */
    public List<Bosque> listarBosques() {
        EntityManager em = jpa.getEntityManager();
        List<Bosque> bosques = null;

        try {
            // Crea la query y se obtienen sus resultados
            TypedQuery<Bosque> query = em.createQuery("SELECT b FROM Bosqu b", Bosque.class);
            bosques = query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar los bosques: " + e.getMessage());
        } finally { // Cierra el EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return bosques;
    }

    /**
     * Busca un bosque por su ID
     * * @param id ID del bosque a buscar
     * 
     * @return el bosque si existe, o null si no se encuentra
     */
    public Bosque buscarBosque(int id) {
        EntityManager em = jpa.getEntityManager();
        Bosque bosqueEncontrado = null;

        try {
            bosqueEncontrado = em.find(Bosque.class, id);

        } catch (Exception e) {
            System.err.println("Error al intentar buscar el bosque con id " + id + ": " + e.getMessage());

        } finally { // Cierra el EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return bosqueEncontrado;
    }

    /**
     * Actualiza los datos de un bosque existente
     * * @param id ID del bosque a actualizar
     * 
     * @param nombre       Nuevo nombre
     * @param nivelPeligro Nuevo nivel de peligro
     * @param jefe         Nuevo monstruo jefe
     * @return true si se actualizó correctamente, false si no existe o hubo error
     */
    public boolean actualizarBosque(int id, String nombre, int nivelPeligro, Monstruo jefe) {
        EntityManager em = jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean actualizado = false;

        try {
            tx.begin();

            // Se busca el bosque existente por su ID
            Bosque b = em.find(Bosque.class, id);

            if (b != null) {
                // Si existe, se modifican sus atributos
                b.setNombre(nombre);
                b.setNivelPeligro(nivelPeligro);
                b.setMonstruoJefe(jefe);

                // Se guardan los cambios
                em.merge(b);

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

            System.err.println("Error al actualizar el bosque: " + e.getMessage());
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
     * Elimina un bosque de la base de datos
     * 
     * @param id ID del bosque a eliminar
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarBosque(int id) {
        EntityManager em = jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean eliminado = false;

        try {
            // Inicia transacción, busca el bosque y lo elimina de la BD
            tx.begin();
            Bosque b = em.find(Bosque.class, id); // buscar bosque

            if (b != null) {
                em.remove(b); // eliminar de la BD
                eliminado = true;
            }

            tx.commit();
        } catch (Exception e) { // Captura la excepción y hace rollback de la transacción en caso de error
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            System.err.println("Error al eliminar el bosque: " + e.getMessage());
        } finally { // Cierra el EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return eliminado;
    }

    /**
     * Agrega un monstruo a la lista de monstruos de un bosque
     * 
     * @param bosque   Bosque al que se agrega el monstruo
     * @param monstruo Monstruo a agregar
     * @return true si se agregó correctamente, false si hubo error
     */
    public boolean agregarMonstruo(Bosque bosque, Monstruo monstruo) {
        if (bosque == null || monstruo == null) {
            return false;
        }

        EntityManager em = jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean agregado = false;

        try {
            tx.begin();

            Bosque b = em.find(Bosque.class, bosque.getId());

            if (b != null) {
                b.getMonstruos().add(monstruo);

                tx.commit();
                agregado = true;
            } else {
                // Si no se encuentra el bosque, no se hace nada
                tx.commit();
            }

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al agregar monstruo al bosque: " + e.getMessage());
            agregado = false;

        } finally { // Cierra el EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return agregado;
    }

    /**
     * Cambia el monstruo jefe de un bosque
     * 
     * @param bosque    Bosque donde se quiere cambiar el jefe
     * @param nuevoJefe Monstruo que será el nuevo jefe
     * @return true si se cambió correctamente, false si hay algún error
     */
    public boolean cambiarJefe(Bosque bosque, Monstruo nuevoJefe) {
        if (bosque == null || nuevoJefe == null) {
            return false;
        }

        EntityManager em = jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean cambiado = false;

        try {
            tx.begin();

            // Busca el bosque en la BD
            Bosque b = em.find(Bosque.class, bosque.getId());

            if (b != null) {
                // Se asigna el nuevo jefe
                b.setMonstruoJefe(nuevoJefe);

                // Confirma los cambios
                tx.commit();
                cambiado = true;
            } else {
                // Si el bosque no existe en BD, cerramos transacción sin hacer nada
                tx.commit();
            }

        } catch (Exception e) {
            // Manejo de errores y rollback de la transacción
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            System.err.println("Error al cambiar el jefe del bosque: " + e.getMessage());
            cambiado = false;

        } finally {
            // Cierra el EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return cambiado;
    }
}
