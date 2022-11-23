package TPBASE.tpBase.entidades.compras;

import TPBASE.tpBase.entidades.enums.MetodoPago;
import TPBASE.tpBase.entidades.modelos.Cliente;
import TPBASE.tpBase.entidades.modelos.Vendedor;
import TPBASE.tpBase.entidades.productos.Publicacion;
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
    @OneToMany // revisar y fiajarse que cre una table intermedia
    private List<Publicacion> productos;
    @Column(name = "precioTotal")
    private Integer precioTotal;

    @Transient
    private MetodoPago metodoPago;

    @Transient
    private Tienda tienda;

    @Transient
    private Cliente cliente;

}
