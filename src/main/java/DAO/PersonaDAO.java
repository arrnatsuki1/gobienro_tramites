/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

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

            if(em!=null){
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
            if(em!=null){
                em.close();
            }
            return null;
        }
    }

}
