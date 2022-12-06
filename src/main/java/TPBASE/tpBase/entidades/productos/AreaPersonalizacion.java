package TPBASE.tpBase.entidades.productos;

import TPBASE.tpBase.entidades.superclases.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter @Setter
@Entity
@Table(name = "area_personalizacion")
public class AreaPersonalizacion extends EntidadPersistente {
    @NotNull
    @NotBlank
    @Column(name = "area")
    public String area;

    public AreaPersonalizacion(String area) {
        this();
        this.area = area;
    }

    public AreaPersonalizacion() {
    }
}
