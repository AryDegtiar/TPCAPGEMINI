package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.productos.Categoria;
import TPBASE.tpBase.entidades.repositorios.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestController
public class CategoriaControladorComplemento {

    @Autowired
    CategoriaRepositorio repo;

    @PostMapping(path = "/categoria")
    public @ResponseBody ResponseEntity<?> agregarCategoria(@RequestBody Categoria categoria){
        try {
            Categoria categoria1 = repo.save(categoria);
            return new ResponseEntity<>(categoria1, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("No se pudo crear la categoria, campos invalidos", HttpStatus.BAD_REQUEST);
        }
    }

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