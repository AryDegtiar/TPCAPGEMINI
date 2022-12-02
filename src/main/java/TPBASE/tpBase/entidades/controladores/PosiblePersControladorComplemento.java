package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.dto.setter.PosiblePersonalizacionDTOsetter;
import TPBASE.tpBase.entidades.productos.AreaPersonalizacion;
import TPBASE.tpBase.entidades.productos.PosiblePersonalizacion;
import TPBASE.tpBase.entidades.productos.TipoPersonalizacion;
import TPBASE.tpBase.entidades.repositorios.AreaPersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.PosiblePersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.TipoPersonalizacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;


@RepositoryRestController
public class PosiblePersControladorComplemento {

    @Autowired
    PosiblePersonalizacionRepositorio repo;
    @Autowired
    AreaPersonalizacionRepositorio areaRepo;
    @Autowired
    TipoPersonalizacionRepositorio tipoRepo;

    @PostMapping(path = "/posiblepersonalizacion")
    public @ResponseBody ResponseEntity<PosiblePersonalizacion> agregarPosiblePersonalizacion(@RequestBody PosiblePersonalizacionDTOsetter posiblePersonalizacionDTOsetter){
        AreaPersonalizacion area = areaRepo.findById(posiblePersonalizacionDTOsetter.getAreaPersonalizacionId()).get();
        TipoPersonalizacion tipo = tipoRepo.findById(posiblePersonalizacionDTOsetter.getTipoPersonalizacionId()).get();

        PosiblePersonalizacion posiblePersonalizacion = new PosiblePersonalizacion();
        posiblePersonalizacion.setAreaPersonalizacion(area);
        posiblePersonalizacion.setTipoPersonalizacion(tipo);

        repo.save(posiblePersonalizacion);
        return new ResponseEntity<PosiblePersonalizacion>(posiblePersonalizacion, HttpStatus.OK);
    }

    @DeleteMapping(path = {"/posiblepersonalizacion/{posiblePersoID}"})
    @Transactional
    public @ResponseBody ResponseEntity<PosiblePersonalizacion> darDeBajaArea(@PathVariable("posiblePersoID") Integer posiblePersoID){
        if (repo.existsById(posiblePersoID)){
            PosiblePersonalizacion posiblePersonalizacion = repo.findById(posiblePersoID).get();
            posiblePersonalizacion.setActivo(false);
            return new ResponseEntity<PosiblePersonalizacion>(posiblePersonalizacion, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}