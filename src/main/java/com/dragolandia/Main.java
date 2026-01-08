package com.dragolandia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dragolandia.controller.ControladorBatalla;
import com.dragolandia.model.Bosque;
import com.dragolandia.model.Dragon;
import com.dragolandia.model.Mago;
import com.dragolandia.model.Monstruo;
import com.dragolandia.model.TipoMonstruo;
import com.dragolandia.view.Vista;

public class Main {
    public static void main(String[] args) {
        Session session = null;

        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory()) { //hacer con EntityManagerFactory, meter carpeta META-INF en resources y quitar 
            session = factory.getCurrentSession();
            Transaction tx = session.beginTransaction();

            Mago mago = ControladorMago.crearMago();
            session.persist(mago);

            Bosque bosque = ControladorBosque.crearBosque();
            session.persist(bosque);

            
            Dragon dragon = new Dragon("Dragón de Piedra", 40, 30);

            int numMonstruos = Vista.leerEntero("Número de monstruos en el bosque: ");
            Monstruo jefe = null;

            for (int i = 1; i <= numMonstruos; i++) {
                String nombreMonstruo = Vista.leerTexto("Nombre del monstruo " + i + ": ");
                int vidaMonstruo = Vista.leerEntero("Vida del monstruo " + i + ": ");
                int fuerza = Vista.leerEntero("Fuerza del monstruo " + i + ": ");
                TipoMonstruo tipoMonstruo = TipoMonstruo.valueOf(
                        Vista.leerTexto("Tipo de monstruo (OGRO/TROLL/ESPECTRO): ").toUpperCase());

                Monstruo monstruo = new Monstruo(nombreMonstruo, vidaMonstruo, tipoMonstruo, fuerza);
                monstruo.setBosque(bosque);
                bosque.addMonstruo(monstruo);

                if (jefe == null) {
                    jefe = monstruo;
                }
            }

            bosque.setMonstruoJefe(jefe);

            session.persist(bosque);
            session.persist(dragon);

            tx.commit();

            ControladorBatalla.batalla(mago, jefe);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}