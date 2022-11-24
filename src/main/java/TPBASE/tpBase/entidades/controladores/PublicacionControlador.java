package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.modelos.Vendedor;
import TPBASE.tpBase.entidades.productos.Publicacion;
import TPBASE.tpBase.entidades.repositorios.PublicacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.VendedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publicacion")
public class PublicacionControlador {

    @Autowired
    PublicacionRepositorio publicacionRepositorio;


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
    Publicacion agregaripoPublicacion(@RequestBody Publicacion publicacion){
        return publicacionRepositorio.save(publicacion);
    }

}