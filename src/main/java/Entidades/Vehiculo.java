/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase que representa un vehículo y su dueño en el sistema.
 * @author Rosa Rodriguez y Jose Trista
 */
@Entity
@Table(name = "vehiculos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 
     * Identificador único del vehículo 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_vehiculo")
    protected Integer id;

    /** 
     * Dueño del vehículo 
     */
    @ManyToOne()
    @JoinColumn(name = "id_dueno")
    protected Persona duenio;
    
    /**
     *
     * Constructor por defecto de la clase Vehiculo.
     */
    public Vehiculo() {
    }

    /**
     *
     * Constructor de la clase Vehiculo.
     *
     * @param id Identificador único del vehículo.
     * @param duenio Dueño del vehículo.
     */
    public Vehiculo(Integer id, Persona duenio) {
        this.id = id;
        this.duenio = duenio;
    }

    /**
     *
     * Método que devuelve el identificador único del vehículo.
     *
     * @return Identificador único del vehículo.
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * Método que establece el identificador único del vehículo.
     *
     * @param id Identificador único del vehículo.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * Método que devuelve el dueño del vehículo.
     *
     * @return Dueño del vehículo.
     */
    public Persona getDuenio() {
        return duenio;
    }

    /**
     *
     * Método que establece el dueño del vehículo.
     *
     * @param duenio Dueño del vehículo.
     */
    public void setDuenio(Persona duenio) {
        this.duenio = duenio;
    } 
}
