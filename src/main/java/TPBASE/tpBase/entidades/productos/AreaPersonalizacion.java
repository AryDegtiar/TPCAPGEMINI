package TPBASE.tpBase.entidades.productos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "area_personalizacion")
public class AreaPersonalizacion {
    @Getter
    @Id
    @GeneratedValue
    private Integer areaperso_id;

    @Column(name = "area")
    public String area;
    @OneToMany // revisar y fiajarse que cre una table intermedia
    public List<tipoPersonalizacion> personalizacionesProductos;
}
