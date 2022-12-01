package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.actores.Vendedor;
import TPBASE.tpBase.entidades.productos.Personalizacion;
import TPBASE.tpBase.entidades.repositorios.PersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.VendedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;

@RepositoryRestController
public class PersonalizacionControladorComplemento {

    @Autowired
    PersonalizacionRepositorio repo;

    @DeleteMapping(path = {"/personalizacion/{personalizacionID}"})
    @Transactional
    public @ResponseBody ResponseEntity<Personalizacion> darDeBajaArea(@PathVariable("personalizacionID") Integer personalizacionID){
        if (repo.existsById(personalizacionID)){
            Personalizacion personalizacion = repo.findById(personalizacionID).get();
            personalizacion.setActivo(false);
            return new ResponseEntity<Personalizacion>(personalizacion, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}