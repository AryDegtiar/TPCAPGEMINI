package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.productos.AreaPersonalizacion;
import TPBASE.tpBase.entidades.repositorios.AreaPersonalizacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/area")
public class AreaPersonalizacionControlador {

    @Autowired
    AreaPersonalizacionRepositorio areaPersonalizacionRepositorio;


    @GetMapping(path = {"","/"})
    List<AreaPersonalizacion> areasPersonalizaciones(){
        return areaPersonalizacionRepositorio.findAll();
    }

    @GetMapping(path = {"/{areaID}"} )
    AreaPersonalizacion obtenerAreaPersonalizacion(@PathVariable("areaID") Integer areaID){
        Optional<AreaPersonalizacion> area = areaPersonalizacionRepositorio.findById(areaID);
        if (area.isPresent()){
            return area.get();
        }else{
            return null;
        }
    }

    @DeleteMapping(path = {"/{areaID}"})
    String eliminarAreaPersonalizacionID(@PathVariable("areaID") Integer areaID){
        areaPersonalizacionRepositorio.deleteById(areaID);
        return "Se elimino correctamente el area personaliazda con ID: " + (areaID).toString();
    }

    @PostMapping(path = {"", "/"})
    AreaPersonalizacion agregarAreaPersonalizacion(@RequestBody AreaPersonalizacion areaPersonalizacion){
        return areaPersonalizacionRepositorio.save(areaPersonalizacion);
    }

}
