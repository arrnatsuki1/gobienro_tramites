package DAO;

import Entidades.Licencia;
import Entidades.Persona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rosa Rodriguez
 */
public class LicenciaDAO implements ILicenciaDAO {

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
            licencia = null;

        } finally {
            if (em != null) {
                em.close();
            }

            return licencia;
        }

    }

    @Override
    public List<Licencia> listaLicenciasVigentes(Persona persona) {
        EntityManager em = null;
        List<Licencia> listalicencia = null;
        try {
            em = getEntityManager();

            em.getTransaction().begin();
            TypedQuery<Licencia> query = em.createQuery(""
                    + "Select L FROM Licencia L WHERE L.persona.id = :id AND "
                    + "L.vigencia > CURRENT_DATE", Licencia.class);
            query.setParameter("id", persona.getId());
            listalicencia = query.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.close();
            }

            return null;

        } finally {
            if (em != null) {
                em.close();
            }
            return listalicencia;
        }
    }

    @Override
    public void refrescar(Licencia licencia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.refresh(licencia);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void actualizar(Licencia licencia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            
            em.getTransaction().begin();
                em.merge(licencia);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
