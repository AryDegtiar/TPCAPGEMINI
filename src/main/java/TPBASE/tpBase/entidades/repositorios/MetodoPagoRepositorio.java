package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.metodosPagos.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoPagoRepositorio extends JpaRepository<MetodoPago, Integer> {
}
