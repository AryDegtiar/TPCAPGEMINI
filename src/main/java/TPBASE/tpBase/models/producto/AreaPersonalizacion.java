package TPBASE.tpBase.models.producto;

import TPBASE.tpBase.models.controladores.EntidadPersistente;

import java.util.List;

public class AreaPersonalizacion extends EntidadPersistente {
    public String area;
    public List<tipoPersonalizacion> personalizacionesProductos;
}
