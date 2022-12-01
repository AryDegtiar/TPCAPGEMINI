package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.productos.Personalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "personalizacion")
public interface PersonalizacionRepositorio extends JpaRepository<Personalizacion, Integer> {
}
