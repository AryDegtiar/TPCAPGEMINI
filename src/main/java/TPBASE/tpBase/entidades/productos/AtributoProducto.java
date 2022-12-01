package TPBASE.tpBase.entidades.productos;

import TPBASE.tpBase.entidades.superclases.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/*
 *************************************************
 * * * * * * * * * * * * * * * * * * * * * * * * *
 *               NO SE ESTA USANDO               *
 * * * * * * * * * * * * * * * * * * * * * * * * *
 *************************************************
 */

@Getter @Setter
@Entity
@Table(name = "atributo_producto")
public class AtributoProducto extends EntidadPersistente {

    @Column(name = "modelo")
    private String modelo;

    @Transient
    private List<String> colores;

}
