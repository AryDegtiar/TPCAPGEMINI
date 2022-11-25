package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.productos.Personalizacion;
import TPBASE.tpBase.entidades.repositorios.PersonalizacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personalizacion")
public class PersonalizacionControlador {

    @Autowired
    PersonalizacionRepositorio personalizacionRepositorio;


    @GetMapping(path = {"","/"})
    List<Personalizacion> personalizaciones(){
        return personalizacionRepositorio.findAll();
    }

    @GetMapping(path = {"/{personalizacionID}"} )
    Personalizacion obtenerPersonalizacionID(@PathVariable("personalizacionID") Integer personalizacionID){
        Optional<Personalizacion> personalizacion = personalizacionRepositorio.findById(personalizacionID);
        if (personalizacion.isPresent()){
            return personalizacion.get();
        }else{
            return null;
        }
    }

    @DeleteMapping(path = {"/{personalizacionID}"})
    String eliminarPersonalizacionID(@PathVariable("personalizacionID") Integer personalizacionID){
        personalizacionRepositorio.deleteById(personalizacionID);
        return "Se elimino correctamente la personalizacion con ID: " + (personalizacionID).toString();
    }

    @PostMapping(path = {"", "/"})
    Personalizacion agregaripoPersonalizacion(@RequestBody Personalizacion personalizacion){
        return personalizacionRepositorio.save(personalizacion);
    }

}