package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.metodosPagos.MetodoPago;
import TPBASE.tpBase.entidades.modelos.Vendedor;
import TPBASE.tpBase.entidades.repositorios.MetodoPagoRepositorio;
import TPBASE.tpBase.entidades.repositorios.VendedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/metodopago")
public class MetodoPagoControlador {

    @Autowired
    MetodoPagoRepositorio metodoPagoRepositorio;


    @GetMapping(path = {"","/"})
    List<MetodoPago> metodosPagos(){
        return metodoPagoRepositorio.findAll();
    }

    @GetMapping(path = {"/{metodopagoID}"} )
    MetodoPago obtenerMetodoPagoID(@PathVariable("metodopagoID") Integer metodopagoID){
        Optional<MetodoPago> metodopago = metodoPagoRepositorio.findById(metodopagoID);
        if (metodopago.isPresent()){
            return metodopago.get();
        }else{
            return null;
        }
    }

    @DeleteMapping(path = {"/{metodopagoID}"})
    String eliminarMetodoPagoID(@PathVariable("metodopagoID") Integer metodopagoID){
        metodoPagoRepositorio.deleteById(metodopagoID);
        return "Se elimino correctamente el metodo de pago con ID: " + (metodopagoID).toString();
    }

    @PostMapping(path = {"", "/"})
    MetodoPago agregaripoVendedor(@RequestBody MetodoPago metodopago){
        return metodoPagoRepositorio.save(metodopago);
    }

}