package package_call;

import DAO.IPersonaDAO;
import DAO.PersonaDAO;
import Entidades.Persona;
import Frames.Principal;
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
        Principal pl = new Principal(false, null);
        pl.setVisible(true);
//        IPersonaDAO dao = new PersonaDAO();
//        Persona p = new Persona(0, "6442099375", "ROVA030825MYA", (byte)0, "Rosa", "Rodriguez", "Valenzuela", null, null);
//        Persona p2 = new Persona(0, "6442099375", "ROVA030825MYA", (byte)0, "Eliot", "Chavez", "Rayos", null, null);
//        p = dao.agregarPersona(p);
//        p2 = dao.agregarPersona(p2);
//        
//        System.out.println(p2.getId());
        

    }
    
}
