package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.productos.Personalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalizacionRepositorio extends JpaRepository<Personalizacion, Integer> {
}
