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
