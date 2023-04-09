package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
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

    private byte estado;
    
    public Licencia() {
        super();
    }

    public Licencia(Calendar vigencia, Integer id, Calendar fechaEmision,
            BigDecimal costo, Persona persona, byte estado) {
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

    public byte getEstado() {
        return estado;
    }

    public void setEstado(byte estado){
        this.estado = estado;
    }
    
}
