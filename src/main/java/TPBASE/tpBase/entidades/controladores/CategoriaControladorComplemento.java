package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.productos.Categoria;
import TPBASE.tpBase.entidades.repositorios.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;

@RepositoryRestController
public class CategoriaControladorComplemento {

    @Autowired
    CategoriaRepositorio repo;

    @DeleteMapping(path = {"/categoria/{categoriaID}"})
    @Transactional
    public @ResponseBody ResponseEntity<Categoria> darDeBajaArea(@PathVariable("categoriaID") Integer categoriaID){
        if (repo.existsById(categoriaID)){
            Categoria categoria = repo.findById(categoriaID).get();
            categoria.setActivo(false);
            return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}