/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

import Entidades.Persona;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rosa Rodriguez
 */
public class ChiperPersonas extends Encriptacion{
    
    public List<Persona> desencriptarLista(List<Persona> lista) {
        List<Persona> lista_desencriptada = new ArrayList();
        
        for(Persona persona : lista) {
            
            persona.setNombre( desencriptar( persona.getNombre()) );
            persona.setPrimerApellido( desencriptar( persona.getPrimerApellido()) );
            persona.setSegundoApellido( desencriptar( persona.getSegundoApellido() ) );
            lista_desencriptada.add(persona);
            
        }
        return lista_desencriptada;
    }
    
}
