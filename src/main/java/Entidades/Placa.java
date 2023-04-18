/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * La clase Placa  es una entidad en el contexto de un sistema que
 * maneja información sobre Tramites, y Hereda de Tramite
 * @author Rosa Rodriguez y Jose Trista
 */
@Entity
@Table(name="placas")
public class Placa extends Tramite implements Serializable {
    /**
     *
     * Código único de la placa.
     */
    @Basic
    @Column(name="codigo")
    private String codigo;
    /**
     *
     * Estado de la placa, indica si está activa o no.
     */
    @Basic
    @Column(name = "activa")
    private String activa;
    /**
     *
     * Fecha en que se recibió el trámite de placa.
     */
    @Temporal(TemporalType.DATE)
    private Calendar fechaRecepcion;

    /**
     *
     * Automóvil asociado a la placa.
     */
    @ManyToOne()
    @JoinColumn(name = "id_auto")
    private Automovil auto;

    /**
     *
     * Constructor vacío para la clase Placa. Este constructor crea un nuevo
     * objeto Placa con los valores predeterminados para sus atributos.
     */
    public Placa() {
        super();
    }

    /**
     *
     * Constructor de la clase Placa. Este constructor crea un nuevo objeto
     * Placa con los valores especificados para sus atributos.
     *
     * @param codigo el código único de la placa
     * @param fechaRecepcion la fecha en que se recibió el trámite de placa
     * @param id el identificador único del trámite
     * @param fechaEmision la fecha en que se emitió el trámite
     * @param costo el costo del trámite
     * @param auto el automóvil asociado a la placa
     * @param persona la persona que solicitó el trámite
     */
    public Placa(String codigo, Calendar fechaRecepcion, Integer id,
            Calendar fechaEmision, BigDecimal costo,
            Automovil auto, Persona persona) {
        super(id, fechaEmision, costo, persona);
        this.codigo = codigo;
        this.fechaRecepcion = fechaRecepcion;
        this.auto = auto;
    }

    /**
     *
     * Obtiene el código único de la placa.
     *
     * @return el código único de la placa
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     *
     * Establece el código único de la placa.
     *
     * @param codigo el nuevo código único de la placa
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     *
     * Obtiene el estado de la placa.
     *
     * @return el estado de la placa, indicando si está activa o no
     */
    public String getActiva() {
        return activa;
    }

    /**
     *
     * Establece el estado de la placa.
     *
     * @param activa el nuevo estado de la placa, indicando si está activa o no
     */
    public void setActiva(String activa) {
        this.activa = activa;
    }

    /**
     *
     * Obtiene la fecha en que se recibió el trámite de placa.
     *
     * @return la fecha en que se recibió el trámite de placa
     */
    public Calendar getFechaRecepcion() {
        return fechaRecepcion;
    }

    /**
     * Establece la fecha de recepción de la placa.
     *
     * @param fechaRecepcion La fecha de recepción de la placa.
     */
    public void setFechaRecepcion(Calendar fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    /**
     * Devuelve el automóvil asociado a la placa.
     *
     * @return El automóvil asociado a la placa.
     */
    public Automovil getAuto() {
        return auto;
    }

    /**
     * Establece el automóvil asociado a la placa.
     *
     * @param auto El automóvil asociado a la placa.
     */
    public void setAuto(Automovil auto) {
        this.auto = auto;
    }

}
