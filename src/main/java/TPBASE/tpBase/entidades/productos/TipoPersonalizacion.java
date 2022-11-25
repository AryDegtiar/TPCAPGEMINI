package TPBASE.tpBase.entidades.productos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "tipo_personalizacion")
public class TipoPersonalizacion {
    @Getter
    @Id
    @GeneratedValue
    private Integer tipoperso_id;

    @Column(name = "tipo")
    private String tipo;

    public TipoPersonalizacion(){

    }
}
