package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.productos.PosiblePersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosiblePersonalizacionRepositorio extends JpaRepository<PosiblePersonalizacion, Integer> {

}
