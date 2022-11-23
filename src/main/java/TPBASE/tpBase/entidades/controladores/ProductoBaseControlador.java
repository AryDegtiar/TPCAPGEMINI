package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.productos.AreaPersonalizacion;
import TPBASE.tpBase.entidades.productos.ProductoBase;
import TPBASE.tpBase.entidades.repositorios.AreaPersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.ProductoBaseRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productoBase")
public class ProductoBaseControlador {

    @Autowired
    ProductoBaseRepositorio productoBaseRepositorio;


    @GetMapping(path = {"","/"})
    List<ProductoBase> categorias(){
        return productoBaseRepositorio.findAll();
    }

    @GetMapping(path = {"/{prodBaseID}"} )
    ProductoBase obtenerAreaPersonalizacion(@PathVariable("prodBaseID") Integer prodBaseID){
        Optional<ProductoBase> prodBase = productoBaseRepositorio.findById(prodBaseID);
        if (prodBase.isPresent()){
            return prodBase.get();
        }else{
            return null;
        }

    }

    @PostMapping(path = {"", "/"})
    ProductoBase agregarCategoria(@RequestBody ProductoBase productoBase){
        return productoBaseRepositorio.save(productoBase);
    }

}
