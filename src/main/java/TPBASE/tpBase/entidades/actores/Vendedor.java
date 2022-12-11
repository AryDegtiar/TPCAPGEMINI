package TPBASE.tpBase.entidades.actores;

import TPBASE.tpBase.entidades.metodosPagos.MetodoPago;
import TPBASE.tpBase.entidades.superclases.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "vendedor")
public class Vendedor extends EntidadPersistente {
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
    private List<MetodoPago> metodoPagos;

    public boolean contieneMetodoPago(MetodoPago metodoPago) {
        return metodoPagos.contains(metodoPago);
    }

    public Vendedor(String mail, String contrasenia, String nombreTienda) {
        this();
        this.mail = mail;
        this.contrasenia = contrasenia;
        this.nombreTienda = nombreTienda;
    }

    public Vendedor() {
         this.metodoPagos = new ArrayList<>();
    }

    public void addMetodoPago(MetodoPago metodoPago){
        this.metodoPagos.add(metodoPago);
    }

}
