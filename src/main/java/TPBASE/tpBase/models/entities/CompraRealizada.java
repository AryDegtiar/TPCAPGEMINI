package TPBASE.tpBase.models.entities;

import TPBASE.tpBase.models.controladores.EntidadPersistente;
import TPBASE.tpBase.models.producto.Publicacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class CompraRealizada extends EntidadPersistente {
    @Column(name = "fecha", columnDefinition = "DATE")
    private LocalDate fecha;
    @Transient
    private List<Publicacion> productos;
    @Column(name = "precioTotal")
    private Integer precioTotal;
}
