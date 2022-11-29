package TPBASE.tpBase.entidades.metodosPagos;

import TPBASE.tpBase.entidades.enums.EnumMetodoPago;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "MetodoPago")
public class MetodoPago {
    @Id
    @GeneratedValue
    private Integer metodoPagoID;

    @Enumerated(EnumType.STRING)
    private EnumMetodoPago metodoPago;

    public MetodoPago() {
    }

    public MetodoPago(EnumMetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }
}
