package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.productos.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, Integer> {

}
