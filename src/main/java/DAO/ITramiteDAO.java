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
 *
 * @author jctri
 */
public interface ITramiteDAO {
    public EntityManager getEntityManager();
    public List<Tramite> listaTramite(int inicio, int limit);
    public List<Tramite> listaTramitePersona(Persona persona, int inicio, int limit);
    public List<Tramite> listaTramitePersona(Persona p, Calendar fecha, int inicio, int limit);
    public List<Tramite> listaTramiteFechaTodos(Calendar fecha, int inicio, int limit);
}
