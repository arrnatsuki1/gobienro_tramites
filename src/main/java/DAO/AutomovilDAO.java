package DAO;

import Entidades.Automovil;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase Automovil DAO que implementa los metodos de la interfaz 
 * IAutomovilDAO. 
 * @author Rosa Rodriguez y Jose Trista
 */
public class AutomovilDAO implements IAutomovilDAO {
    /**
     * Atributo para conexion con la Base de Datos
     */
    private EntityManagerFactory emf;

    /**
     * Constructor de la clase creando la conexion con la BD
     */
    public AutomovilDAO() {
        emf = Persistence.createEntityManagerFactory("conexion");
    }

    /**
     * Metodo que obtiene el EntityManager
     * @return EntityManager
     */
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Metodo para insertar un automovil a la Base de Datos
     * @param auto Automovil que se agregara
     * @return El auto que se agregara o null dependiendo del caso
     */
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
    
    /**
     * Metodo para actualizar un Automovil en la Base de Datos
     * @param auto Automovil a Actualizar
     * @return El automovil o null dependiendo del caso
     */
    @Override
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
    }
    
    /**
     * Metodo utilizado para obtener el Automovil dependiendo el numero de 
     * serie
     * @param auto Automovil a Buscar
     * @return El automovil de la consulta o null dependiendo del caso
     */
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
