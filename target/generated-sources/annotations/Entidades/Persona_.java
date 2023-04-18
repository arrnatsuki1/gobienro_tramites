package Entidades;

import Entidades.Tramite;
import Entidades.Vehiculo;
import java.util.Calendar;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-18T12:13:45", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, Byte> discapacitado;
    public static volatile SingularAttribute<Persona, String> primerApellido;
    public static volatile SingularAttribute<Persona, Calendar> fechaNacimiento;
    public static volatile ListAttribute<Persona, Tramite> tramites;
    public static volatile SingularAttribute<Persona, String> segundoApellido;
    public static volatile SingularAttribute<Persona, Integer> id;
    public static volatile SingularAttribute<Persona, String> telefono;
    public static volatile SingularAttribute<Persona, String> nombre;
    public static volatile SingularAttribute<Persona, String> RFC;
    public static volatile ListAttribute<Persona, Vehiculo> vehiculos;

}