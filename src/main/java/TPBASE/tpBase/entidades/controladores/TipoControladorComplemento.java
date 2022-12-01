package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.productos.AreaPersonalizacion;
import TPBASE.tpBase.entidades.productos.TipoPersonalizacion;
import TPBASE.tpBase.entidades.repositorios.AreaPersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.TipoPersonalizacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;


@RepositoryRestController
public class TipoControladorComplemento {

    @Autowired
    TipoPersonalizacionRepositorio repo;

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

