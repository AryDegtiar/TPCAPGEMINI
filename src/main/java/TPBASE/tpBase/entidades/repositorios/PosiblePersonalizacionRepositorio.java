package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.productos.AreaPersonalizacion;
import TPBASE.tpBase.entidades.productos.PosiblePersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "posiblepersonalizacion")
public interface PosiblePersonalizacionRepositorio extends JpaRepository<PosiblePersonalizacion, Integer> {


    List<PosiblePersonalizacion> findAllByAreaPersonalizacion(AreaPersonalizacion areaPersonalizacion);

    boolean existsByAreaPersonalizacion(AreaPersonalizacion area);

    boolean existsByAreaPersonalizacionId(Integer areaID);
}
