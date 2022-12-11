package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.actores.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cliente")
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {

}
