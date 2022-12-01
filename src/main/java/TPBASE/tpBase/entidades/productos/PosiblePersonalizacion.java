package TPBASE.tpBase.entidades.productos;

import TPBASE.tpBase.entidades.superclases.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "posiblePersonalizacion")
public class PosiblePersonalizacion extends EntidadPersistente {
    @ManyToOne
    @JoinColumn(name = "posible_area_personalizacion")
    private AreaPersonalizacion areaPersonalizacion;

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
