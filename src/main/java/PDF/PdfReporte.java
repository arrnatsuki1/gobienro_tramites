/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF;

/**
 *  Clase para setear los atributos de un tramite y poder generar una fila
 * en el reporte
 * @author jctri
 */
public class PdfReporte {
    /**
     * Tipo del tramite ExpedicionLicencia/ExpedicionPlaca
     */
    private String tipoTramite;
    /**
     * Costo del tramite eg. 1500
     */
    private String costo;
    /**
     * Fecha del tramite eg. 15 octubre 2003
     */
    private String fecha;
    /**
     * Estado del tramite Vigente/Expirado
     */
    private String estado;
    /**
     * Nombre de la persona eg. Rosa Rodriguez Valenzuela
     */
    private String nombrePersona;
    /**
     * Constructor por defecto
     */
    public PdfReporte() {
    }
    /**
     * Constructor que recibe todos los atributos como parametro
     * @param tipoTramite String tipo tramite Placa/Licencia
     * @param costo String costo del tramite
     * @param fecha String fecha de realizacion
     * @param estado String estado, si sigue activo o no
     * @param nombrePersona String nombre de la persona que realizo el tramite
     */
    public PdfReporte(String tipoTramite, String costo, String fecha, String estado, String nombrePersona) {
        this.tipoTramite = tipoTramite;
        this.costo = costo;
        this.fecha = fecha;
        this.estado = estado;
        this.nombrePersona = nombrePersona;
    }
    /**
     * regresa el tipo del tramite realizado
     * @return String
     */
    public String getTipoTramite() {
        return tipoTramite;
    }
    /**
     * Regresa el tipo de tramite
     * @param tipoTramite String
     */
    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }
    /**
     * Regresa el costo del tramite
     * @return String
     */
    public String getCosto() {
        return costo;
    }
    /**
     * setea el costo del tramite
     * @param costo String
     */
    public void setCosto(String costo) {
        this.costo = costo;
    }
    /**
     * Regresa la fecha del tramite
     * @return String
     */
    public String getFecha() {
        return fecha;
    }
    /**
     * Setea la fecha del tramite
     * @param fecha String
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    /**
     * Regresa el estado del tramite Activo/Expirado
     * @return String
     */
    public String getEstado() {
        return estado;
    }
    /**
     * Setea el estado del tramite
     * @param estado String
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    /**
     * Regresa el nombre completo de la persona
     * @return String
     */
    public String getNombrePersona() {
        return nombrePersona;
    }
    /**
     * Setea el nombre de la persona
     * @param nombrePersona String
     */
    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }
    
    
}
