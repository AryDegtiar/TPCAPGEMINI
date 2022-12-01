package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.productos.Publicacion;
import TPBASE.tpBase.entidades.repositorios.PublicacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;

@RepositoryRestController
public class PublicacionControladorComplemento {

    @Autowired
    PublicacionRepositorio repo;

    @DeleteMapping(path = {"/publicacion/{publicacionID}"})
    @Transactional
    public @ResponseBody ResponseEntity<Publicacion> darDeBajaArea(@PathVariable("publicacionID") Integer publicacionID){
        if (repo.existsById(publicacionID)){
            Publicacion posiblePersonalizacion = repo.findById(publicacionID).get();
            posiblePersonalizacion.setActivo(false);
            return new ResponseEntity<Publicacion>(posiblePersonalizacion, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}