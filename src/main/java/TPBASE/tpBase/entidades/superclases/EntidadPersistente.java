package TPBASE.tpBase.entidades.superclases;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntidadPersistente {
    @Getter
    @Id
    @GeneratedValue
    private Integer id;

    @Setter
    @Getter
    @JoinColumn(name = "activo")
    private boolean activo;


    public EntidadPersistente() {
        this.activo = true;
    }

}


