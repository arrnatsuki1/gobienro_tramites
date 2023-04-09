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
 *
 * @author Alan Rodriguez
 */
@Entity
@Table(name="placas")
public class Placa extends Tramite implements Serializable {

    @Basic
    @Column(name="codigo")
    private String codigo;
    @Basic
    @Column(name="activa")
    private byte activa;
    @Temporal(TemporalType.DATE)
    private Calendar fechaRecepcion;

    @ManyToOne()
    @JoinColumn(name="id_auto")
    private Automovil auto;
    
    public Placa() {
        super();
    }

    public Placa(String codigo, Calendar fechaRecepcion, Integer id,
            Calendar fechaEmision, BigDecimal costo,
            Automovil auto, Persona persona) {
        super(id, fechaEmision, costo, persona);
        this.codigo = codigo;
        this.fechaRecepcion = fechaRecepcion;
        this.auto = auto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public byte getActiva() {
        return activa;
    }

    public void setActiva(byte activa) {
        this.activa = activa;
    }

    public Calendar getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Calendar fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Automovil getAuto() {
        return auto;
    }

    public void setAuto(Automovil auto) {
        this.auto = auto;
    }
    
    
    
}
