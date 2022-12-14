package TPBASE.tpBase.entidades.compras;

import TPBASE.tpBase.entidades.productos.Publicacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "cantidadXProducto")
public class CantidadXProducto {
    @Id
    @GeneratedValue
    private Integer cantXProdID;

    @NotNull
    @Column(name = "cantidad")
    private Integer cantidad;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "publicacion_xcantidad")
    private Publicacion publicacion;

    public CantidadXProducto(Publicacion publicacion, Integer integer) {
        this.publicacion = publicacion;
        this.cantidad = integer;
    }

    public CantidadXProducto() {

    }
}
