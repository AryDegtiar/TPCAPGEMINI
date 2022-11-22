package TPBASE.tpBase.models.entities;

import TPBASE.tpBase.models.controladores.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Cliente extends EntidadPersistente {
    @Column(name = "mail")
    private String mail;
    @Column(name = "contrasenia")
    private String contrasenia;
}
