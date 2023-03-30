package package_call;

import Entidades.Automovil;
import Entidades.Licencia;
import Entidades.Persona;
import Entidades.Placa;
import Entidades.Tramite;
import Entidades.Vehiculo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jctri
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Principal pl = new Principal(false);
//        pl.setVisible(true);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("conexion");
        EntityManager em = emf.createEntityManager();
        
        List<Tramite> tramitesPersona = new ArrayList();
        List<Vehiculo> vehiculos = new ArrayList();
        List<Placa> tramites = new ArrayList();
        
        
        Licencia l = new Licencia(new Date(), 1, new Date(), new BigDecimal("123"), null);
        tramitesPersona.add(l);
        
        
        Placa t = new Placa("CPAPU", new Date(), 1, new Date(), new BigDecimal("123"), null, null);
        tramites.add(t);
        Persona p = new Persona(1, "6442099375", "ROVA030825MYA", (byte)0, "RSOA", "RODRIGUEZ", "VALENZUELA", vehiculos,tramitesPersona);
        
        Automovil auto = new Automovil("Rojo", "Buggate", "Beiron", "123123", "el bonito", tramites, 1, p);
        
        t.setAuto(auto);
        l.setPersona(p);
        
        
        vehiculos.add(auto);
        p.setVehiculos(vehiculos);
        
        
        //Tramite t = new Tramite(1, new Date(), new BigDecimal("123"));
        //Placa p = new Placa("sexooo", new Date(), 1, new Date(), new BigDecimal("123"));
        //Licencia l = new Licencia(new Date(), 1, new Date(), new BigDecimal("123"));
        
        
        em.getTransaction().begin();
            em.persist(p);
        em.getTransaction().commit();
        
        List<Persona> personas = em.createQuery("SELECT p FROM Persona p").getResultList();
        
        for(Persona pe : personas) {
            for(Vehiculo v : pe.getVehiculos()) {
                Automovil autom = (Automovil) v;
                System.out.println(autom.getColor());
            }
        }
        
        em.close();
    }
    
}
