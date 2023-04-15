package DAO;

import Entidades.Automovil;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Rosa Rodriguez
 */
public class AutomovilDAO implements IAutomovilDAO {

    private EntityManagerFactory emf;

    public AutomovilDAO() {
        emf = Persistence.createEntityManagerFactory("conexion");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Automovil insertarAutomovil(Automovil auto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
                em.persist(auto);
            em.getTransaction().commit();
            em.close();
            return auto;
        } catch (Exception e) {
            if (em != null) {
                em.close();
            }
            return null;
        }
    }

    public Automovil actualizarAutomovil(Automovil auto){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
                em.merge(auto);
            em.getTransaction().commit();
            em.close();
            return auto;
        } catch (Exception e) {
            if (em != null) {
                em.close();
            }
            return null;
        }
    };
    
    @Override
    public Automovil obtenerAutomovil(Automovil auto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            
            auto = (Automovil)em.createQuery
                    ("SELECT a FROM Automovil a WHERE a.nserie = :nserie")
                    .setParameter("nserie", auto.getNserie())
                    .getSingleResult();
            
            em.close();
            return auto;
        } catch (Exception e) {
            if (em != null) {
                em.close();
            }
            return null;
        }
    }

}
