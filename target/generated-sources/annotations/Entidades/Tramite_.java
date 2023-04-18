package Entidades;

import Entidades.Persona;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-18T12:13:45", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Tramite.class)
public class Tramite_ { 

    public static volatile SingularAttribute<Tramite, Persona> persona;
    public static volatile SingularAttribute<Tramite, BigDecimal> costo;
    public static volatile SingularAttribute<Tramite, Calendar> fechaEmision;
    public static volatile SingularAttribute<Tramite, Integer> id;

}