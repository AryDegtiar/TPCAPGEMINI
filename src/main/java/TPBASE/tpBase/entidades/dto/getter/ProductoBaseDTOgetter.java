package TPBASE.tpBase.entidades.dto.getter;

import TPBASE.tpBase.entidades.productos.Categoria;
import TPBASE.tpBase.entidades.productos.PosiblePersonalizacion;
import TPBASE.tpBase.entidades.productos.ProductoBase;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Projection(name = "ProductoBaseDTOgetter", types = ProductoBase.class)
public interface ProductoBaseDTOgetter {

    Integer getId();

    boolean getActivo();

    Categoria getCategoria();

    String getNombre();

    String getDescripcion();

    Integer getPrecioBase();

    Integer getTiempoFabricacion();

    // no muestra bien la lista
    List<PosiblePersonalizacion> getPosiblePersonalizaciones();
}
