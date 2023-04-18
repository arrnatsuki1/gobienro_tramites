/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author Rosa Rodriguez y Jose Trista
 */
public class RFCExistenteException extends Exception {
    /**
     * Metodo constructor por defecto para la excepcion RFCExistente
     */
    public RFCExistenteException() {
        super();
    }
    /**
     * Metodo constructor con mensaje para la excepcion RFCExistente
     * @param msg String mensaje
     */
    public RFCExistenteException(String msg) {
        super(msg);
    }
}
