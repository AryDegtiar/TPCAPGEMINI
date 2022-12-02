package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.actores.Vendedor;
import TPBASE.tpBase.entidades.dto.VendedorDTOgetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "vendedor", excerptProjection = VendedorDTOgetter.class)
public interface VendedorRepositorio extends JpaRepository<Vendedor, Integer> {
}
