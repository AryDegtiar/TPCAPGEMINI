package TPBASE.tpBase.entidades.productos;

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
public class AtributoProducto {
    @Getter
    @Id
    @GeneratedValue
    private Integer atribperso_id;

    @Column(name = "modelo")
    private String modelo;

    @Transient
    private List<String> colores;

}
