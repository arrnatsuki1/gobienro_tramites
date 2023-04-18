/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Persona;
import Entidades.Tramite;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Esta interfaz define los métodos necesarios para realizar operaciones de 
 * la clase TramiteDAO en la Base de Datos
 * @author Rosa Rodriguez y Jose Trista
 */
public interface ITramiteDAO {
    /**
     * Obtiene el objeto EntityManager para interactuar con la base de datos.
     * @return El objeto EntityManager.
     */
    public EntityManager getEntityManager();
    
    /**
     * Obtiene una lista de trámites de la base de datos, desde la posición
     * "inicio" hasta "inicio+limit".
     * @param inicio El índice del primer elemento a obtener.
     * @param limit El número máximo de elementos a obtener.
     * @return Una lista de trámites que cumplan con los criterios de búsqueda
     * especificados.
     */
    public List<Tramite> listaTramite(int inicio, int limit);
    
    /**
     * Obtiene una lista de trámites de la base de datos realizados por una
     * persona específica con el nombre de esa persona
     * desde la posición "inicio" hasta "inicio+limit".
     * @param p La persona de la cual se quieren obtener los trámites.
     * @param inicio El índice del primer elemento a obtener.
     * @param limit El número máximo de elementos a obtener.
     * @return Una lista de trámites realizados por la persona especificada 
     */
    public List<Tramite> listaTramiteNombre(Persona p, int inicio, int limit);
    
    /**
     * Obtiene una lista de trámites de la base de datos relacionados con una
     * persona específica, desde la posición "inicio" hasta "inicio+limit".
     * @param persona La persona relacionada con los trámites que se quieren
     * obtener.
     * @param inicio El índice del primer elemento a obtener.
     * @param limit El número máximo de elementos a obtener.
     * @return Una lista de trámites relacionados con la persona especificada
     */
    public List<Tramite> listaTramitePersona(Persona persona, int inicio, int limit);
    
    /**
     * Obtiene una lista de trámites de la base de datos realizados por una
     * persona específica con una fecha de nacimiento desde la
     * posición "inicio" hasta "inicio+limit".
     * @param p La persona de la cual se quieren obtener los trámites.
     * @param fecha La fecha de nacimiento de la persona.
     * @param inicio El índice del primer elemento a obtener.
     * @param limit El número máximo de elementos a obtener.
     * @return Una lista de trámites realizados por la persona especificada en
     * la fecha de nacimiento especificada.
     */
    public List<Tramite> listaTramitePersonaNacimiento(Persona p, Calendar fecha, int inicio, int limit);
    
    /**
     * Obtiene una lista de trámites de la base de datos realizados en una fecha
     * específica, desde la posición "inicio" hasta "inicio+limit".
     * @param fecha La fecha en la cual se realizaron los trámites.
     * @param inicio El índice del primer elemento a obtener.
     * @param limit El número máximo de elementos a obtener.
     * @return Una lista de trámites realizados en la fecha especificada 
     */
    public List<Tramite> listaTramiteFechaTodos(Calendar fecha, int inicio, int limit);
    
    /**
     * Este método recupera una lista de trámites realizados por una persona 
     * dentro de un periodo de tiempo determinado.
     * @param p  representa la persona a la que se le buscarán los trámites.
     * @param f1 representa la fecha inicial del periodo de tiempo.
     * @param f2 representa la fecha final del periodo de tiempo.
     * @param inicio índice del primer resultado que se recuperará de la lista.
     * @param limit número máximo de resultados que se recuperarán.
     * @return los trámites realizados por la persona en el periodo de 
     * tiempo especificado.
     */
    public List<Tramite> listaPeriodoPersona(Persona p, Calendar f1, Calendar f2, int inicio, int limit);
    
    /**
     * Este método recupera una lista de todos los trámites realizados en 
     * un periodo de tiempo determinado.
     * @param f1 representa la fecha inicial del periodo de tiempo.
     * @param f2 representa la fecha final del periodo de tiempo.
     * @param inicio indice del primer resultado que se recuperará de la lista.
     * @param limit número máximo de resultados que se recuperarán.
     * @return todos los trámites realizados en el periodo de tiempo especificado.
     */
    public List<Tramite> listaPeriodoTodos(Calendar f1, Calendar f2, int inicio, int limit);
    
    /**
     * Este método recupera una lista de trámites realizados por una persona 
     * de acuerdo a un tipo de trámite específico.
     * @param p representa la persona a la que se le buscarán los trámites.
     * @param respuesta contendrá la respuesta que se enviará al usuario.
     * @param inicio índice del primer resultado que se recuperará de la lista.
     * @param limite número máximo de resultados que se recuperarán.
     * @return los trámites realizados por la persona de acuerdo al tipo de
     * trámite especificado.
     */
    public List<Tramite> listaPorTipoPersona(Persona p, StringBuffer respuesta, int inicio, int limite);
    
    /**
     * Este método recupera una lista de todos los trámites realizados de acuerdo
     * a un tipo de trámite específico.
     * @param respuesta contendrá la respuesta que se enviará al usuario.
     * @param inicio índice del primer resultado que se recuperará de la lista.
     * @param limite número máximo de resultados que se recuperarán.
     * @return todos los trámites realizados de acuerdo al tipo de trámite 
     * especificado.
     */
    public List<Tramite> listaPorTipoTodos(StringBuffer respuesta, int inicio, int limite);
}
