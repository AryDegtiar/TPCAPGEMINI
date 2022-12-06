package TPBASE.tpBase.entidades.productos;

import TPBASE.tpBase.entidades.superclases.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@Entity
@Table(name = "tipo_personalizacion")
public class TipoPersonalizacion extends EntidadPersistente {
    @NotNull(message = "Tipo personalizacion invalida")
    @NotBlank(message = "Tipo personalizacion invalida")
    @Column(name = "tipo")
    private String tipo;

    public TipoPersonalizacion(){

    }

    public TipoPersonalizacion(String tipo) {
        this.tipo = tipo;
    }
}
