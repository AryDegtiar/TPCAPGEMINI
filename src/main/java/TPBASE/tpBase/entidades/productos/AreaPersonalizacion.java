package TPBASE.tpBase.entidades.productos;

import TPBASE.tpBase.entidades.superclases.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "area_personalizacion")
public class AreaPersonalizacion extends EntidadPersistente {

    @Column(name = "area")
    public String area;

    public AreaPersonalizacion(String area) {
        this();
        this.area = area;
    }

    public AreaPersonalizacion() {
    }
}
