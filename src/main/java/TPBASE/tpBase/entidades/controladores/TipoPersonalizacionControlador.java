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
    TipoPersonalizacion obtenerAreaPersonalizacion(@PathVariable("areaID") Integer tipoID){
        Optional<TipoPersonalizacion> tipo = tipoPersonalizacionRepositorio.findById(tipoID);
        if (tipo.isPresent()){
            return tipo.get();
        }else{
            return null;
        }

    }

    @PostMapping(path = {"", "/"})
    TipoPersonalizacion agregarCategoria(@RequestBody TipoPersonalizacion tipo){
        return tipoPersonalizacionRepositorio.save(tipo);
    }

}
