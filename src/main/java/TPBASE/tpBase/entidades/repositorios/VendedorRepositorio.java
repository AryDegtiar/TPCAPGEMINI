package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.actores.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "vendedor")
public interface VendedorRepositorio extends JpaRepository<Vendedor, Integer> {
}
