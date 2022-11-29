package TPBASE.tpBase.entidades.actores;

import TPBASE.tpBase.entidades.metodosPagos.MetodoPago;
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

    @Column(name = "nombre_tienda")
    private String nombreTienda;

    @ManyToMany //funca
    private List<MetodoPago> metodoPago;

    public Vendedor(String mail, String contrasenia, String nombreTienda, List<MetodoPago> metodoPago) {
        this.mail = mail;
        this.contrasenia = contrasenia;
        this.nombreTienda = nombreTienda;
        this.metodoPago = metodoPago;
    }

    public Vendedor() {
    }

}
