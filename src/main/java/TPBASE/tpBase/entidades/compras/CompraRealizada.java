package TPBASE.tpBase.entidades.compras;

import TPBASE.tpBase.entidades.enums.EnumMetodoPago;
import TPBASE.tpBase.entidades.actores.Vendedor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "compra_realizada")
public class CompraRealizada {
    @Getter
    @Id
    @GeneratedValue
    private Integer comprareali_id;

    @Column(name = "fecha", columnDefinition = "DATE")
    private LocalDate fecha;

    @OneToMany
    @JoinColumn(name = "compraRealizadaID", referencedColumnName = "comprareali_id")
    private List<CantidadXProducto> cantidadXProductos;

    @Column(name = "precioTotal")
    private Integer precioTotal;

    @Enumerated(EnumType.STRING)
    private EnumMetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "vendedor_CompraRealizada")
    private Vendedor vendedor;

}
