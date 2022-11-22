package TPBASE.tpBase.models.producto;

import TPBASE.tpBase.models.controladores.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter @Setter
@Entity
public class Categoria extends EntidadPersistente {
    @Column(name = "categoria")
    private String categoria;
}
