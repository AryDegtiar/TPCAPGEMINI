package TPBASE.tpBase.entidades.dto.getter;

import TPBASE.tpBase.entidades.productos.AreaPersonalizacion;
import TPBASE.tpBase.entidades.productos.Personalizacion;
import TPBASE.tpBase.entidades.productos.PosiblePersonalizacion;
import TPBASE.tpBase.entidades.productos.TipoPersonalizacion;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.Column;

@Projection(name = "PersonalizacionDTOgetter", types = Personalizacion.class)
public interface PersonalizacionDTOgetter {

    Integer getId();
    boolean getActivo();
    PosiblePersonalizacion getPosiblePersonalizacion();
    String getNombre();
    String getContenido();
    Integer getPrecio();


}
