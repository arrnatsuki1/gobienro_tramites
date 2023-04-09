package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Alan Rodriguez y Jose Trista
 */
@Entity
@Table(name = "licencias")
public class Licencia extends Tramite implements Serializable {
    @Column(name = "vigencia")
    private Date vigencia;

    private byte estado;
    
    public Licencia() {
        super();
    }

    public Licencia(Date vigencia, Integer id, Date fechaEmision,
            BigDecimal costo, Persona persona, byte estado) {
        super(id, fechaEmision, costo, persona);
        this.vigencia = vigencia;
        this.estado = estado;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public byte getEstado() {
        return estado;
    }

    public void setEstado(byte estado){
        this.estado = estado;
    }
    
}
