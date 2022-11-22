package TPBASE.tpBase.models.producto;

import TPBASE.tpBase.models.controladores.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter @Setter
@Entity
public class tipoPersonalizacion extends EntidadPersistente {
    @Column(name = "tipo")
    private String tipo;
    // NO VA
    //private Integer precio;
    //private String contenido; //se le puede agregar el contenido que va a ser como un link de muestra o algo asi
}
