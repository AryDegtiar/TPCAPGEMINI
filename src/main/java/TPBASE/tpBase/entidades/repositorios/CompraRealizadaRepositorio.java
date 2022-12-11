package TPBASE.tpBase.entidades.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import TPBASE.tpBase.entidades.compras.CompraRealizada;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRealizadaRepositorio extends JpaRepository<CompraRealizada, Integer> {

}
