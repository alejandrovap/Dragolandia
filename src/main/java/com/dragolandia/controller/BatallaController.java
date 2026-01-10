package com.dragolandia.controller;

import com.dragolandia.model.Hechizo;
import com.dragolandia.model.Mago;
import com.dragolandia.model.Monstruo;
import com.dragolandia.util.JpaUtil;
import jakarta.persistence.EntityManager;

/**
 * Controlador que maneja las batallas entre magos y monstruos.
 */
public class BatallaController {

    private final JpaUtil jpa = JpaUtil.getInstance();

    /**
     * Mago ataca a un monstruo usando un hechizo.
     * Modifica la vida del monstruo en la base de datos.
     *
     * @param mago Mago atacante
     * @param monstruo Monstruo objetivo
     * @param hechizo Hechizo; si es null, se usa daño básico
     * @return cantidad de daño infligido
     */
    public int magoAtacaMonstruo(Mago mago, Monstruo monstruo, Hechizo hechizo) {
        int damage;

        if (hechizo != null) {
            if (hechizo.getNombre().equals("Bola de Nieve")) {
                damage = monstruo.getVida();
                monstruo.setVida(0);
            } else {
                damage = hechizo.getEfecto();
                monstruo.setVida(Math.max(0, monstruo.getVida() - damage));
            }
        } else {
            damage = mago.getNivelMagia();
            monstruo.setVida(Math.max(0, monstruo.getVida() - damage));
        }

        // Persistir el cambio de vida en la base de datos
        EntityManager em = jpa.getEntityManager();
        em.getTransaction().begin();
        Monstruo m = em.find(Monstruo.class, monstruo.getId());
        if (m != null) {
            m.setVida(monstruo.getVida());
        }
        em.getTransaction().commit();
        em.close();

        return damage;
    }

    /**
     * Monstruo ataca a un mago.
     * Modifica la vida del mago en la base de datos.
     *
     * @param monstruo Monstruo atacante
     * @param mago Mago objetivo
     * @return daño infligido
     */
    public int monstruoAtacaMago(Monstruo monstruo, Mago mago) {
        int damage = monstruo.getFuerza();
        mago.setVida(Math.max(0, mago.getVida() - damage));

        EntityManager em = jpa.getEntityManager();
        em.getTransaction().begin();
        Mago m = em.find(Mago.class, mago.getId());
        if (m != null) {
            m.setVida(mago.getVida());
        }
        em.getTransaction().commit();
        em.close();

        return damage;
    }
}
