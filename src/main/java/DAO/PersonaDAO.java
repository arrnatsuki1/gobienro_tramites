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

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("conexion");

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Persona agregarPersona(Persona p) {
        EntityManager em = null;
        try {
            em = getEntityManager();

            em.getTransaction().begin();
                em.persist(p);
            em.getTransaction().commit();
            //Fin de la transaccion
            em.close();
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
        try {
            em = getEntityManager();
            TypedQuery<Persona> query = em
                    .createQuery("SELECT p FROM Persona p WHERE p.RFC = :rfc",
                            Persona.class);
            query.setParameter("rfc", rfc);
            Persona p = query.getSingleResult();
            em.close();
            return p;
        } catch (Exception e) {
            e.printStackTrace();
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
