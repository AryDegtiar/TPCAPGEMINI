package TPBASE.tpBase.entidades.productos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "posiblePersonalizacion")
public class PosiblePersonalizacion {
    @Id
    @GeneratedValue
    private Integer posiblePersoID;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "posible_area_personalizacion")
    private AreaPersonalizacion areaPersonalizacion;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "posible_tipo_personalizacion")
    private TipoPersonalizacion tipoPersonalizacion;

    public PosiblePersonalizacion(){

    }
}
