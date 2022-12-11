package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.productos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "categoria")
public interface CategoriaRepositorio extends JpaRepository<Categoria, Integer> {

}


