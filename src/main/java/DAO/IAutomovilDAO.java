package DAO;

import Entidades.Automovil;

/**
 *
 * @author Rosa Rodriguez
 */
public interface IAutomovilDAO {
    public Automovil insertarAutomovil(Automovil auto);
    public Automovil actualizarAutomovil(Automovil auto);
    public Automovil obtenerAutomovil(Automovil auto);
}
