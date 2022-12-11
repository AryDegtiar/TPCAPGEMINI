package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.compras.CantidadXProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CantidadXProductoRepositorio extends JpaRepository<CantidadXProducto, Integer> {

}

