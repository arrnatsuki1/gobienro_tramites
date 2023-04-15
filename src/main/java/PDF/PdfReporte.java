/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF;

/**
 *
 * @author jctri
 */
public class PdfReporte {
    private String tipoTramite;
    private String costo;
    private String fecha;
    private String estado;
    private String nombrePersona;

    public PdfReporte() {
    }

    public PdfReporte(String tipoTramite, String costo, String fecha, String estado, String nombrePersona) {
        this.tipoTramite = tipoTramite;
        this.costo = costo;
        this.fecha = fecha;
        this.estado = estado;
        this.nombrePersona = nombrePersona;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }
    
    
}
