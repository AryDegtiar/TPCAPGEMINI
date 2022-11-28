package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.productos.AreaPersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaPersonalizacionRepositorio extends JpaRepository<AreaPersonalizacion, Integer> {

}
