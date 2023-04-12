package package_call;

import DAO.IPersonaDAO;
import DAO.ITramiteDAO;
import DAO.PersonaDAO;
import DAO.TramiteDAO;
import Entidades.Persona;
import Entidades.Tramite;
import Frames.Principal;
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
        Principal pl = new Principal(false, null);
        pl.setVisible(true);
//        IPersonaDAO dao = new PersonaDAO();
//        Persona p = new Persona();
//        p.setNombre("ROSA");
//        p.setPrimerApellido("RODRIGUEZ");
//        p.setSegundoApellido("VALENZUELA");
//        List<Persona> personas = dao.buscarPorNombre(p);
////        
//        for(Persona persona : personas) {
//            System.out.println(persona.getRFC());
//        }

//        ITramiteDAO dao = new TramiteDAO();
//        Persona p = new Persona();
//        p.setNombre("JOSE");
//        p.setPrimerApellido("GUADALUPE");
//        p.setSegundoApellido("RODRIGUEZ");
//        List<Tramite> tramites = dao.listaTramiteNombre(p, 0, 100);
//        System.out.println(tramites.get(0).getCosto());
        
    }
    
}
