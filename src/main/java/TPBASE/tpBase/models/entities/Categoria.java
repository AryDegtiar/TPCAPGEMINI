package TPBASE.tpBase.models.entities;

import TPBASE.tpBase.models.controladores.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Categoria extends EntidadPersistente {
    private String name;
}
