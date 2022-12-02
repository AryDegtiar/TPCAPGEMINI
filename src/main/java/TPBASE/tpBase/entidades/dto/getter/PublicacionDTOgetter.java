package TPBASE.tpBase.entidades.dto.getter;

import TPBASE.tpBase.entidades.actores.Vendedor;
import TPBASE.tpBase.entidades.enums.EnumEstado;
import TPBASE.tpBase.entidades.productos.Personalizacion;
import TPBASE.tpBase.entidades.productos.ProductoBase;
import TPBASE.tpBase.entidades.productos.Publicacion;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.*;
import java.util.List;

@Projection(name = "PublicacionDTOgetter", types = Publicacion.class)
public interface PublicacionDTOgetter {
    Integer getId();

    boolean getActivo();

    EnumEstado getEstadoPublicacion();

    Integer getPrecioTotal();

    Vendedor getVendedor();

    List<Personalizacion> getPersonalizaciones();

    ProductoBase getProductoBase();


}
