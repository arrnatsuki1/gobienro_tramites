package Entidades;

import DAO.Estados;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * La clase Automovil  es una entidad en el contexto de un sistema que
 * maneja información sobre vehículos, y Hereda de Vehiculo
 * @author Rosa Rodriguez y Jose Trista
 */
@Entity
@Table(name = "Automoviles")
@DiscriminatorValue("Automovil")
public class Automovil extends Vehiculo implements Serializable {
    /**
     * Atributo color que indica el color del Automovil 
     */
    @Basic
    @Column(name="color")
    private String color;
    
    /**
     * Atributo Marca que indica la Marca del Automovil
     */
    @Basic
    @Column(name="marca")
    private String marca;
    
    /**
     * Atributo Linea que indica la Linea del Automovil
     */
    @Basic
    @Column(name="linea")
    private String linea;
    
    /**
     * Atributo numero de serie que indica el numero de serie del Automovil
     */
    @Basic
    @Column(name="numero_serie")
    private String nserie;
    
    /**
     * Atributo modelo que indica el modelo del Automovil
     */
    @Basic
    @Column(name="modelo")
    private String modelo;

    /**
     * Lista de Placas que se obtiene de la relacion uno a muchos con la
     * entidad placa
     */
    @OneToMany(cascade = 
            {CascadeType.REMOVE,
                CascadeType.PERSIST,
                CascadeType.MERGE
            }, fetch = FetchType.LAZY)
    private List<Placa> placas;
    
    /**
     * Constructor Vacio de la Clase
     */
    public Automovil() {
        
    }

    /**
     * Metodo get que devuelve una lista de las placas del Automovil
     * @return placas del automovil
     */
    public List<Placa> getPlacas() {
        return placas;
    }

    /**
     * Metodo Set que manda la Lista de placas 
     * @param placas lista de placas
     */
    public void setPlacas(List<Placa> placas) {
        this.placas = placas;
    }

    /**
     * Metodo get que devuelve el color del automovil
     * @return el color del automovil
     */
    public String getColor() {
        return color;
    }

    /**
     * Metodo set que manda el color del automovil
     * @param color color del automovil
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Metodo get que devuelve la Marca del Automovil
     * @return marca del automovil
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Metodo set que establece la marca del Automovil
     * @param marca marca del automovil
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Metodo get que devuelve la linea del Automovil
     * @return linea del automovil
     */
    public String getLinea() {
        return linea;
    }

    /**
     * Establece la línea del Automovil.
     *
     * @param linea La línea del Automovil.
     */
    public void setLinea(String linea) {
        this.linea = linea;
    }

    /**
     * Devuelve el número de serie del Automovil.
     *
     * @return El número de serie del Automovil.
     */
    public String getNserie() {
        return nserie;
    }

    /**
     * Establece el número de serie del Automovil.
     *
     * @param nserie El número de serie del Automovil.
     */
    public void setNserie(String nserie) {
        this.nserie = nserie;
    }

    /**
     * Devuelve el modelo del Automovil.
     *
     * @return El modelo del Automovil.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Establece el modelo del Automovil.
     *
     * @param modelo El modelo del Automovil.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    /**
     * Verifica si el Automovil tiene una placa activa.
     *
     * @return true si el Automovil tiene una placa activa, false en caso
     * contrario.
     */
    public boolean tienePlacaActiva() {
        for(Placa placa : this.getPlacas()) {
            if(placa.getActiva().equalsIgnoreCase(Estados.PLACA_ACTIVA)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Obtiene la placa activa del Automovil.
     *
     * @return La placa activa del Automovil, o null si no tiene una placa
     * activa.
     */
    public Placa obtenerPlacaActiva() {
        for(Placa placa : this.getPlacas()) {
            if(placa.getActiva().equals(Estados.PLACA_ACTIVA)) {
                return placa;
            }
        }
        return null;
    }
    
}
