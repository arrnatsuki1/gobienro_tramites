package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase que representa un Tramite 
 * @author Rosa Rodriguez y Jose Trista
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Tramite implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Identificador único del trámite.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

     /**
     * Fecha en la que se emitió el trámite.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_emision")
    protected Calendar fechaEmision;
    
    /**
     * Costo del trámite.
     */
    @Basic
    @Column(name = "costo")
    private BigDecimal costo;

    /**
     * Persona que realizó el trámite.
     */
    @ManyToOne(cascade = {
     CascadeType.REFRESH
    })
    @JoinColumn(name="id_persona")
    protected Persona persona;
    
    /**
     * Constructor por defecto de la clase.
     */
    public Tramite() {
    }

    /**
     * Constructor de la clase con parámetros.
     * 
     * @param id Identificador único del trámite.
     * @param fechaEmision Fecha en la que se emitió el trámite.
     * @param costo Costo del trámite.
     * @param persona Persona que realizó el trámite.
     */
    public Tramite(Integer id, Calendar fechaEmision, BigDecimal costo,
            Persona persona) {
        this.id = id;
        this.fechaEmision = fechaEmision;
        this.costo = costo;
        this.persona = persona;
    }
    
    /**
     * Obtiene el identificador único del trámite.
     * 
     * @return Identificador único del trámite.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del trámite.
     * 
     * @param id Identificador único del trámite.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha en la que se emitió el trámite.
     * 
     * @return Fecha en la que se emitió el trámite.
     */
    public Calendar getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Establece la fecha en la que se emitió el trámite.
     * 
     * @param fechaEmision Fecha en la que se emitió el trámite.
     */
    public void setFechaEmision(Calendar fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * Obtiene el costo del trámite.
     * 
     * @return Costo del trámite.
     */
    public BigDecimal getCosto() {
        return costo;
    }

    /**
     * Establece el costo del trámite.
     * 
     * @param costo Costo del trámite.
     */
    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    /**
     * Obtiene la persona que realizó el trámite.
     * 
     * @return Persona que realizó el trámite.
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * Establece la persona que realiza el tramite
     * @param persona persona que realiza el tramite
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
