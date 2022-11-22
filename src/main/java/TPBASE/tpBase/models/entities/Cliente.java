package TPBASE.tpBase.models.entities;

import TPBASE.tpBase.models.controladores.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente extends EntidadPersistente {
    private String mail;
    private String contrasenia;
}
