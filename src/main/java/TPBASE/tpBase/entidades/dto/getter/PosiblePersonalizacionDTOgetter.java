package TPBASE.tpBase.entidades.dto.getter;

import TPBASE.tpBase.entidades.productos.AreaPersonalizacion;
import TPBASE.tpBase.entidades.productos.PosiblePersonalizacion;
import TPBASE.tpBase.entidades.productos.TipoPersonalizacion;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "PosiblePersonalizacionDTOgetter", types = PosiblePersonalizacion.class)
public interface PosiblePersonalizacionDTOgetter {

    Integer getId();

    boolean getActivo();

    AreaPersonalizacion getAreaPersonalizacion();
    TipoPersonalizacion getTipoPersonalizacion();

}
