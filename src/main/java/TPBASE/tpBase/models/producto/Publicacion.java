package TPBASE.tpBase.models.producto;

import TPBASE.tpBase.models.controladores.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class Publicacion extends EntidadPersistente {
    @ManyToOne
    @JoinColumn(name = "productoBase_id", referencedColumnName = "id")
    private ProductoBase productoBase;
    @Transient
    private List<Personalizacion> personalizaciones; // podria ser un set de personalizaciones
    @Column(name = "precioTotal")
    private Integer precioTotal;

    public void agregarPersonalizacion(Personalizacion personalizacion){
        this.personalizaciones.add(personalizacion);
    }

}
