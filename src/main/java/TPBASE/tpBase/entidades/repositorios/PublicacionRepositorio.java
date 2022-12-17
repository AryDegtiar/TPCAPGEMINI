package TPBASE.tpBase.entidades.repositorios;

import TPBASE.tpBase.entidades.dto.getter.PublicacionDTOgetter;
import TPBASE.tpBase.entidades.productos.Publicacion;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@JsonPropertyOrder({"id","activo","estadoPublicacion","vendedor","precioTotal","productoBase","personalizaciones","productoBase"})
@RepositoryRestResource(path = "publicacion", excerptProjection = PublicacionDTOgetter.class)
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
