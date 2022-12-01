package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.productos.TipoPersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "tipopersonalizacion")
public interface TipoPersonalizacionRepositorio extends JpaRepository<TipoPersonalizacion, Integer> {
}
