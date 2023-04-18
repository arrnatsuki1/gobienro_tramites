package DAO;

import Entidades.Persona;
import Excepciones.RFCExistenteException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Interfaz que contiene los metodos que se utilizaran en la clase 
 * PersonaDAO
 * @author Rosa Rodriguez y Jose Trista
 */
public interface IPersonaDAO {
    /**
     * Obtiene el EntityManager utilizado para realizar operaciones con la base
     * de datos.
     * @return El EntityManager.
     **/
    public EntityManager getEntityManager();
    
    /**
     * Agrega una nueva persona a la base de datos.
     * @param p La persona a agregar.
     * @return La persona agregada.
     * @throws RFCExistenteException si el RFC de la persona ya existe en la
     * base de datos.
     **/
    public Persona agregarPersona(Persona p) throws RFCExistenteException;
    
    /**
     * Consulta una persona en la base de datos por su RFC.
     * @param rfc El RFC de la persona a consultar.
     * @return La persona consultada, o null si no se encontró ninguna persona
     * con ese RFC.
     */
    public Persona consultarRFC(String rfc);
    
    /**
     * Consulta una persona en la base de datos por su objeto Persona.
     * @param p El objeto Persona a consultar.
     * @return La persona consultada, o null si no se encontró ninguna persona
     * con ese objeto Persona.
     */
    public Persona consultarObj(Persona p);
    
    /**
     * Consulta todas las personas almacenadas en la base de datos.
     * @return Una lista de todas las personas almacenadas en la base de datos.
     */
    public List<Persona> consultarTodos();
    
    /**
     * Actualiza los datos de una persona en la base de datos.
     * @param p La persona a actualizar.
     */
    public void refrescar(Persona p);
    
    /**
     * Busca personas en la base de datos que tengan el mismo nombre que la
     * persona dada.
     * @param persona La persona con el nombre a buscar.
     * @return Una lista de personas que tienen el mismo nombre que la persona
     * dada.
     */
    public List<Persona> buscarPorNombre(Persona persona);
    
    /**
     * Busca personas en la base de datos que tengan la misma fecha de
     * nacimiento que la dada.
     * @param date La fecha de nacimiento a buscar.
     * @return Una lista de personas que tienen la misma fecha de nacimiento que
     * la dada.
     */
    public List<Persona> buscarPorNacimiento(Calendar date);
    
    /**
     * Busca personas en la base de datos que tengan el mismo nombre y fecha de
     * nacimiento que la persona dada.
     * @param persona La persona con el nombre y fecha de nacimiento a buscar.
     * @return Una lista de personas que tienen el mismo nombre y fecha de
     * nacimiento que la persona dada.
     */
    public List<Persona> buscarPorNombreNacimiento(Persona persona);
    
    /**
     * Consulta todas las personas en la base de datos que tengan el mismo RFC
     * que el dado.
     * @param rfc El RFC a buscar.
     * @return Una lista de todas las personas en la base de datos que tengan el
     * mismo RFC que el dado.
     */
    public List<Persona> consultarRFClista(String rfc);
    
    /**
     * Agrega 20 nuevas personas a la base de datos. Las personas agregadas 
     * tienen nombres y fechas de nacimiento aleatorias.
     * @return La lista de  personas agregadas
     * @throws RFCExistenteException si las personas ya estan insertadas en la
     * BD
     */
    public List<Persona> agregar20Personas() throws RFCExistenteException;
}
