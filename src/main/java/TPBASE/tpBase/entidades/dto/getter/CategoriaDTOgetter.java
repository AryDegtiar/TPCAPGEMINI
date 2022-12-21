package TPBASE.tpBase.entidades.dto.getter;

import TPBASE.tpBase.entidades.productos.Personalizacion;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "PersonalizacionDTOgetter", types = Personalizacion.class)
public interface CategoriaDTOgetter {
    Integer getId();
    String getNombre();
}
