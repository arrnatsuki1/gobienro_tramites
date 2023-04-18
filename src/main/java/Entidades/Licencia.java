package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * La clase Licencia  es una entidad en el contexto de un sistema que
 * maneja información sobre Tramites, y Hereda de Tramite
 * @author Rosa Rodriguez y Jose Trista
 */
@Entity
@Table(name = "licencias")
public class Licencia extends Tramite implements Serializable {
     /**
     * La fecha de vigencia de la Licencia.
     */
    @Column(name = "vigencia")
    @Temporal(TemporalType.DATE)
    private Calendar vigencia;
    
    /**
     * El estado actual de la Licencia.
     */
    @Basic
    @Column(name="estado")
    private String estado;
    
    /**
     * Constructor predeterminado que crea una nueva instancia de Licencia.
     */
    public Licencia() {
        super();
    }

    /**
     * Constructor que crea una nueva instancia de Licencia con los valores
     * especificados.
     *
     * @param vigencia La fecha de vigencia de la Licencia.
     * @param id El identificador único de la Licencia.
     * @param fechaEmision La fecha de emisión de la Licencia.
     * @param costo El costo de la Licencia.
     * @param persona La persona a la que se otorgó la Licencia.
     * @param estado El estado actual de la Licencia.
     */
    public Licencia(Calendar vigencia, Integer id, Calendar fechaEmision,
            BigDecimal costo, Persona persona, String estado) {
        super(id, fechaEmision, costo, persona);
        this.vigencia = vigencia;
        this.estado = estado;
    }

    /**
     * Devuelve la fecha de vigencia de la Licencia.
     *
     * @return La fecha de vigencia de la Licencia.
     */
    public Calendar getVigencia() {
        return vigencia;
    }

    /**
     * Establece la fecha de vigencia de la Licencia.
     *
     * @param vigencia La fecha de vigencia de la Licencia.
     */
    public void setVigencia(Calendar vigencia) {
        this.vigencia = vigencia;
    }

    /**
     * Devuelve el estado actual de la Licencia.
     *
     * @return El estado actual de la Licencia.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual de la Licencia.
     *
     * @param estado El estado actual de la Licencia.
     */

    public void setEstado(String estado){
        this.estado = estado;
    }
    
}
