package TPBASE.tpBase.models.producto;

import TPBASE.tpBase.models.controladores.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Getter @Setter
@Entity
public class AtributoProducto extends EntidadPersistente {
    @Column(name = "modelo")
    private String modelo;
    @Transient
    private List<String> colores;

}
