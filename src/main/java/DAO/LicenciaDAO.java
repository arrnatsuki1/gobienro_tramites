package DAO;

import Entidades.Licencia;
import Entidades.Persona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * Esta clase implementa la interfaz ILicenciaDAO y proporciona la lógica para
 * interactuar con la base de datos para las operaciones relacionadas con la
 * entidad Licencia.
 *
 * @author Rosa Rodriguez y Jose Trista
 */
public class LicenciaDAO implements ILicenciaDAO {
    /**
     * Se crea la conexion con el EntityManagerFactory con la BD
     */
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("conexion");

    /**
     * Devuelve un objeto EntityManager para interactuar con la base de datos.
     * @return el objeto EntityManager
     */
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Agrega una nueva Licencia a la base de datos.
     * @param licencia la Licencia que se agregará a la base de datos
     * @return la Licencia agregada, o null si se produjo una excepción
     */
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

    /**
     * Devuelve una lista de todas las Licencia vigentes para una
     * persona específica o null si se hizo una excepcion 
     * @param persona la persona para la que se buscan las licencias vigentes
     * @return una lista de todas las Licencias vigentes para la
     * persona especificada,
     */
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

    /**
     * Refresca la Licencia especificada en la base de datos.
     * @param licencia la Licencia que se actualizará en la base de
     * datos
     */
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

    /**
     * Actualiza la Licencia especificada en la base de datos.
     * @param licencia la Licencia que se actualizará en la base de
     * datos
     */
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
