package DAO;

import Entidades.Automovil;
import Entidades.Persona;
import Entidades.Placa;
import Entidades.Vehiculo;
import java.util.List;

/**
 * Esta interfaz define los métodos necesarios para realizar operaciones de 
 * la clase PlacaDAO en la Base de Datos
 * @author Rosa Rodriguez y Jose Trista
 */
public interface IPlacaDAO {
    /**
     * Genera una nueva placa y la agrega a la base de datos.
     * @param placa La placa a generar y agregar.
     * @return La placa generada y agregada a la base de datos.
     */
    public Placa generarPlaca(Placa placa);
    
    /**
     * Obtiene una placa específica de la base de datos.
     * @param placa La placa a obtener.
     * @return La placa obtenida, o null si no se encontró ninguna placa con ese
     * número de placa.
     */
    public Placa obtenerPlaca(Placa placa);
    
    /**
     * Obtiene todas las placas asociadas a un vehículo específico.
     * @param vehiculo El vehículo del cual se quieren obtener las placas.
     * @return Una lista de todas las placas asociadas al vehículo dado.
     */
    public List<Placa> obtenerPlacasVehiculo(Vehiculo vehiculo);
    
    /**
     * Actualiza los datos de una placa en la base de datos.
     * @param placa La placa a actualizar.
     * @return La placa actualizada.
     */
    public Placa actualizar(Placa placa);
}
