package TPBASE.tpBase.entidades.productos;

import TPBASE.tpBase.entidades.superclases.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "posiblePersonalizacion")
public class PosiblePersonalizacion extends EntidadPersistente {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "posible_area_personalizacion")
    private AreaPersonalizacion areaPersonalizacion;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "posible_tipo_personalizacion")
    private TipoPersonalizacion tipoPersonalizacion;

    public PosiblePersonalizacion(){

    }

    public PosiblePersonalizacion(AreaPersonalizacion areaPersonalizacion, TipoPersonalizacion tipoPersonalizacion) {
        this.areaPersonalizacion = areaPersonalizacion;
        this.tipoPersonalizacion = tipoPersonalizacion;
    }
}
