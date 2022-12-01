package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.metodosPagos.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "metodopago")
public interface MetodoPagoRepositorio extends JpaRepository<MetodoPago, Integer> {
}
