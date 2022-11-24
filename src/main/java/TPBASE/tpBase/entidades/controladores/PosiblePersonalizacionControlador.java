package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.productos.Categoria;
import TPBASE.tpBase.entidades.productos.PosiblePersonalizacion;
import TPBASE.tpBase.entidades.repositorios.CategoriaRepositorio;
import TPBASE.tpBase.entidades.repositorios.PosiblePersonalizacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posiblePerso")
public class PosiblePersonalizacionControlador {

    @Autowired
    PosiblePersonalizacionRepositorio posiblePersonalizacionRepositorio;

    @GetMapping(path = {"","/"})
    List<PosiblePersonalizacion> posiblesPersonalizaciones(){
        return posiblePersonalizacionRepositorio.findAll();
    }

    @GetMapping(path = {"/{posiblePersonalizacionID}"} )
    PosiblePersonalizacion obtenerPosiblePersonalizacion(@PathVariable("posiblePersonalizacionID") Integer posiblePersonalizacionID){
        Optional<PosiblePersonalizacion> posiblePersonalizacion = posiblePersonalizacionRepositorio.findById(posiblePersonalizacionID);
        if (posiblePersonalizacion.isPresent()){
            return posiblePersonalizacion.get();
        }else{
            return null;
        }
    }

    @DeleteMapping(path = {"/{posiblePersonalizacionID}"})
    String eliminarPosiblePersonalizacionID(@PathVariable("posiblePersonalizacionID") Integer posiblePersonalizacionID){
        posiblePersonalizacionRepositorio.deleteById(posiblePersonalizacionID);
        return "Se elimino correctamente la posible personalizacion con ID: " + (posiblePersonalizacionID).toString();
    }

    @PostMapping(path = {"", "/"})
    PosiblePersonalizacion agregarPosiblePersonalizacion(@RequestBody PosiblePersonalizacion posiblePersonalizacion){
        return posiblePersonalizacionRepositorio.save(posiblePersonalizacion);
    }

}
