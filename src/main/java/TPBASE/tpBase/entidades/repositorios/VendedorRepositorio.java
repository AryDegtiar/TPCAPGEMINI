package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.modelos.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepositorio extends JpaRepository<Vendedor, Integer> {
}
