package Entidades;

import DAO.Estados;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Rosa
 */
@Entity
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer id;
    @Basic
    @Column(name = "telefono")
    private String telefono;
    @Basic
    @Column(name = "RFC", unique = true)
    private String RFC;
    @Basic
    @Column(name = "discapacitado")
    private byte discapacitado;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "primer_pellido")
    private String primerApellido;
    @Basic
    @Column(name = "segundo_pellido")
    private String segundoApellido;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_Nacimiento")
    private Calendar fechaNacimiento;

    @OneToMany(cascade
            = {CascadeType.REMOVE,
                CascadeType.PERSIST,
                CascadeType.MERGE
            }, fetch = FetchType.LAZY)
    private List<Vehiculo> vehiculos;

    @OneToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE,
        CascadeType.REFRESH
    }, fetch = FetchType.LAZY, mappedBy = "persona")
    private List<Tramite> tramites;

    public Persona() {
    }

    public Persona(String telefono, String RFC, byte discapacitado,
            String nombre, String primerApellido, String segundoApellido,
            Calendar fechaNacimiento)
    {

        this.telefono = telefono;
        this.RFC = RFC;
        this.discapacitado = discapacitado;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public byte getDiscapacitado() {
        return discapacitado;
    }

    public void setDiscapacitado(byte discapacitado) {
        this.discapacitado = discapacitado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public List<Tramite> getTramites() {
        return tramites;
    }

    public void setTramites(List<Tramite> tramites) {
        this.tramites = tramites;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean tieneLicenciaActiva() {
        for(Tramite t : getTramites()) {
            if(t.getClass() == Licencia.class) {
                Licencia lic = (Licencia) t;
                if(lic.getEstado().equals(Estados.LICENCIA_VIGENTE)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Licencia obtenerLicenciaVigente() {
        for(Tramite t : getTramites()) {
            if(t.getClass() == Licencia.class) {
                Licencia lic = (Licencia) t;
                if(lic.getEstado().equals(Estados.LICENCIA_VIGENTE) ) {
                    return lic;
                }
            }
        }
        return null;
    }
    
}
