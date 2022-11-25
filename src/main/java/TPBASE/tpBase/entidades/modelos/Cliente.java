package TPBASE.tpBase.entidades.modelos;

import TPBASE.tpBase.entidades.compras.CompraRealizada;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
    @Getter
    @Id
    @GeneratedValue
    private Integer clie_id;

    @Column(name = "mail")
    private String mail;
    @Column(name = "contrasenia")
    private String contrasenia;

    @OneToMany
    @JoinColumn(name = "clienteID", referencedColumnName = "clie_id")
    private List<CompraRealizada> compraRealizadas;
}
