package DAO;

import Entidades.Persona;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Rosa Rodriguez
 */
public interface IPersonaDAO {
    public EntityManager getEntityManager();
    public Persona agregarPersona(Persona p);
    public Persona consultarId(int id);
    public Persona consultarObj(Persona p);
    public List<Persona> consultarTodos();
}
