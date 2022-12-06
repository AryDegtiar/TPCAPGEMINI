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
import TPBASE.tpBase.entidades.repositorios.TipoPersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.VendedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import static org.springframework.http.HttpStatus.*;

@RepositoryRestController
public class PersonalizacionControladorComplemento {

    @Autowired
    PersonalizacionRepositorio repo;
    @Autowired
    PosiblePersonalizacionRepositorio posiblePersoRepo;
    @Autowired
    private TipoPersonalizacionRepositorio tipoPersonalizacionRepositorio;

    @PostMapping(path = "/personalizacion")
    public @ResponseBody ResponseEntity<?> agregarPersonalizacion(@RequestBody PersonalizacionDTOsetter personalizacionDTOsetter) {
        try {
            if (posiblePersoRepo.existsById(personalizacionDTOsetter.getPosiblePersonalizacionId())) {
                PosiblePersonalizacion posiblePersonalizacion = posiblePersoRepo.findById(personalizacionDTOsetter.getPosiblePersonalizacionId()).get();
                Personalizacion personalizacion = new Personalizacion();
                personalizacion.setPosiblePersonalizacion(posiblePersonalizacion);
                personalizacion.setNombre(personalizacionDTOsetter.getNombre());
                personalizacion.setContenido(personalizacionDTOsetter.getContenido());
                personalizacion.setPrecio(personalizacionDTOsetter.getPrecio());

                repo.save(personalizacion);
                return new ResponseEntity<>(personalizacion, OK);
            } else {
                return new ResponseEntity<>("No existe la posible personalizacion", NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>("No se pudo crear la personalizacion, campos invalidos", INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(path = {"/personalizacion/{personalizacionID}"})
    @Transactional
    public @ResponseBody ResponseEntity<Personalizacion> darDeBajaArea(@PathVariable("personalizacionID") Integer personalizacionID){
        if (repo.existsById(personalizacionID)){
            Personalizacion personalizacion = repo.findById(personalizacionID).get();
            personalizacion.setActivo(false);
            return new ResponseEntity<Personalizacion>(personalizacion, OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}