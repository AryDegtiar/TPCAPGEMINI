package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.actores.Vendedor;
import TPBASE.tpBase.entidades.metodosPagos.MetodoPago;
import TPBASE.tpBase.entidades.dto.setter.VendedorDTOsetter;
import TPBASE.tpBase.entidades.productos.PosiblePersonalizacion;
import TPBASE.tpBase.entidades.productos.TipoPersonalizacion;
import TPBASE.tpBase.entidades.repositorios.MetodoPagoRepositorio;
import TPBASE.tpBase.entidades.repositorios.VendedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RepositoryRestController
public class VendedorControladorComplemento {

    @Autowired
    VendedorRepositorio repo;
    @Autowired
    MetodoPagoRepositorio metodoPagoRepo;

    @PostMapping(path = "/vendedor")
    public @ResponseBody ResponseEntity<?> agregarPosiblePersonalizacion(@RequestBody VendedorDTOsetter vendedorDTOsetter) {
        try {
            boolean notFoundMetodoPago = true;
            List<MetodoPago> metodosPagos = new ArrayList<>();
            for(Integer metodoPagoId : vendedorDTOsetter.getMetodosPagosId()){
                if (metodoPagoRepo.existsById(metodoPagoId)) {
                    MetodoPago metodoPago = metodoPagoRepo.findById(metodoPagoId).get();
                    metodosPagos.add(metodoPago);
                    notFoundMetodoPago = false;
                }else{
                    notFoundMetodoPago = true;
                }
            }

            if (notFoundMetodoPago) {
                return new ResponseEntity<>("No se encontró el método de pago", HttpStatus.BAD_REQUEST);
            }else{
                Vendedor vendedor = new Vendedor();
                vendedor.setMail(vendedorDTOsetter.getMail());
                vendedor.setContrasenia(vendedorDTOsetter.getContrasenia());
                vendedor.setNombreTienda(vendedorDTOsetter.getNombreTienda());
                vendedor.setMetodoPagos(metodosPagos);

                repo.save(vendedor);
                return new ResponseEntity<Vendedor>(vendedor, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error, campos de vendedor invalidos", HttpStatus.BAD_REQUEST);
        }
    }

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