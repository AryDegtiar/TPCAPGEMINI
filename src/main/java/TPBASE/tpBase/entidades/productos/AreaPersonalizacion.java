package TPBASE.tpBase.entidades.productos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
    *************************************************
    * * * * * * * * * * * * * * * * * * * * * * * * *
    *               NO SE ESTA USANDO               *
    * * * * * * * * * * * * * * * * * * * * * * * * *
    *************************************************
 */
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

    public AreaPersonalizacion(String area) {
        this.area = area;
    }

    public AreaPersonalizacion() {
    }
}
