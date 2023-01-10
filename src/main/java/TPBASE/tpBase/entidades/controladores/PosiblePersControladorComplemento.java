package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.dto.setter.PosiblePersonalizacionDTOsetter;
import TPBASE.tpBase.entidades.dto.setterSoloID.AreaPersonalizacionDTOsetterID;
import TPBASE.tpBase.entidades.dto.setterSoloID.TipoPersonalizacionDTOsetterID;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RepositoryRestController
public class PosiblePersControladorComplemento {

    @Autowired
    PosiblePersonalizacionRepositorio repo;
    @Autowired
    AreaPersonalizacionRepositorio areaRepo;
    @Autowired
    TipoPersonalizacionRepositorio tipoRepo;

    @PostMapping(path = "/posiblepersonalizacion")
    public @ResponseBody ResponseEntity<?> agregarPosiblePersonalizacion(@RequestBody PosiblePersonalizacionDTOsetter posiblePersonalizacionDTOsetter){
        try {
            if (areaRepo.existsById(posiblePersonalizacionDTOsetter.getAreaPersonalizacionId()) && tipoRepo.existsById(posiblePersonalizacionDTOsetter.getTipoPersonalizacionId())) {
                AreaPersonalizacion area = areaRepo.findById(posiblePersonalizacionDTOsetter.getAreaPersonalizacionId()).get();
                TipoPersonalizacion tipo = tipoRepo.findById(posiblePersonalizacionDTOsetter.getTipoPersonalizacionId()).get();

                PosiblePersonalizacion posiblePersonalizacion = new PosiblePersonalizacion();
                posiblePersonalizacion.setAreaPersonalizacion(area);
                posiblePersonalizacion.setTipoPersonalizacion(tipo);

                repo.save(posiblePersonalizacion);
                return new ResponseEntity<>(posiblePersonalizacion, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se pudo crear la posible personalizacion, area o tipo inexistentes", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("No se pudo crear la posible personalizacion, campos invalidos", HttpStatus.BAD_REQUEST);
        }
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

    //PATCH

    @PatchMapping("/posiblepersonalizacion/{posiblePersoID}/areaPersonalizacion")
    public @ResponseBody ResponseEntity<?> actualizarArea(@PathVariable("posiblePersoID") Integer posiblePersoID, @RequestBody AreaPersonalizacionDTOsetterID areaPersonalizacionDTOsetterID, BindingResult bindingResult){
        try{
            if (repo.existsById(posiblePersoID)){
                if (areaRepo.existsById(areaPersonalizacionDTOsetterID.getAreaPersonalizacionId())) {
                    PosiblePersonalizacion posiblePersonalizacion = repo.findById(posiblePersoID).get();
                    AreaPersonalizacion areaPersonalizacion = areaRepo.findById(areaPersonalizacionDTOsetterID.getAreaPersonalizacionId()).get();
                    posiblePersonalizacion.setAreaPersonalizacion(areaPersonalizacion);
                    posiblePersonalizacion = repo.save(posiblePersonalizacion);
                    return new ResponseEntity<>(posiblePersonalizacion, HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("No se pudo modificar la posible personalizacion, area personalizacion inexistente", HttpStatus.BAD_REQUEST);
                }
            }else{
                return new ResponseEntity<>("No se pudo modificar la posible personalizacion, area personalizacion inexistentes", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("No se pudo modificar la posible personalizacion, campos invalidos", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/posiblepersonalizacion/{posiblePersoID}/tipoPersonalizacion")
    public @ResponseBody ResponseEntity<?> modificarArea(@PathVariable("posiblePersoID") Integer posiblePersoID, @RequestBody TipoPersonalizacionDTOsetterID tipoPersonalizacionDTOsetterID){
        try{
            if (repo.existsById(posiblePersoID)){
                if (tipoRepo.existsById(tipoPersonalizacionDTOsetterID.getTipoPersonalizacionId())) {
                    PosiblePersonalizacion posiblePersonalizacion = repo.findById(posiblePersoID).get();
                    TipoPersonalizacion tipoPersonalizacion = tipoRepo.findById(tipoPersonalizacionDTOsetterID.getTipoPersonalizacionId()).get();
                    posiblePersonalizacion.setTipoPersonalizacion(tipoPersonalizacion);
                    posiblePersonalizacion = repo.save(posiblePersonalizacion);
                    return new ResponseEntity<>(posiblePersonalizacion, HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("No se pudo modificar la posible personalizacion, tipo personalizacion inexistente", HttpStatus.BAD_REQUEST);
                }
            }else{
                return new ResponseEntity<>("No se pudo modificar la posible personalizacion, tipo personalizacion inexistentes", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("No se pudo modificar la posible personalizacion, campos invalidos", HttpStatus.BAD_REQUEST);
        }
    }

}