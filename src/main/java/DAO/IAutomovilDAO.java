package DAO;

import Entidades.Automovil;

/**
 * Interfaz que contiene los metodos que se utilizaran en la clase 
 * AutomovilDAO
 * @author Rosa Rodriguez y Jose Trista
 */
public interface IAutomovilDAO {
    /**
     * Metodo que se utiliza para insertar un Automovil en la Base de 
     * Datos            
     * @param auto Automovil que se va a insertar en la Base de datos
     * @return El automovil que se inserto o null dependiendo del caso
     */
    public Automovil insertarAutomovil(Automovil auto);
    
    /**
     * Metodo que se utiliza para actualizar un Automovil en la Base
     * de Datos
     * @param auto Automovil que se va a actualizar en la Base de Datos
     * @return El automovil que se inserto o null dependiendo del caso
     */
    public Automovil actualizarAutomovil(Automovil auto);
    
    /**
     * Metodo que se utiliza para obtener un Automovil dependiendo de 
     * su numero de serie
     * @param auto Automovil que se obtiene de la consulta
     * @return el automovil o null depediendo del caso
     */
    public Automovil obtenerAutomovil(Automovil auto);
}
