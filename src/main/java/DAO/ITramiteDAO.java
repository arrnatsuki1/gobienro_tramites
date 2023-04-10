/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Tramite;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jctri
 */
public interface ITramiteDAO {
    public EntityManager getEntityManager();
    public List<Tramite> listaTramitesNombre(String nombre);
}
