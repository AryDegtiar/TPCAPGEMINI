package TPBASE.tpBase.entidades.compras;

import TPBASE.tpBase.entidades.actores.Cliente;
import TPBASE.tpBase.entidades.enums.EnumMetodoPago;
import TPBASE.tpBase.entidades.actores.Vendedor;
<<<<<<< HEAD
import TPBASE.tpBase.entidades.productos.Publicacion;
import com.sun.javafx.collections.MappingChange;
=======
import TPBASE.tpBase.entidades.metodosPagos.MetodoPago;
import TPBASE.tpBase.entidades.productos.Publicacion;
>>>>>>> test-api-dto
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
<<<<<<< HEAD
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
=======
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
>>>>>>> test-api-dto
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

    @NotNull
    @Column(name = "fecha", columnDefinition = "DATE")
    private LocalDate fecha;

<<<<<<< HEAD
<<<<<<< HEAD
    @OneToMany // se puede agregar esto por cascada
    @JoinColumn(name = "compraRealizadaID", referencedColumnName = "id")
=======
=======
    @NotNull
    @Column(name = "hora", columnDefinition = "TIME")
    private LocalTime hora;

>>>>>>> test-api-dto
    // ojo ver, es para opcion 2
    @OneToMany
    @JoinColumn(name = "compraRealizadaID", referencedColumnName = "comprareali_id")
>>>>>>> test-api-dto
    private List<CantidadXProducto> cantidadXProductos;

    // ojo ver, es para opcion 1
    /*
    @ManyToMany
    private List<Publicacion> publicaciones;
     */

    @Column(name = "precioTotal")
    private BigInteger precioTotal;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EnumMetodoPago metodoPago;

    @NotNull
    @JoinColumn(name = "vendedor_CompraRealizada")
    private static Vendedor vendedor;

    @NotNull
    @JoinColumn(name = "direccion")
    private String direccionEnvio;


    public CompraRealizada(List<CantidadXProducto> cantidadXProductos, MetodoPago metodoPago, Vendedor vendedor,String direccionEnvio) {
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.cantidadXProductos = cantidadXProductos;
        this.precioTotal = calcularPrecioTotal(cantidadXProductos);
        this.metodoPago = metodoPago.getMetodoPago();
        this.vendedor = vendedor;
        this.direccionEnvio = direccionEnvio;
    }

    public CompraRealizada() {

    }

    private BigInteger calcularPrecioTotal(List<CantidadXProducto> cantidadXProductos) {
        BigInteger precioTotal = BigInteger.ZERO;
        for (CantidadXProducto cantidadXProducto : cantidadXProductos) {
            precioTotal = precioTotal.add( BigInteger.valueOf(cantidadXProducto.getCantidad()).multiply( BigInteger.valueOf(cantidadXProducto.getPublicacion().getPrecioTotal()) ) );;
        }
        return precioTotal;
    }
}
