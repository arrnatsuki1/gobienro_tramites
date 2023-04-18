package Entidades;

import Entidades.Placa;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-18T12:13:45", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Automovil.class)
public class Automovil_ extends Vehiculo_ {

    public static volatile SingularAttribute<Automovil, String> marca;
    public static volatile SingularAttribute<Automovil, String> color;
    public static volatile SingularAttribute<Automovil, String> nserie;
    public static volatile SingularAttribute<Automovil, String> modelo;
    public static volatile SingularAttribute<Automovil, String> linea;
    public static volatile ListAttribute<Automovil, Placa> placas;

}