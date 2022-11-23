package TPBASE.tpBase.entidades.modelos;

import TPBASE.tpBase.entidades.compras.CompraRealizada;
import TPBASE.tpBase.entidades.enums.MetodoPago;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "vendedor")
public class Vendedor {
    @Getter
    @Id
    @GeneratedValue
    private Integer vendedor_id;

    @Column(name = "mail")
    private String mail;

    @Column(name = "contrasenia")
    private String contrasenia;

    @OneToOne
    private CompraRealizada compraRealizada;

    @Transient // revisar y fiajarse que cre una table intermedia
    private List<MetodoPago> metodosPago;

    public void agregarProducto(){

    }

    public void pausarProducto(){

    }

    public void eliminarProducto(){

    }
}
