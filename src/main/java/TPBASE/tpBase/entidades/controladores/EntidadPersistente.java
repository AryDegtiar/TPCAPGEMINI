package TPBASE.tpBase.entidades.controladores;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntidadPersistente {
    @Getter
    @Id
    @GeneratedValue
    private Integer id;
}