package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.actores.Vendedor;
import TPBASE.tpBase.entidades.dto.setter.PersonalizacionDTOsetter;
import TPBASE.tpBase.entidades.dto.setter.PosiblePersonalizacionDTOsetter;
import TPBASE.tpBase.entidades.productos.AreaPersonalizacion;
import TPBASE.tpBase.entidades.productos.Personalizacion;
import TPBASE.tpBase.entidades.productos.PosiblePersonalizacion;
import TPBASE.tpBase.entidades.productos.TipoPersonalizacion;
import TPBASE.tpBase.entidades.repositorios.PersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.PosiblePersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.VendedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RepositoryRestController
public class PersonalizacionControladorComplemento {

    @Autowired
    PersonalizacionRepositorio repo;
    @Autowired
    PosiblePersonalizacionRepositorio posiblePersoRepo;

    @PostMapping(path = "/personalizacion")
    public @ResponseBody ResponseEntity<Personalizacion> agregarPersonalizacion(@RequestBody PersonalizacionDTOsetter personalizacionDTOsetter){
        PosiblePersonalizacion posiblePersonalizacion = posiblePersoRepo.findById(personalizacionDTOsetter.getPosiblePersonalizacionId()).get();

        Personalizacion personalizacion = new Personalizacion();
        personalizacion.setPosiblePersonalizacion(posiblePersonalizacion);
        personalizacion.setNombre(personalizacionDTOsetter.getNombre());
        personalizacion.setContenido(personalizacionDTOsetter.getContenido());
        personalizacion.setPrecio(personalizacionDTOsetter.getPrecio());

        repo.save(personalizacion);
        return new ResponseEntity<Personalizacion>(personalizacion, HttpStatus.OK);
    }


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