package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.productos.AreaPersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "areapersonalizacion")
public interface AreaPersonalizacionRepositorio extends JpaRepository<AreaPersonalizacion, Integer> {

}
