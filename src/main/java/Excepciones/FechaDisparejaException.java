/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author Rosa Rodriguez
 */
public class FechaDisparejaException extends Exception {
    /**
     * Metodo constructor por defecto para la ExcepcionFechaDispareja
     */
    public FechaDisparejaException() {
        super();
    }
    /**
     * Metodo constructor con mensaje para la excepcion FechaDispareja
     * @param msg String mensaje
     */
    public FechaDisparejaException(String msg) {
        super(msg);
    }
}
