package TPBASE.tpBase.models.producto;

import TPBASE.tpBase.models.controladores.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Getter @Setter
@Entity
public class Publicacion extends EntidadPersistente {
    @Transient
    private ProductoBase productoBase;
    @Transient
    private List<Personalizacion> personalizaciones; // podria ser un set de personalizaciones
    @Column(name = "precioTotal")
    private Integer precioTotal;

}
