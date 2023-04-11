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
 *
 * @author Rosa Rodriguez y Jose Trista
 */
@Entity
@Table(name = "licencias")
public class Licencia extends Tramite implements Serializable {
    @Column(name = "vigencia")
    @Temporal(TemporalType.DATE)
    private Calendar vigencia;
    @Basic
    @Column(name="estado")
    private String estado;
    
    public Licencia() {
        super();
    }

    public Licencia(Calendar vigencia, Integer id, Calendar fechaEmision,
            BigDecimal costo, Persona persona, String estado) {
        super(id, fechaEmision, costo, persona);
        this.vigencia = vigencia;
        this.estado = estado;
    }

    public Calendar getVigencia() {
        return vigencia;
    }

    public void setVigencia(Calendar vigencia) {
        this.vigencia = vigencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }
    
}
