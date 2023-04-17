package DAO;

import Entidades.Licencia;
import Entidades.Persona;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Interfaz que contiene los metodos que se utilizaran en la clase 
 * LicenciaDAO
 * @author Rosa Rodriguez y Jose Trista
 */
public interface ILicenciaDAO {
    /**
     * Metodo que se utiliza para la conexion con
     * la Base de Datos
     * @return conexion con la BD
     */
    public EntityManager getEntityManager();
    
    /**
     * Metodo que se utiliza para agregar la Licencia en la
     * Base de Datos
     * @param licencia licencia que se agregara en la Base de Datos
     * @return la licencia que se agrego o null dependiendo del caso
     */
    public Licencia agregarLicencia(Licencia licencia);
    
    /**
     * Metodo que obtiene las licencias Vigentes dependiendo de la persona
     * @param persona Persona de la que se va a obtener la licencia vigente
     * @return La lista de licencias vigentes o null dependiendo del caso
     */
    public List <Licencia> listaLicenciasVigentes(Persona persona);
    
    /**
     * Metodo que se utiliza para refrescar una Licencia en la base de datos
     * @param licencia licencia a refrescar
     */
    public void refrescar(Licencia licencia);
    
    /**
     * Metodo que se utiliza para Actualizar una Licencia en 
     * La base de datos
     * @param licencia licencia que se va a actualizar en la BD
     */
    public void actualizar(Licencia licencia);
}
