/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Persona;
import Entidades.Tramite;
import Utilidades.Encriptacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jctri
 */
public class TramiteDAO implements ITramiteDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("conexion");

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public List<Tramite> listaTramite(int inicio, int fin) {
        EntityManager em = null;
        try {
            em = getEntityManager();

            /**
             * Aqui hay un error, porque toma los ID para hacer la seleccion
             * tipo, select * from tramite where id >= inicio and id <= id ta
             * raro asi que no se que hacer para que las tome por filas y no por
             * el id, a menos que tomemos todos los tramites y en un array en
             * algun lado solo tomemos 10 de las filas que regrese pero nunca
             * van a estar bien
             */
            List<Tramite> tramites = em.createQuery("SELECT t FROM Tramite t")
                    .setFirstResult(inicio)
                    .setMaxResults(fin)
                    .getResultList();

            Encriptacion e = new Encriptacion();

            tramites = e.desencriptarListaTramite(tramites);

            em.close();
            return tramites;
        } catch (Exception e) {
            if (em != null) {
                em.close();
            }
            return null;
        }
    }

    @Override
    public List<Tramite> listaTramitePersina(Persona persona, int inicio, int limit) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            List<Tramite> tramites = em.createQuery("SELECT t FROM Tramite t WHERE t.persona = :persona")
                    .setParameter("persona", persona)
                    .setFirstResult(inicio)
                    .setMaxResults(limit)
                    .getResultList();

            Encriptacion e = new Encriptacion();
            tramites = e.desencriptarListaTramite(tramites);

            em.close();
            return tramites;
        } catch (Exception e) {
            if (em != null) {
                em.close();
            }
            return null;
        }
    }

}
