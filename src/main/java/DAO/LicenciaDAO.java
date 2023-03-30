package DAO;

import Entidades.Licencia;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Rosa Rodriguez
 */
public class LicenciaDAO implements ILicenciaDAO{

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("conexion");

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    @Override
    public Licencia agregarLicencia(Licencia licencia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            
            em.getTransaction().begin();
                em.persist(licencia);
            em.getTransaction().commit();
            
            
        } catch (Exception e) {
            if(em!=null){
                em.close();
            }
            
            licencia = null;
            
        } finally {
            if(em != null) {
                em.close();
            }
            
            return licencia;
        }
        
    }

    
    
}
