package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.productos.AreaPersonalizacion;
import TPBASE.tpBase.entidades.productos.TipoPersonalizacion;
import TPBASE.tpBase.entidades.repositorios.AreaPersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.TipoPersonalizacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipo")
public class TipoPersonalizacionControlador {

    @Autowired
    TipoPersonalizacionRepositorio tipoPersonalizacionRepositorio;


    @GetMapping(path = {"","/"})
    List<TipoPersonalizacion> categorias(){
        return tipoPersonalizacionRepositorio.findAll();
    }

    @GetMapping(path = {"/{tipoID}"} )
    TipoPersonalizacion obtenerAreaPersonalizacion(@PathVariable("tipoID") Integer tipoID){
        Optional<TipoPersonalizacion> tipo = tipoPersonalizacionRepositorio.findById(tipoID);
        if (tipo.isPresent()){
            return tipo.get();
        }else{
            return null;
        }
    }

    @DeleteMapping(path = {"/{tipoPersonalizacionID}"})
    String eliminarTipoPersonalizacionID(@PathVariable("tipoPersonalizacionID") Integer tipoPersonalizacionID){
        tipoPersonalizacionRepositorio.deleteById(tipoPersonalizacionID);
        return "Se elimino correctamente el tipo personalizacion con ID: " + (tipoPersonalizacionID).toString();
    }

    @PostMapping(path = {"", "/"})
    TipoPersonalizacion agregarCategoria(@RequestBody TipoPersonalizacion tipo){
        return tipoPersonalizacionRepositorio.save(tipo);
    }

}
