package cl.usm.GestorClientes.dto;

import cl.usm.GestorClientes.dto.Cliente;
import cl.usm.GestorClientes.dto.Pais;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-12-04T16:31:05")
@StaticMetamodel(Ciudad.class)
public class Ciudad_ { 

    public static volatile ListAttribute<Ciudad, Cliente> clienteList;
    public static volatile SingularAttribute<Ciudad, Pais> codPais;
    public static volatile SingularAttribute<Ciudad, Integer> codCiudad;
    public static volatile SingularAttribute<Ciudad, String> nombre;

}