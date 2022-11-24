package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.productos.AreaPersonalizacion;
import TPBASE.tpBase.entidades.productos.Categoria;
import TPBASE.tpBase.entidades.repositorios.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaControlador {

    @Autowired
    CategoriaRepositorio categoriaRepositorio;

    @GetMapping(path = {"","/"})
    List<Categoria> categorias(){
        return categoriaRepositorio.findAll();
    }

    @GetMapping(path = {"/{categoriaID}"} )
    Categoria obtenerCategorias(@PathVariable("categoriaID") Integer categoriaID){
        Optional<Categoria> categoria = categoriaRepositorio.findById(categoriaID);
        if (categoria.isPresent()){
            return categoria.get();
        }else{
            return null;
        }
    }

    @DeleteMapping(path = {"/{categoriaID}"})
    String eliminarCategoriaID(@PathVariable("categoriaID") Integer categoriaID){
        categoriaRepositorio.deleteById(categoriaID);
        return "Se elimino correctamente la categoria con ID: " + (categoriaID).toString();
    }

    @PostMapping(path = {"", "/"})
    Categoria agregarCategoria(@RequestBody Categoria categoria){
        return categoriaRepositorio.save(categoria);
    }

}
