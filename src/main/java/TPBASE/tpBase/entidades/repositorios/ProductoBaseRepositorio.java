package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.dto.getter.ProductoBaseDTOgetter;
import TPBASE.tpBase.entidades.productos.ProductoBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "productobase", excerptProjection = ProductoBaseDTOgetter.class)
public interface ProductoBaseRepositorio extends JpaRepository<ProductoBase, Integer> {
}
