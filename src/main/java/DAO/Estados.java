package DAO;

/**
 * Clase que contiene los Estados de las placas y las licencias
 * ademas de los tipos de persona
 * @author Rosa Rodriguez y Jose Trista
 */
public class Estados {
    /**
     * Metodo estatico que asigna un valor String a las placas activas
     */
    public static String PLACA_ACTIVA = "VIGENTE";
    
    /**
     * Metodo estatico que asigna un valor String a las placas no activas
     */
    public static String PLACA_NO_ACTIVA = "EXPIRADA";
    
    /**
     * Metodo estatico que asinga un valor Byte a las personas discapacitadas
     */
    public static byte PERSONA_DISCAPACITADA = 1;
    
    /**
     * Metodo estatico que asigna un valor Byte a las personas no 
     * discapacitadas
     */
    public static byte PERSONA_NO_DISCAPACITADA = 0;
    
    /**
     * Metodo estatico que asigna un valor String a las Licencias vigentes
     */
    public static String LICENCIA_VIGENTE = "VIGENTE";
    
    /**
     * Metodo estatico que asgina un valor String a las Licencias no 
     * vigentes
     */
    public static String LICENCIA_NO_VIGENTE = "EXPIRADA";
    
    /**
     * Metodo estatico que asigna un valor Byte a los tramites de tipo licencia
     *      */
    public static byte TIPO_LICENCIA = 1;
    
    /**
     * Metodo estatico que asigna un valor Byte a los tramites de tipo Placa
     */
    public static byte TIPO_PLACA = 0;
}
