/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Utilidades.Encriptacion;
import Entidades.Persona;
import Excepciones.RFCExistenteException;
//import Utilidades.ChiperPersonas;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rosa Rodriguez y Jose Trista
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
    public Persona agregarPersona(Persona p) throws RFCExistenteException{
        EntityManager em = null;

        if(consultarRFC(p.getRFC()) != null) {
            throw new RFCExistenteException("este rfc ya existe");
        }

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
            
            p.setNombre(encripta.desencriptar(p.getNombre()));
            p.setPrimerApellido(encripta.desencriptar(p.getPrimerApellido()));
            p.setSegundoApellido(encripta.desencriptar(p.getSegundoApellido()));
            
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
            TypedQuery<Persona> query = em.createQuery(
                    "SELECT p FROM Persona p WHERE p.RFC LIKE :rfc", Persona.class);
            query.setParameter("rfc", "%" + rfc + "%");
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
    public List<Persona> consultarRFClista(String rfc) {
        EntityManager em = null;
        Encriptacion desencripta = new Encriptacion();
        try {
            em = getEntityManager();
            TypedQuery<Persona> query = em.createQuery(
                    "SELECT p FROM Persona p WHERE p.RFC LIKE :rfc", Persona.class);
            query.setParameter("rfc", "%" + rfc + "%");
            List<Persona> p = query.getResultList();
            p = desencripta.desencriptarLista(p);
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
            Encriptacion desencripta = new Encriptacion();
            personas = desencripta.desencriptarLista(personas);
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
        Encriptacion chiper = new Encriptacion();
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

            personas = cipher.desencriptarLista(personas);

            em.close();
            return personas;
        } catch (Exception e) {

            if (em != null) {
                em.close();
            }
            return null;
        }
    }

    public List<Persona> agregar20Personas() throws RFCExistenteException{
        try {
            List<Persona> personas = new ArrayList();
            personas.add(new Persona("6441906030", "ABC123", Estados.PERSONA_DISCAPACITADA, "JOSE", "ROSAS", "PANDURO", new GregorianCalendar(2000, 8, 24)));
            personas.add(new Persona("6442076531", "CARS25", Estados.PERSONA_NO_DISCAPACITADA, "AZUL", "CHAVEZ", "GONZALES", new GregorianCalendar(1994, 5, 4)));
            personas.add(new Persona("6442099371", "VALK25", Estados.PERSONA_NO_DISCAPACITADA, "LUIS", "PEREZ", "MADURO", new GregorianCalendar(2001, 0, 8)));
            personas.add(new Persona("6442090175", "PEPE25", Estados.PERSONA_NO_DISCAPACITADA, "ROAS", "ROSAS", "TORRES", new GregorianCalendar(1998, 4, 25)));
            personas.add(new Persona("6442090210", "NOSE:d", Estados.PERSONA_NO_DISCAPACITADA, "JOSE", "VALLEZ", "TRISTA", new GregorianCalendar(1995, 3, 29)));
            personas.add(new Persona("6442090310", "ROPG91", Estados.PERSONA_NO_DISCAPACITADA, "LOURDES", "QUINTERO", "CAMPA", new GregorianCalendar(1995, 9, 10)));
            personas.add(new Persona("6442090520", "JGH1MY", Estados.PERSONA_NO_DISCAPACITADA, "GENARO", "GASTELUM", "ANN", new GregorianCalendar(2000, 8, 24)));
            personas.add(new Persona("6442090632", "BQYB13", Estados.PERSONA_DISCAPACITADA, "AXEL", "BARCO", "BOTE", new GregorianCalendar(1970, 0, 1)));
            personas.add(new Persona("6442090806", "P018B2", Estados.PERSONA_DISCAPACITADA, "OLIVER", "VALENZUELA", "LOPEZ", new GregorianCalendar(1979, 7, 21)));
            personas.add(new Persona("6442090861", "BY61CA", Estados.PERSONA_DISCAPACITADA, "IVETH", "LOPEZ", "VALENZUELA", new GregorianCalendar(1983, 4, 21)));
            personas.add(new Persona("6442090958", "BY691Z", Estados.PERSONA_NO_DISCAPACITADA, "DOLORES", "RODRIGUEZ", "VALENZUELA", new GregorianCalendar(1990, 9, 25)));
            personas.add(new Persona("6442091186", "VRT1RA", Estados.PERSONA_NO_DISCAPACITADA, "KARLA", "CRUZ", "ZILMAN", new GregorianCalendar(1986, 11, 25)));
            personas.add(new Persona("6442091286", "POI5AB", Estados.PERSONA_NO_DISCAPACITADA, "JOTARO", "JOESTAR", "JOESTAR", new GregorianCalendar(1987, 2, 17)));
            personas.add(new Persona("6442091438", "24AB87", Estados.PERSONA_NO_DISCAPACITADA, "JOSUKE", "JOESTAR", "JOESTAR", new GregorianCalendar(1994, 4, 6)));
            personas.add(new Persona("6442091559", "QWERTY", Estados.PERSONA_DISCAPACITADA, "JOLYNE", "KUJO", "JOESTAR", new GregorianCalendar(2003, 3, 20)));
            personas.add(new Persona("6442091778", "ZVQRTQ", Estados.PERSONA_DISCAPACITADA, "JONATHAN", "ENRIQUE", "LEON", new GregorianCalendar(1999, 7, 12)));
            personas.add(new Persona("6442091924", "AYUOPA", Estados.PERSONA_NO_DISCAPACITADA, "JONATHAN", "JOESTAR", "JOESTAR", new GregorianCalendar(1980, 10, 31)));
            personas.add(new Persona("6442095551", "JUKILO", Estados.PERSONA_DISCAPACITADA, "KAKYOIN", "NORIAKI", "VALENZUELA", new GregorianCalendar(2000, 9, 30)));
            personas.add(new Persona("6442099993", "MOTOMA", Estados.PERSONA_NO_DISCAPACITADA, "GIORNO", "BUCCARATI", "JOESTAR", new GregorianCalendar(1970, 5, 17)));
            personas.add(new Persona("6442098889", "ARCAGO", Estados.PERSONA_DISCAPACITADA, "JOSEPH", "PAREDES", "TORRES", new GregorianCalendar(2001, 11, 15)));

            for (Persona p : personas) {
                this.agregarPersona(p);
            }
            Encriptacion en = new Encriptacion();
            personas = en.desencriptarNombresPersonas(personas);

            return personas;
        }catch (RFCExistenteException rfce){
            throw new RFCExistenteException("Ya existen estas personas");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
