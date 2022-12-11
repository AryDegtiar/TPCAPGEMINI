package TPBASE.tpBase.entidades.actores;

import TPBASE.tpBase.entidades.compras.CompraRealizada;
import TPBASE.tpBase.entidades.superclases.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente extends EntidadPersistente {

    @NotNull @Email
    @Column(name = "mail")
    private String mail;
    @NotNull
    @Column(name = "contrasenia")
    private String contrasenia;

    @OneToMany
    @JoinColumn(name = "clienteID", referencedColumnName = "id")
    private List<CompraRealizada> compraRealizadas;

    public void agregarCompra(CompraRealizada compraRealizada) {
        this.compraRealizadas.add(compraRealizada);
    }
}
