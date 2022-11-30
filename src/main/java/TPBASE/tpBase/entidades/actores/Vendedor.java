package TPBASE.tpBase.entidades.actores;

import TPBASE.tpBase.entidades.metodosPagos.MetodoPago;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @Email(message = "Mail invalido")
    @NotBlank(message = "Mail invalido")
    @Column(name = "mail")
    private String mail;

    @NotNull(message = "Contraseña invalida")
    @NotBlank(message = "Contraseña invalida")
    @Column(name = "contrasenia")
    private String contrasenia;

    @NotNull(message = "Nombre de la tienda Invalido")
    @NotBlank(message = "Nombre de la tienda Invalido")
    @Column(name = "nombre_tienda")
    private String nombreTienda;

    @NotNull(message = "Metodo de pago invalido")
    @NotEmpty(message = "Metodo de pago invalido")
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
