/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Tramite;
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
    public List<Tramite> listaTramite() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            List<Tramite> tramites = em.createQuery("SELECT t FROM Tramite t")
                    .getResultList();
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
