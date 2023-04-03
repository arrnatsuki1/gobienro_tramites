/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Automovil;
import Entidades.Persona;
import Entidades.Placa;
import Entidades.Vehiculo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alan Rodriguez
 */
public class PlacaDAO implements IPlacaDAO{

    private EntityManagerFactory emf;
    
    public PlacaDAO() {
        emf = Persistence.createEntityManagerFactory("conexion");
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    @Override
    public Placa generarPlaca(Placa placa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
                em.persist(placa);
            em.getTransaction().commit();
            em.close();
            return placa;
        } catch (Exception e) {
            if(em!=null){
                em.close();
            }
            return null;
        }
    }

    @Override
    public Placa obtenerPlaca(Placa placa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            placa = (Placa)em
                    .createQuery("SELECT p FROM Placa p WHERE p.codigo = :codigo")
                    .setParameter("codigo", placa.getCodigo())
                    .getSingleResult();
            em.close();
            return placa;
        } catch (Exception e) {
            if(em!=null){
                em.close();
            }
            return null;
        }
    }

    @Override
    public List<Placa> obtenerPlacasVehiculo(Vehiculo vehiculo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Automovil auto = (Automovil) vehiculo;
            String codigo = auto.getNserie();
            
            List<Placa> placas = em
                    .createQuery("SELECT p FROM Placa p WHERE p.codigo = :codigo")
                    .setParameter("codigo", codigo)
                    .getResultList();
            em.close();
            return placas;
        } catch (Exception e) {
            if(em!=null){
                em.close();
            }
            return null;
        }
    }

    @Override
    public Placa actualizar(Placa placa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
                em.merge(placa);
            em.getTransaction().commit();
            em.close();
            return placa;
        } catch (Exception e) {
            if(em!=null){
                em.close();
            }
            return null;
        }
    }
    
}
