package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.dto.setter.PublicacionDTOsetter;
import TPBASE.tpBase.entidades.productos.Personalizacion;
import TPBASE.tpBase.entidades.productos.PosiblePersonalizacion;
import TPBASE.tpBase.entidades.productos.ProductoBase;
import TPBASE.tpBase.entidades.productos.Publicacion;
import TPBASE.tpBase.entidades.repositorios.PersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.ProductoBaseRepositorio;
import TPBASE.tpBase.entidades.repositorios.PublicacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.VendedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RepositoryRestController
public class PublicacionControladorComplemento {

    @Autowired
    PublicacionRepositorio repo;
    @Autowired
    PersonalizacionRepositorio personalizacionRepo;
    @Autowired
    ProductoBaseRepositorio productoBaseRepo;
    @Autowired
    VendedorRepositorio vendedorRepo;

    @PostMapping(path = "/publicacion")
    public @ResponseBody ResponseEntity<Publicacion> agregarPublicacion(@RequestBody PublicacionDTOsetter publicacionDTOsetter) {

        // ROMPE LA PERSONALIZACIONES NOSE PORQUE

        List<Integer> personalizacionesId = publicacionDTOsetter.getPersonalizacionesId();
        List<Personalizacion> personalizaciones = new ArrayList<>();
        for(Integer personalizacionId : publicacionDTOsetter.getPersonalizacionesId()){
            System.out.println("ENTRE " + personalizacionId);
            Personalizacion personalizacion = personalizacionRepo.findById(personalizacionId).get();
            personalizaciones.add(personalizacion);
        }


        Publicacion publicacion = new Publicacion();
        publicacion.setEstadoPublicacion(publicacionDTOsetter.getEstadoPublicacion());
        publicacion.setProductoBase(productoBaseRepo.findById(publicacionDTOsetter.getProductoBaseId()).get());
        publicacion.setPersonalizaciones(personalizaciones);
        publicacion.setVendedor( vendedorRepo.findById(publicacionDTOsetter.getVendedorId()).get() );

        repo.save(publicacion);
        return new ResponseEntity<Publicacion>(publicacion, HttpStatus.OK);
    }

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