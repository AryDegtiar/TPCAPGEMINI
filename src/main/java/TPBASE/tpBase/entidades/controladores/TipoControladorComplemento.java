package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.productos.AreaPersonalizacion;
import TPBASE.tpBase.entidades.productos.TipoPersonalizacion;
import TPBASE.tpBase.entidades.repositorios.AreaPersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.TipoPersonalizacionRepositorio;
import com.fasterxml.jackson.annotation.JacksonInject;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RepositoryRestController
public class TipoControladorComplemento {

    @Autowired
    TipoPersonalizacionRepositorio repo;

    @PostMapping(path = "/tipopersonalizacion")
    public @ResponseBody ResponseEntity<?> agregarTipoPersonalizacion(@RequestBody TipoPersonalizacion tipoPersonalizacion) {
       try {
            TipoPersonalizacion tipoPersonalizacion1 = repo.save(tipoPersonalizacion);
            return new ResponseEntity<>(tipoPersonalizacion1,HttpStatus.CREATED);
       }catch (Exception e){
           return new ResponseEntity<>("Error, campo de tipo personalizacion invalido",HttpStatus.BAD_REQUEST);
       }
    }

    @DeleteMapping(path = {"/tipopersonalizacion/{tipoID}"})
    @Transactional
    public @ResponseBody ResponseEntity<TipoPersonalizacion> darDeBajaArea(@PathVariable("tipoID") Integer tipoID){
        if (repo.existsById(tipoID)){
            TipoPersonalizacion tipo = repo.findById(tipoID).get();
            tipo.setActivo(false);
            return new ResponseEntity<TipoPersonalizacion>(tipo, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}

