package Entidades;

import Entidades.Automovil;
import java.util.Calendar;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-18T12:13:45", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Placa.class)
public class Placa_ extends Tramite_ {

    public static volatile SingularAttribute<Placa, String> codigo;
    public static volatile SingularAttribute<Placa, Automovil> auto;
    public static volatile SingularAttribute<Placa, String> activa;
    public static volatile SingularAttribute<Placa, Calendar> fechaRecepcion;

}