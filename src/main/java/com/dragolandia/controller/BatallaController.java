package com.dragolandia.controller;

import com.dragolandia.model.*;
import com.dragolandia.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class BatallaController {
    private final JpaUtil jpa = JpaUtil.getInstance();

    /**
     * Aplica daño a una entidad (Mago o Monstruo)
     * Si la vida llega a 0, elimina la entidad de la BD
     * Si no, solo actualiza su vida
     */
    public void recibirDano(Object entidad, int damage) {
        EntityManager em = jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            if (entidad instanceof Monstruo m) {
                m = em.merge(m);
                m.setVida(m.getVida() - damage);

                if (m.getVida() <= 0) {
                    em.remove(m); // Borrar si muere
                }
            } else if (entidad instanceof Mago m) {
                m = em.merge(m);
                m.setVida(m.getVida() - damage);

                if (m.getVida() <= 0) {
                    em.remove(m); // Borrar si muere
                }
            }

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }

            System.err.println("Error en batalla: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Asigna un nuevo jefe al bosque si el actual es null o ha muerto
     */
    public void asignarJefe(Bosque bosque, List<Monstruo> monstruosVivos) {
        EntityManager em = jpa.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Bosque b = em.merge(bosque);

            // Si no tiene jefe o el jefe murió
            if (b.getMonstruoJefe() == null || b.getMonstruoJefe().getVida() <= 0) {
                if (!monstruosVivos.isEmpty()) {
                    // El primero de la lista pasa a ser jefe
                    b.setMonstruoJefe(monstruosVivos.get(0));

                    System.out.println("Nuevo Jefe asignado: " + monstruosVivos.get(0).getNombre());
                } else {
                    b.setMonstruoJefe(null);
                }
            }
            tx.commit();

            bosque.setMonstruoJefe(b.getMonstruoJefe());
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }
}