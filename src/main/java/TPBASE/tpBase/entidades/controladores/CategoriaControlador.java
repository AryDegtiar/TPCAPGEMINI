package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.productos.Categoria;
import TPBASE.tpBase.entidades.repositorios.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaControlador {

    @Autowired
    CategoriaRepositorio categoriaRepositorio;

    @GetMapping(path = {"","/"})
    List<Categoria> categorias(){
        return categoriaRepositorio.findAll();
    }

    @PostMapping(path = {"", "/"})
    Categoria agregarCategoria(@RequestBody Categoria categoria){
        return categoriaRepositorio.save(categoria);
    }

}
