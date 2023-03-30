package Entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Alan Rodriguez
 */
@Entity
@Table(name = "Automoviles")
@DiscriminatorValue("Automovil")
public class Automovil extends Vehiculo implements Serializable {

    @Basic
    @Column(name="color")
    private String color;
    @Basic
    @Column(name="marca")
    private String marca;
    @Basic
    @Column(name="linea")
    private String linea;
    @Basic
    @Column(name="numero_serie")
    private String nserie;
    @Basic
    @Column(name="modelo")
    private String modelo;

    @OneToMany(cascade = 
            {CascadeType.REMOVE,
                CascadeType.PERSIST,
                CascadeType.MERGE
            }, fetch = FetchType.LAZY)
    private List<Placa> placas;
    
    public Automovil() {
        super();
    }

    public Automovil(String color, String marca, String linea, String nserie,
            String modelo, List<Placa> placas, Integer id, Persona duenio) {
        super(id, duenio);
        this.color = color;
        this.marca = marca;
        this.linea = linea;
        this.nserie = nserie;
        this.modelo = modelo;
        this.placas = placas;
    }

    public List<Placa> getPlacas() {
        return placas;
    }

    public void setPlacas(List<Placa> placas) {
        this.placas = placas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getNserie() {
        return nserie;
    }

    public void setNserie(String nserie) {
        this.nserie = nserie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    
}
