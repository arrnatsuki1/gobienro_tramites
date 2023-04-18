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
 * Esta clase implementa la interfaz IPlacaDAO y proporciona la lógica para
 * interactuar con la base de datos para las operaciones relacionadas con la
 * entidad Placa.
 * @author Rosa Rodriguez y Jose Trista
 */
public class PlacaDAO implements IPlacaDAO{
    /**
     * Se crea la conexion con el EntityManagerFactory con la BD
     */
    private EntityManagerFactory emf;
    
    /**
     * Constructor sin parametro que recibe la conexion 
     */
    public PlacaDAO() {
        emf = Persistence.createEntityManagerFactory("conexion");
    }
    
    /**
     * Devuelve un objeto EntityManager para interactuar con la base de datos.
     * @return el objeto EntityManager
     */
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    /**
     * Metodo que genera una placa en la BD
     * @param placa placa que se genera
     * @return la placa generada
     */
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

    /**
     * Obtiene la placa de la BD segun su numero de placa (codigo)
     * @param placa placa a obtener de la BD
     * @return placa obtenida de la consulta
     */
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

    /**
     * Metodo que obtiene las placas dependiendo del Vehiculo que se manda 
     * en el parametro
     * @param vehiculo vehiculo del que se obtendra las placas
     * @return placas obtenidas del vehiculo
     */
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

    /**
     * Metodo para actualizar la placa en la BD
     * @param placa placa a actualizar en la BD
     * @return placa actualizada 
     */
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
