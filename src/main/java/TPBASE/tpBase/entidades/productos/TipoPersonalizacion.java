package TPBASE.tpBase.entidades.productos;

import TPBASE.tpBase.entidades.superclases.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "tipo_personalizacion")
public class TipoPersonalizacion extends EntidadPersistente {

    @Column(name = "tipo")
    private String tipo;

    public TipoPersonalizacion(){

    }

    public TipoPersonalizacion(String tipo) {
        this.tipo = tipo;
    }
}
