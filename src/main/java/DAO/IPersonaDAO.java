package DAO;

import Entidades.Persona;
import Excepciones.RFCExistenteException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Rosa Rodriguez
 */
public interface IPersonaDAO {
    public EntityManager getEntityManager();
    public Persona agregarPersona(Persona p) throws RFCExistenteException;
    public Persona consultarRFC(String rfc);
    public Persona consultarObj(Persona p);
    public List<Persona> consultarTodos();
    public void refrescar(Persona p);
    public List<Persona> buscarPorNombre(Persona persona);
    public List<Persona> buscarPorNacimiento(Calendar date);
    public List<Persona> buscarPorNombreNacimiento(Persona persona);
    public List<Persona> consultarRFClista(String rfc);
    public List<Persona> agregar20Personas() throws RFCExistenteException;
}
