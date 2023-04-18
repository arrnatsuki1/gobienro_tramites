/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Licencia;
import Entidades.Persona;
import Entidades.Placa;
import Entidades.Tramite;
import Utilidades.Encriptacion;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * Esta clase implementa la interfaz ITramiteDAO y proporciona la lógica para
 * interactuar con la base de datos para las operaciones relacionadas con la
 * entidad Tramite.
 * @author Rosa Rodriguez y Jose Trista
 */
public class TramiteDAO implements ITramiteDAO {
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
     * Metodo que obtiene los tramites que esten en la BD
     * @param inicio El índice del primer elemento a obtener.
     * @param fin El número máximo de elementos a obtener.
     * @return Una lista de trámites que cumplan con los criterios de búsqueda
     * especificados.
     */
    @Override
    public List<Tramite> listaTramite(int inicio, int fin) {
        EntityManager em = null;
        try {
            em = getEntityManager();
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

    /**
     * Obtiene una lista de trámites de la base de datos relacionados con una
     * persona específica
     * @param persona La persona relacionada con los trámites que se quieren
     * obtener.
     * @param inicio El índice del primer elemento a obtener.
     * @param limit El número máximo de elementos a obtener.
     * @return Una lista de trámites relacionados con la persona especificada
     */
    @Override
    public List<Tramite> listaTramitePersona(Persona persona, int inicio, int limit) {
        EntityManager em = null;
        Encriptacion encripta = new Encriptacion();
        encripta.encriptar(persona.getNombre());
        encripta.encriptar(persona.getPrimerApellido());
        encripta.encriptar(persona.getSegundoApellido());
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

    /**
     * Obtiene una lista de trámites de la base de datos realizados por una
     * persona específica con una fecha de nacimiento
     * @param p La persona de la cual se quieren obtener los trámites.
     * @param fecha La fecha de nacimiento de la persona.
     * @param inicio El índice del primer elemento a obtener.
     * @param limit El número máximo de elementos a obtener.
     * @return Una lista de trámites realizados por la persona especificada en
     * la fecha de nacimiento especificada.
     */
    @Override
    public List<Tramite> listaTramitePersonaNacimiento(Persona p, Calendar fecha, int inicio, int limit) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            List<Tramite> tramites = em.createQuery("SELECT t FROM Tramite t WHERE t.persona = :persona AND t.fechaEmision = :fecha")
                    .setParameter("persona", p)
                    .setParameter("fecha", fecha)
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

    /**
     * Obtiene una lista de trámites de la base de datos realizados en una fecha
     * específica.
     * @param fecha La fecha en la cual se realizaron los trámites.
     * @param inicio El índice del primer elemento a obtener.
     * @param limit El número máximo de elementos a obtener.
     * @return Una lista de trámites realizados en la fecha especificada 
     */
    @Override
    public List<Tramite> listaTramiteFechaTodos(Calendar fecha, int inicio, int limit) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            List<Tramite> tramites = em.createQuery("SELECT t FROM Tramite t WHERE t.fechaEmision = :fecha")
                    .setParameter("fecha", fecha)
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
    
    /**
     * Este método recupera una lista de trámites realizados por una persona
     * dentro de un periodo de tiempo determinado.
     * @param p representa la persona a la que se le buscarán los trámites.
     * @param f1 representa la fecha inicial del periodo de tiempo.
     * @param f2 representa la fecha final del periodo de tiempo.
     * @param inicio índice del primer resultado que se recuperará de la lista.
     * @param limit número máximo de resultados que se recuperarán.
     * @return los trámites realizados por la persona en el periodo de tiempo
     * especificado.
     */
    @Override
    public List<Tramite> listaPeriodoPersona(Persona p, Calendar f1, Calendar f2, int inicio, int limit) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            List<Tramite> tramites = em.createQuery("SELECT t FROM Tramite t WHERE t.persona = :persona AND t.fechaEmision BETWEEN :fecha1 AND :fecha2")
                    .setParameter("fecha1", f1)
                    .setParameter("fecha2", f2)
                    .setParameter("persona", p)
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

    /**
     * Este método recupera una lista de todos los trámites realizados en 
     * un periodo de tiempo determinado.
     * @param f1 representa la fecha inicial del periodo de tiempo.
     * @param f2 representa la fecha final del periodo de tiempo.
     * @param inicio indice del primer resultado que se recuperará de la lista.
     * @param limit número máximo de resultados que se recuperarán.
     * @return todos los trámites realizados en el periodo de tiempo especificado.
     */
    @Override
    public List<Tramite> listaPeriodoTodos(Calendar f1, Calendar f2, int inicio, int limit) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            List<Tramite> tramites = em.createQuery("SELECT t FROM Tramite t WHERE t.fechaEmision BETWEEN :fecha1 AND :fecha2")
                    .setParameter("fecha1", f1)
                    .setParameter("fecha2", f2)
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

    /**
     * Este método recupera una lista de trámites realizados por una persona 
     * de acuerdo a un tipo de trámite específico.
     * @param p representa la persona a la que se le buscarán los trámites.
     * @param respuesta contendrá la respuesta que se enviará al usuario.
     * @param inicio índice del primer resultado que se recuperará de la lista.
     * @param limite número máximo de resultados que se recuperarán.
     * @return los trámites realizados por la persona de acuerdo al tipo de
     * trámite especificado.
     */
    @Override
    public List<Tramite> listaPorTipoPersona(Persona p, StringBuffer respuesta, int inicio, int limite) {
        EntityManager em = null;
        try {

            String query = null;

            if (Integer.parseInt(respuesta.substring(0, 1)) == Estados.TIPO_LICENCIA) {
                query = "SELECT l FROM Licencia l WHERE l.persona = :persona";
            } else if (Integer.parseInt(respuesta.substring(0, 1)) == Estados.TIPO_PLACA) {
                query = "SELECT p FROM Placa p WHERE p.persona = :persona";
            }

            em = getEntityManager();
            List<Tramite> tramites = em.createQuery(query)
                    .setParameter("persona", p)
                    .setFirstResult(inicio)
                    .setMaxResults(limite)
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

    /**
     * Este método recupera una lista de todos los trámites realizados de acuerdo
     * a un tipo de trámite específico.
     * @param respuesta contendrá la respuesta que se enviará al usuario.
     * @param inicio índice del primer resultado que se recuperará de la lista.
     * @param limite número máximo de resultados que se recuperarán.
     * @return todos los trámites realizados de acuerdo al tipo de trámite 
     * especificado.
     */
    @Override
    public List<Tramite> listaPorTipoTodos(StringBuffer respuesta, int inicio, int limite) {
        EntityManager em = null;
        try {

            String query = null;

            if (Integer.parseInt(respuesta.substring(0, 1)) == Estados.TIPO_LICENCIA) {
                query = "SELECT l FROM Licencia l";
            } else if (Integer.parseInt(respuesta.substring(0, 1)) == Estados.TIPO_PLACA) {
                query = "SELECT p FROM Placa p";
            }

            em = getEntityManager();
            List<Tramite> tramites = em.createQuery(query)
                    .setFirstResult(inicio)
                    .setMaxResults(limite)
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

    /**
     * Obtiene una lista de trámites de la base de datos realizados por una
     * persona específica con el nombre de esa persona
     * @param p La persona de la cual se quieren obtener los trámites.
     * @param inicio El índice del primer elemento a obtener.
     * @param limit El número máximo de elementos a obtener.
     * @return Una lista de trámites realizados por la persona especificada 
     */
    @Override
    public List<Tramite> listaTramiteNombre(Persona p, int inicio, int limit) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Encriptacion encriptar = new Encriptacion();
            p = encriptar.encriptarNombrePersona(p);
            
            TypedQuery<Tramite> query = em
                    .createQuery("SELECT t FROM Tramite t WHERE "
                            + "t.persona.nombre LIKE :nombre AND t.persona.primerApellido LIKE :apellido1 "
                            + "AND t.persona.segundoApellido LIKE :apellido2", Tramite.class)
                    .setParameter("nombre", "%" + p.getNombre() + "%")
                    .setParameter("apellido1", "%" + p.getPrimerApellido() + "%")
                    .setParameter("apellido2", "%" + p.getSegundoApellido() + "%")
                    .setFirstResult(inicio)
                    .setMaxResults(limit);
            
            List<Tramite> tramites = query.getResultList();
            tramites = encriptar.desencriptarListaTramite(tramites);

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
