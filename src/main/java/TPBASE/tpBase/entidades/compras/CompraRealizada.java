package TPBASE.tpBase.entidades.compras;

import TPBASE.tpBase.entidades.enums.EnumMetodoPago;
import TPBASE.tpBase.entidades.actores.Vendedor;
import TPBASE.tpBase.entidades.metodosPagos.MetodoPago;
import TPBASE.tpBase.entidades.productos.Publicacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private Integer comprareali_id;

    @Column(name = "fecha", columnDefinition = "DATE")
    private LocalDate fecha;

    // ojo ver, es para opcion 2
    @OneToMany
    @JoinColumn(name = "compraRealizadaID", referencedColumnName = "comprareali_id")
    private List<CantidadXProducto> cantidadXProductos;

    // ojo ver, es para opcion 1
    /*
    @ManyToMany
    private List<Publicacion> publicaciones;
     */

    @Column(name = "precioTotal")
    private BigInteger precioTotal;

    @Enumerated(EnumType.STRING)
    private EnumMetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "vendedor_CompraRealizada")
    private static Vendedor vendedor;

    //Esto deveria funcionar opcion 1
    /*
    public static CompraRealizada agregarCompra(List<Publicacion> publicaciones, MetodoPago metodoPago) {
        CompraRealizada compraRealizada = new CompraRealizada();

        if (vendedor.contieneMetodoPago(metodoPago)) {
            compraRealizada.setMetodoPago(metodoPago.getMetodoPago());
        } else {
            throw new RuntimeException("El vendedor no acepta ese metodo de pago");
        }

        compraRealizada.setPublicaciones(publicaciones);
        compraRealizada.vendedor = publicaciones.get(0).getVendedor();
        compraRealizada.setFecha(LocalDate.now());

        compraRealizada.setPrecioTotal(compraRealizada.calcularPrecioTotal(publicaciones));

        return compraRealizada;
    }
     */

    //Esto deveria funcionar opcion 2
    public static CompraRealizada agregarCompra(List<Publicacion> publicaciones, MetodoPago metodoPago) {
        CompraRealizada compraRealizada = new CompraRealizada();

        if (vendedor.contieneMetodoPago(metodoPago)) {
            compraRealizada.setMetodoPago(metodoPago.getMetodoPago());
        } else {
            throw new RuntimeException("El vendedor no acepta ese metodo de pago");
        }
        compraRealizada.setCantidadXProductos( compraRealizada.convertidorPublicacionesACantidadProducto(publicaciones) );
        compraRealizada.vendedor = publicaciones.get(0).getVendedor();
        compraRealizada.setFecha(LocalDate.now());

        compraRealizada.setPrecioTotal(compraRealizada.calcularPrecioTotal(publicaciones));

        return compraRealizada;
    }

    private List<CantidadXProducto> convertidorPublicacionesACantidadProducto(List<Publicacion> publicaciones) {
        Map<Publicacion, Integer> cantidadXProducto = new HashMap<>();
        for (Publicacion publicacion : publicaciones) {
            if (cantidadXProducto.containsKey(publicacion)) {
                cantidadXProducto.put(publicacion, cantidadXProducto.get(publicacion) + 1);
            } else {
                cantidadXProducto.put(publicacion, 1);
            }
        }
        List<CantidadXProducto> cantidadXProductos = new ArrayList<>();
        for (Publicacion publicacion : cantidadXProducto.keySet()) {
            cantidadXProductos.add( new CantidadXProducto(publicacion, cantidadXProducto.get(publicacion)) );
        }
        return cantidadXProductos;
    }

    private BigInteger calcularPrecioTotal(List<Publicacion> publicaciones) {
        BigInteger precioTotal = BigInteger.ZERO;
        for (Publicacion publicacion : publicaciones) {
            precioTotal = precioTotal.add(BigInteger.valueOf(publicacion.getPrecioTotal()));
        }
        return precioTotal;
    }
}
