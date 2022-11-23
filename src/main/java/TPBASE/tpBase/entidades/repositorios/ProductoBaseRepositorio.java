package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.productos.ProductoBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoBaseRepositorio extends JpaRepository<ProductoBase, Integer> {
}
