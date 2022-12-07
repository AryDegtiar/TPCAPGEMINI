package TPBASE.tpBase.entidades.compras;

import TPBASE.tpBase.entidades.enums.EnumMetodoPago;
import TPBASE.tpBase.entidades.actores.Vendedor;
import TPBASE.tpBase.entidades.productos.Publicacion;
import com.sun.javafx.collections.MappingChange;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "compra_realizada")
public class CompraRealizada {
    @Getter
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "fecha", columnDefinition = "DATE")
    private LocalDate fecha;

    @OneToMany // se puede agregar esto por cascada
    @JoinColumn(name = "compraRealizadaID", referencedColumnName = "id")
    private List<CantidadXProducto> cantidadXProductos;

    @Column(name = "precioTotal")
    private Integer precioTotal;

    @Enumerated(EnumType.STRING)
    private EnumMetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "vendedor_CompraRealizada")
    private Vendedor vendedor;

}
