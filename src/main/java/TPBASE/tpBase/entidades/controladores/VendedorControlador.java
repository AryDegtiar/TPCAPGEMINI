package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.modelos.Vendedor;
import TPBASE.tpBase.entidades.productos.TipoPersonalizacion;
import TPBASE.tpBase.entidades.repositorios.TipoPersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.VendedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendedor")
public class VendedorControlador {

    @Autowired
    VendedorRepositorio vendedorRepositorio;


    @GetMapping(path = {"","/"})
    List<Vendedor> vendedores(){
        return vendedorRepositorio.findAll();
    }

    @GetMapping(path = {"/{vendedorID}"} )
    Vendedor obtenerVendedorID(@PathVariable("vendedorID") Integer vendedorID){
        Optional<Vendedor> vendedor = vendedorRepositorio.findById(vendedorID);
        if (vendedor.isPresent()){
            return vendedor.get();
        }else{
            return null;
        }
    }

    @DeleteMapping(path = {"/{vendedorID}"})
    String eliminarVendedorID(@PathVariable("vendedorID") Integer vendedorID){
        vendedorRepositorio.deleteById(vendedorID);
        return "Se elimino correctamente el vendedor con ID: " + (vendedorID).toString();
    }

    @PostMapping(path = {"", "/"})
    Vendedor agregaripoVendedor(@RequestBody Vendedor vendedor){
        return vendedorRepositorio.save(vendedor);
    }

}