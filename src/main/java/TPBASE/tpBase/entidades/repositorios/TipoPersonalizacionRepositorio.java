package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.productos.TipoPersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPersonalizacionRepositorio extends JpaRepository<TipoPersonalizacion, Integer> {
    boolean existsByTipo(String nomArea);

    TipoPersonalizacion findByTipo(String nomTipo);
}
