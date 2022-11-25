package TPBASE.tpBase.entidades.compras;

import TPBASE.tpBase.entidades.productos.Publicacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cantidadXProducto")
public class CantidadXProducto {
    @Id
    @GeneratedValue
    private Integer cantXProdID;

    @Column(name = "cantidad")
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "publicacion_xcantidad")
    private Publicacion publicacion;

    public CantidadXProducto(){

    }
}
