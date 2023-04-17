package DAO;

import Entidades.Automovil;
import Entidades.Persona;
import Entidades.Placa;
import Entidades.Vehiculo;
import java.util.List;

/**
 *
 * @author Rosa Rodriguez y Jose Trista
 */
public interface IPlacaDAO {
    //Sujeto a cambio en lo que veo donde genero la placa
    //si en la base de datos o en el sistema
    public Placa generarPlaca(Placa placa);
    public Placa obtenerPlaca(Placa placa);
    public List<Placa> obtenerPlacasVehiculo(Vehiculo vehiculo);
    public Placa actualizar(Placa placa);
}
