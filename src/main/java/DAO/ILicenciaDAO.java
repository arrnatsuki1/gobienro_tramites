package DAO;

import Entidades.Licencia;
import Entidades.Persona;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Rosa Rodriguez
 */
public interface ILicenciaDAO {
    public EntityManager getEntityManager();
    public Licencia agregarLicencia(Licencia licencia);
    public List <Licencia> listaLicenciasVigentes(Persona persona);
    public void refrescar(Licencia licencia);
    public void actualizar(Licencia licencia);
}
