package Utilidades;

import Entidades.Tramite;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Rosa Rodriguez
 */
public class Consultas {
    
    private final EntityManagerFactory emf;
    
    public Consultas() {
        emf = Persistence.createEntityManagerFactory("conexion");
    }
    
    private EntityManager obtenerEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<Tramite> porFecha(Calendar fecha){
        EntityManager em = null;
        try {
            em = obtenerEntityManager();
            
            List<Tramite> tramites = em.createQuery("SELECT t FROM Tramite t WHERE t.fechaEmision = :fecha", Tramite.class)
                    .setParameter("fecha", fecha)
                    .getResultList();
            
            em.close();
            return tramites;
        } catch (Exception e) {
            if(em!=null){
                em.close();
            }
        }
        return null;
    }
    
}
