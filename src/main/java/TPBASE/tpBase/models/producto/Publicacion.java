package TPBASE.tpBase.models.producto;

import TPBASE.tpBase.models.controladores.EntidadPersistente;

import java.util.List;

public class Publicacion extends EntidadPersistente {
    private ProductoBase productoBase;
    private List<Personalizacion> personalizaciones;
    private Integer precioTotal;

}
