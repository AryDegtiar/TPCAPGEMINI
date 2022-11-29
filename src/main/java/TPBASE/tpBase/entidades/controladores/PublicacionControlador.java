package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.productos.Personalizacion;
import TPBASE.tpBase.entidades.productos.ProductoBase;
import TPBASE.tpBase.entidades.productos.Publicacion;
import TPBASE.tpBase.entidades.repositorios.PersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.ProductoBaseRepositorio;
import TPBASE.tpBase.entidades.repositorios.PublicacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publicacion")
public class PublicacionControlador {

    @Autowired
    PublicacionRepositorio publicacionRepositorio;

    @Autowired
    ProductoBaseRepositorio productoBaseRepositorio;
    @Autowired
    PersonalizacionRepositorio personalizacionRepositorio;

    @GetMapping(path = {"","/"})
    List<Publicacion> vendedores(){
        return publicacionRepositorio.findAll();
    }

    @GetMapping(path = {"/{PublicacionID}"} )
    Publicacion obtenerPublicacionIDID(@PathVariable("PublicacionID") Integer publicacionID){
        Optional<Publicacion> publicacion = publicacionRepositorio.findById(publicacionID);
        if (publicacion.isPresent()){
            return publicacion.get();
        }else{
            return null;
        }
    }

    @DeleteMapping(path = {"/{PublicacionID}"})
    String eliminarPublicacionID(@PathVariable("PublicacionID") Integer publicacionID){
        publicacionRepositorio.deleteById(publicacionID);
        return "Se elimino correctamente la publicacion con ID: " + (publicacionID).toString();
    }

    @PostMapping(path = {"", "/"})
    Publicacion agregaripoPublicacion(@RequestBody Publicacion publicacion) {

        ProductoBase productoBase = productoBaseRepositorio.findById(publicacion.getProductoBase().getProdbase_id()).get();
        Integer precioTotal = productoBase.getPrecioBase();

        for (Personalizacion p : publicacion.getPersonalizaciones()){
            Personalizacion personalizacion = personalizacionRepositorio.findById(p.getPerso_id()).get();
            precioTotal = precioTotal + personalizacion.getPrecio();
        }
        publicacion.setPrecioTotal(precioTotal);


        return publicacionRepositorio.save(publicacion);
    }

}