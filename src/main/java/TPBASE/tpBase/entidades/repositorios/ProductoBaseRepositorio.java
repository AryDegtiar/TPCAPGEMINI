package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.productos.ProductoBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "productobase")
public interface ProductoBaseRepositorio extends JpaRepository<ProductoBase, Integer> {
}
