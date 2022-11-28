package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.productos.AreaPersonalizacion;
import TPBASE.tpBase.entidades.productos.Categoria;
import TPBASE.tpBase.entidades.productos.PosiblePersonalizacion;
import TPBASE.tpBase.entidades.productos.TipoPersonalizacion;
import TPBASE.tpBase.entidades.repositorios.AreaPersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.CategoriaRepositorio;
import TPBASE.tpBase.entidades.repositorios.PosiblePersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.TipoPersonalizacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posiblePerso")
public class PosiblePersonalizacionControlador {

    @Autowired
    PosiblePersonalizacionRepositorio posiblePersonalizacionRepositorio;

    @Autowired
    AreaPersonalizacionRepositorio areaPersonalizacionRepositorio;

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

        // obtengo el conetido del area
        String nomArea = posiblePersonalizacion.getAreaPersonalizacion().getArea();
        // obtengo el area con la descripcion
        areaPersonalizacionRepositorio.existsByArea(nomArea);
        AreaPersonalizacion area = areaPersonalizacionRepositorio.findByArea(nomArea);
        // me fijo si existe la descripcion de area
        if (area.getAreaperso_id() != null){
            // borro el area de posible personalizacion
            posiblePersonalizacion.setAreaPersonalizacion(null);
            // guardo rapido
            PosiblePersonalizacion posiblePersonalizacion2 = posiblePersonalizacionRepositorio.save(posiblePersonalizacion);
            // cambio el area que null por el area correspondiente
            posiblePersonalizacion2.setAreaPersonalizacion(area);
        }



        return posiblePersonalizacionRepositorio.save(posiblePersonalizacion);
    }

}
