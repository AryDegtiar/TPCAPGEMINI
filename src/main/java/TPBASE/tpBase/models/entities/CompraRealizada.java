package TPBASE.tpBase.models.entities;

import TPBASE.tpBase.models.controladores.EntidadPersistente;
import TPBASE.tpBase.models.producto.Publicacion;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.util.List;

@Getter
@Setter
public class CompraRealizada extends EntidadPersistente {
    private DateFormat fecha;
    private List<Publicacion> productos;
    private Integer precioTotal;
}
