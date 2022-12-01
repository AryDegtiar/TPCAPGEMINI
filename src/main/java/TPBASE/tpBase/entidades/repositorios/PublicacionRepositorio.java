package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.productos.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.concurrent.atomic.AtomicReference;

@RepositoryRestResource(path = "publicacion")
public interface PublicacionRepositorio extends JpaRepository<Publicacion, Integer> {
    /*
    public static Integer obtenerPrecioTotal(Publicacion publicacion){
        AtomicReference<Integer> total = new AtomicReference<>(0);
        publicacion.getPersonalizaciones().forEach( personalizacion -> total.set( total.get() + personalizacion.getPrecio() ) );
        System.out.println(total.get());
        return total.get();
    }

     */
}
