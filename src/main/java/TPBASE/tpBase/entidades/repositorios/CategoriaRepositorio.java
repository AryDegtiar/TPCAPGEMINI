package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.productos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Integer> {

}
