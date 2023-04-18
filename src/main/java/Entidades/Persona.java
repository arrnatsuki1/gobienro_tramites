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
 * La clase Persona  es una entidad en el contexto de un sistema que
 * maneja información sobre Personas
 * @author Rosa Rodriguez y Jose Trista
 */
@Entity
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Identificador único de la persona en el sistema.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer id;
    /**
     * Teléfono de la persona.
     */
    @Basic
    @Column(name = "telefono")
    private String telefono;
    /**
     * RFC de la persona. Debe ser único.
     */
    @Basic
    @Column(name = "RFC", unique = true)
    private String RFC;
    /**
     * Indica si la persona tiene alguna discapacidad.
     */
    @Basic
    @Column(name = "discapacitado")
    private byte discapacitado;
    /**
     * Nombre de la persona.
     */
    @Basic
    @Column(name = "nombre")
    private String nombre;
    /**
     * Primer apellido de la persona.
     */
    @Basic
    @Column(name = "primer_pellido")
    private String primerApellido;
    /**
     * Segundo apellido de la persona.
     */
    @Basic
    @Column(name = "segundo_pellido")
    private String segundoApellido;
    
    /**
     * Fecha de nacimiento de la persona.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_Nacimiento")
    private Calendar fechaNacimiento;

    /**
     * Lista de vehículos asociados a la persona.
     */
    @OneToMany(cascade
            = {CascadeType.REMOVE,
                CascadeType.PERSIST,
                CascadeType.MERGE
            }, fetch = FetchType.LAZY)
    private List<Vehiculo> vehiculos;

    /**
     * Lista de trámites asociados a la persona.
     */
    @OneToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE,
        CascadeType.REFRESH
    }, fetch = FetchType.LAZY, mappedBy = "persona")
    private List<Tramite> tramites;

    /**
     * Constructor por defecto de la clase Persona.
     */
    public Persona() {
    }

    /**
     * Constructor de la clase Persona.
     * 
     * @param telefono El teléfono de la persona.
     * @param RFC El RFC de la persona.
     * @param discapacitado Indica si la persona tiene alguna discapacidad.
     * @param nombre El nombre de la persona.
     * @param primerApellido El primer apellido de la persona.
     * @param segundoApellido El segundo apellido de la persona.
     * @param fechaNacimiento La fecha de nacimiento de la persona.
     */
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

    /**
     * Metodo que devuelve el ID
     * @return ID de la persona
     */
    public Integer getId() {
        return id;
    }

    /**
     * metodo que establece el Id
     * @param id id de la persona
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Metodo que devuelve el telefono
     * @return telefono de la persona
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     *  metodo que establece el Telefono
     * @param telefono telefono de la persona
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Metodo que devuelve el RFC
     * @return RFc de la persona
     */
    public String getRFC() {
        return RFC;
    }

    /**
     *  metodo que establece el RFC
     * @param RFC RFC de la persona
     */
    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    /**
     * Metodo que devuelve la discapacidad de la persona
     * @return 1 o 0
     */
    public byte getDiscapacitado() {
        return discapacitado;
    }

    /**
     *  metodo que establece la discapacidad 
     * @param discapacitado 1 o 0
     */
    public void setDiscapacitado(byte discapacitado) {
        this.discapacitado = discapacitado;
    }

    /**
     * Metodo que devuelve el nombre
     * @return nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *  metodo que establece el Nombre 
     * @param nombre nombre de la persona
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que devuelve el primer Apellido
     * @return primer Apellido de la persona
     */
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     *  metodo que establece el Primer Apellido 
     * @param primerApellido primer Apellido de la persona
     */
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * Metodo que devuelve el Segundo Apellido 
     * @return Segundo Apellido de la persona
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     *  metodo que establece el Segundo Apellido 
     * @param segundoApellido Segundo apellido de la persona
     */
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * Metodo que devuelve los Vehiculos
     * @return lista de Vehiculo
     */
    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    /**
     *  metodo que establece la lista de vehiculos
     * @param vehiculos lista de vehiculo
     */
    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    
    /**
     * Metodo que devuelve los Tramites
     * @return Lista de Tramites
     */
    public List<Tramite> getTramites() {
        return tramites;
    }

    /**
     *  metodo que establece la lista de tramites
     * @param tramites lista de tramites
     */
    public void setTramites(List<Tramite> tramites) {
        this.tramites = tramites;
    }

    /**
     * Metodo que devuelve la Fecha de nacimiento
     * @return Fecha de Nacimineto de la persona
     */
    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * metodo que establece la fecha de nacimiento
     * @param fechaNacimiento fecha de nacimiento de la persona
     */
    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Metodo que verifica si una licencia es vigentes
     * @return verdadero o false
     */
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
    
    /**
     * Metodo que obtiene las licencias vigentes
     * @return null o la licencia
     */
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
