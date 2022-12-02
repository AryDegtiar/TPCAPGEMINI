package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.dto.getter.PosiblePersonalizacionDTOgetter;
import TPBASE.tpBase.entidades.productos.AreaPersonalizacion;
import TPBASE.tpBase.entidades.productos.PosiblePersonalizacion;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@JsonPropertyOrder({"id", "activo", "areaPersonalizacion", "tipoPersonalizacion"})
@RepositoryRestResource(path = "posiblepersonalizacion", excerptProjection = PosiblePersonalizacionDTOgetter.class)
public interface PosiblePersonalizacionRepositorio extends JpaRepository<PosiblePersonalizacion, Integer> {


    List<PosiblePersonalizacion> findAllByAreaPersonalizacion(AreaPersonalizacion areaPersonalizacion);

    boolean existsByAreaPersonalizacion(AreaPersonalizacion area);

    boolean existsByAreaPersonalizacionId(Integer areaID);
}
