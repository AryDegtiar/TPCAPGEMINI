package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.productos.AreaPersonalizacion;
import TPBASE.tpBase.entidades.productos.PosiblePersonalizacion;
import TPBASE.tpBase.entidades.productos.TipoPersonalizacion;
import TPBASE.tpBase.entidades.repositorios.AreaPersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.PosiblePersonalizacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RepositoryRestController
public class AreaControladorComplemento {

    @Autowired
    AreaPersonalizacionRepositorio areaPersonalizacionRepositorio;

    @Autowired
    PosiblePersonalizacionRepositorio posiblePersonalizacionRepositorio;

    @PostMapping(path = "/areapersonalizacion")
    public @ResponseBody ResponseEntity<?> agregarAreaPersonalizacion(@RequestBody AreaPersonalizacion areaPersonalizacion) {
        try {
            AreaPersonalizacion areaPersonalizacion1 = areaPersonalizacionRepositorio.save(areaPersonalizacion);
            return new ResponseEntity<>(areaPersonalizacion1,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error, campo de area personalizacion invalido",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = {"/areapersonalizacion/{areaID}"})
    @Transactional
    public @ResponseBody ResponseEntity<AreaPersonalizacion> darDeBajaArea(@PathVariable("areaID") Integer areaID){
        AreaPersonalizacion area;
        if (areaPersonalizacionRepositorio.existsById(areaID)){
            area = areaPersonalizacionRepositorio.findById(areaID).get();
            area.setActivo(false);
            return new ResponseEntity<AreaPersonalizacion>(area, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }

        // modificar todas lass posibles personalizaciones que tengan a el area anulada para que aparezca como inactivo
        //boolean b = posiblePersonalizacionRepositorio.existsByAreaPersonalizacionId(areaID);
        //boolean b = posiblePersonalizacionRepositorio.existsByAreaPersonalizacion(area);
        //List<PosiblePersonalizacion> p = posiblePersonalizacionRepositorio.findAllByAreaPersonalizacion(area);
    }

}

