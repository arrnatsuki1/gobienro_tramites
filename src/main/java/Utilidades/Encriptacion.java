package Utilidades;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Entidades.Persona;
import Entidades.Tramite;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Clase Encriptacion
 * @author Rosa Rodriguez y Jose Carlos Trista Rosales
 */
public class Encriptacion {
    /**
     * Metodo Constructor Vacio de la Clase Encriptacion
     */
    public Encriptacion(){
        
    }
    /**
     * Declaracion de Variables de la Clase encriptacion
     */
    private static final String ALGORITMO = "AES";
    private static final String MODO = "ECB";
    private static final String PADDING = "PKCS5Padding";
    private static final String CLAVE_SECRETA = "ProyectoFinalU2B";

    /**
     * Metodo que encripta un nombre
     * @param texto texto
     * @return null o el nombre encriptado dependiendo del caso
     */
    public String encriptar(String texto) {
        try {
            SecretKeySpec clave = new SecretKeySpec(CLAVE_SECRETA.getBytes(StandardCharsets.UTF_8), ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO + "/" + MODO + "/" + PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, clave);
            byte[] textoEncriptado = cipher.doFinal(texto.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(textoEncriptado);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para desencriptar el nombre
     * @param textoEncriptado textoEncript
     * @return null o el nombre desencriptada dependiendo del caso
     */
    public String desencriptar(String textoEncriptado) {
        try {
            SecretKeySpec clave = new SecretKeySpec(CLAVE_SECRETA.getBytes(StandardCharsets.UTF_8), ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO + "/" + MODO + "/" + PADDING);
            cipher.init(Cipher.DECRYPT_MODE, clave);
            byte[] textoDesencriptado = cipher.doFinal(Base64.getDecoder().decode(textoEncriptado));
            return new String(textoDesencriptado, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    /**
     * Metodo para desencriptar los nombres de una lista de personas
     * @param lista List<Persona>
     * @return regresa una lista con todas los nombres de todas las personas
     * desencriptados
     */
    public List<Persona> desencriptarLista(List<Persona> lista) {
        List<Persona> lista_desencriptada = new ArrayList();
        
        for(Persona persona : lista) {
            
            persona.setNombre( this.desencriptar( persona.getNombre()) );
            persona.setPrimerApellido( this.desencriptar( persona.getPrimerApellido()) );
            persona.setSegundoApellido( this.desencriptar( persona.getSegundoApellido() ) );
            lista_desencriptada.add(persona);
            
        }
        return lista_desencriptada;
    }
    /**
     * Metodo para desencriptar todos los nombres de todas las personas
     * que vienen en los tramites
     * @param lista lista de los tramites
     * @return regresa List<Tramite> con el nombre de las personas que lo hicieron
     * desencriptado
     */
    public List<Tramite> desencriptarListaTramite(List<Tramite> lista) {
        List<Persona> personasDesencriptadas = new ArrayList<>(); // crea una lista auxiliar de personas
        List<Tramite> lista_tramitePersona = new ArrayList<>(); // crea una lista de trámites

        for (Tramite tramite : lista) {
            Persona persona = tramite.getPersona(); // obtiene la persona del trámite

            if (!personasDesencriptadas.contains(persona)) { // si la persona no está en la lista auxiliar
                personasDesencriptadas.add(persona); // agrega la persona a la lista auxiliar
                persona.setNombre(this.desencriptar(persona.getNombre()));
                persona.setPrimerApellido(this.desencriptar(persona.getPrimerApellido()));
                persona.setSegundoApellido(this.desencriptar(persona.getSegundoApellido())); 
            }
            lista_tramitePersona.add(tramite); // agrega el trámite a la lista
        }

        return lista_tramitePersona;
    }
    /**
     * Metodo para encriptar el nombre de una persona
     * @param persona recibe un objeto de tipo Persona con los nombres texto plano
     * para encriptarlos
     * @return regresa un objeto de tipo Persona con el nombre, primerApellido y
     * segundoApellido encriptados
     */
    public Persona encriptarNombrePersona(Persona persona) {
        persona.setNombre(encriptar(persona.getNombre()));
        persona.setPrimerApellido(encriptar(persona.getPrimerApellido()));
        persona.setSegundoApellido(encriptar(persona.getSegundoApellido()));
        return persona;
    }
    /**
     * Metodo para desencriptar el nombre de todas las personas en una lista
     * @param lista List<Persona> con nombres encriptados
     * @return regresa una lista con todas las personas pero con el nombre, primerApellido y
     * segundoApellido desencriptados
     */
    public List<Persona> desencriptarNombresPersonas(List<Persona> lista) {
        for(Persona p : lista) {
            p.setNombre(desencriptar(p.getNombre()));
            p.setPrimerApellido(desencriptar(p.getPrimerApellido()));
            p.setSegundoApellido(desencriptar(p.getSegundoApellido()));
        }
        return lista;
    }
    
}
