package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.actores.Vendedor;
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
public class VendedorControladorComplemento {

    @Autowired
    VendedorRepositorio repo;

    @DeleteMapping(path = {"/vendedor/{vendedorID}"})
    @Transactional
    public @ResponseBody ResponseEntity<Vendedor> darDeBajaArea(@PathVariable("vendedorID") Integer vendedorID){
        if (repo.existsById(vendedorID)){
            Vendedor posiblePersonalizacion = repo.findById(vendedorID).get();
            posiblePersonalizacion.setActivo(false);
            return new ResponseEntity<Vendedor>(posiblePersonalizacion, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}