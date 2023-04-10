/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Utilidades.Encriptacion;
import Entidades.Persona;
import Utilidades.ChiperPersonas;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rosa Rodriguez
 */
public class PersonaDAO implements IPersonaDAO {

    private EntityManagerFactory emf;

    public PersonaDAO() {
        emf = Persistence.createEntityManagerFactory("conexion");
    }

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Persona agregarPersona(Persona p) {
        EntityManager em = null;
        String nombre = p.getNombre();
        String apellidoP = p.getPrimerApellido();
        String apellidoM = p.getSegundoApellido();
        Encriptacion encripta = new Encriptacion();
        String encriptado2 = encripta.encriptar(apellidoM);
        String encriptado3 = encripta.encriptar(apellidoP);
        String encriptado = encripta.encriptar(nombre);
        p.setNombre(encriptado);
        p.setPrimerApellido(encriptado3);
        p.setSegundoApellido(encriptado2);
        try {
            em = getEntityManager();

            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            //Fin de la transaccion
            em.close();
            p.setNombre(nombre);
            return p;
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
                em.close();
            }
            return null;
        }
    }

    @Override
    public Persona consultarRFC(String rfc) {
        EntityManager em = null;
        Encriptacion desencripta = new Encriptacion();
        try {
            em = getEntityManager();
            TypedQuery<Persona> query = em
                    .createQuery("SELECT p FROM Persona p WHERE p.RFC = :rfc",
                            Persona.class);
            query.setParameter("rfc", rfc);
            Persona p = query.getSingleResult();
            p.setNombre(desencripta.desencriptar(p.getNombre()));
            p.setPrimerApellido(desencripta.desencriptar(p.getPrimerApellido()));
            p.setSegundoApellido(desencripta.desencriptar(p.getSegundoApellido()));
            em.close();
            return p;
        } catch (Exception e) {

            if (em != null) {
                em.close();
            }
            return null;
        }

    }

    @Override
    public Persona consultarObj(Persona p) {
        return consultarRFC(p.getRFC());
    }

    @Override
    public List<Persona> consultarTodos() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            List<Persona> personas = em
                    .createQuery("SELECT p FROM Persona p")
                    .getResultList();
            em.close();
            return personas;
        } catch (Exception e) {
            if (em != null) {
                em.close();
            }
            return null;
        }
    }

    @Override
    public void refrescar(Persona p) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.refresh(p);
            em.close();
        } catch (Exception e) {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Persona> buscarPorNombre(Persona persona) {
        EntityManager em = null;
        Encriptacion desencripta = new Encriptacion();
        Encriptacion cipher = new Encriptacion();
        try {

            String n_encriptado = cipher.encriptar(persona.getNombre());
            String pp_encriptado = cipher.encriptar(persona.getPrimerApellido());
            String sp_encriptado = cipher.encriptar(persona.getSegundoApellido());

            em = getEntityManager();
            TypedQuery<Persona> query = em
                    .createQuery("SELECT p FROM Persona p WHERE "
                            + "p.nombre LIKE :nombre AND p.primerApellido LIKE :apellido1 "
                            + "AND p.segundoApellido LIKE :apellido2", Persona.class)
                    .setParameter("nombre", "%" + n_encriptado + "%")
                    .setParameter("apellido1", "%" + pp_encriptado + "%")
                    .setParameter("apellido2", "%" + sp_encriptado + "%");

            List<Persona> personas = query.getResultList();

            if (personas.isEmpty()) {
                return null;
            }

            for (Persona ipersona : personas) {
                ipersona.setNombre(desencripta.desencriptar(ipersona.getNombre()));
                ipersona.setPrimerApellido(desencripta.desencriptar(ipersona.getPrimerApellido()));
                ipersona.setSegundoApellido(desencripta.desencriptar(ipersona.getSegundoApellido()));
            }

            em.close();
            return personas;
        } catch (Exception e) {

            if (em != null) {
                em.close();
            }
            return null;
        }
    }

    @Override
    public List<Persona> buscarPorNacimiento(Calendar date) {
        EntityManager em = null;
        ChiperPersonas chiper = new ChiperPersonas();
        try {
            em = getEntityManager();
            TypedQuery<Persona> query = em
                    .createQuery("SELECT p FROM Persona p WHERE p.fechaNacimiento = :fecha",
                            Persona.class);
            query.setParameter("fecha", date);
            List<Persona> lista = query.getResultList();

            lista = chiper.desencriptarLista(lista);

            em.close();
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            if (em != null) {
                em.close();
            }
            return null;
        }
    }

    @Override
    public List<Persona> buscarPorNombreNacimiento(Persona persona) {
        EntityManager em = null;
        ChiperPersonas chiperPersona = new ChiperPersonas();
        Encriptacion cipher = new Encriptacion();
        try {

            String n_encriptado = cipher.encriptar(persona.getNombre());
            String pp_encriptado = cipher.encriptar(persona.getPrimerApellido());
            String sp_encriptado = cipher.encriptar(persona.getSegundoApellido());

            em = getEntityManager();
            TypedQuery<Persona> query = em
                    .createQuery("SELECT p FROM Persona p WHERE "
                            + "p.nombre LIKE :nombre AND p.primerApellido LIKE :apellido1 "
                            + "AND p.segundoApellido LIKE :apellido2 AND p.fechaNacimiento = :fecha", Persona.class)
                    .setParameter("nombre", "%" + n_encriptado + "%")
                    .setParameter("apellido1", "%" + pp_encriptado + "%")
                    .setParameter("apellido2", "%" + sp_encriptado + "%")
                    .setParameter("fecha", persona.getFechaNacimiento());

            List<Persona> personas = query.getResultList();
            
            if (personas.isEmpty()) {
                return null;
            }

            personas = chiperPersona.desencriptarLista(personas);
            
            em.close();
            return personas;
        } catch (Exception e) {

            if (em != null) {
                em.close();
            }
            return null;
        }
    }

}
