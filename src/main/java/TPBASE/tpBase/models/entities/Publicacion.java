package TPBASE.tpBase.models.entities;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class Publicacion extends EntidadPersistente{
    private ProductoBase productoBase;
    private List<Personalizacion> personalizaciones;
    private Integer precioTotal;
}
