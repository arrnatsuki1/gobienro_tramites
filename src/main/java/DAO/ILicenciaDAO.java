package DAO;

import Entidades.Licencia;
import javax.persistence.EntityManager;

/**
 *
 * @author Alan Rodriguez
 */
public interface ILicenciaDAO {
    public EntityManager getEntityManager();
    public Licencia agregarLicencia(Licencia licencia);
}
