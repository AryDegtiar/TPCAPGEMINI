package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.actores.Cliente;
import TPBASE.tpBase.entidades.compras.CompraRealizada;
import TPBASE.tpBase.entidades.dto.setter.ClienteDTOsetter;
import TPBASE.tpBase.entidades.dto.setterSoloID.PublicacionesDTOsetterID;
import TPBASE.tpBase.entidades.metodosPagos.MetodoPago;
import TPBASE.tpBase.entidades.productos.Publicacion;
import TPBASE.tpBase.entidades.repositorios.ClienteRepositorio;
import TPBASE.tpBase.entidades.repositorios.MetodoPagoRepositorio;
import TPBASE.tpBase.entidades.repositorios.PublicacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RepositoryRestController
public class ClienteControladorComplemento {

    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private MetodoPagoRepositorio metodoPagoRepositorio;
    @Autowired
    private PublicacionRepositorio publicacionRepositorio;

    @PostMapping("/cliente")
    public @ResponseBody ResponseEntity<?> agregarCliente(@RequestBody ClienteDTOsetter clienteDTOsetter) {
        try {
            Cliente cliente = new Cliente();
            cliente.setMail(clienteDTOsetter.getMail());
            cliente.setContrasenia(clienteDTOsetter.getContrasenia());
            cliente = clienteRepositorio.save(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("No se pudo agregar el ciente, campos invalidos", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @DeleteMapping("/cliente/{id}")
    public @ResponseBody ResponseEntity<?> eliminarCliente(@PathVariable("id") Integer clienteId) {
        if (clienteRepositorio.existsById(clienteId)) {
            clienteRepositorio.deleteById(clienteId);
            return new ResponseEntity<>("Cliente eliminado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se pudo eliminar el cliente, id invalido", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cliente/{id}/agregarCompra")
    public @ResponseBody ResponseEntity<?> agregarCompra(@PathVariable("id") Integer clienteId, @RequestBody PublicacionesDTOsetterID publicacionesDTOsetterID) {
        //try {
            if (clienteRepositorio.existsById(clienteId)) {
                if (metodoPagoRepositorio.existsById(publicacionesDTOsetterID.getMetodoPagoID())) {
                    MetodoPago metodoPago = metodoPagoRepositorio.findById(publicacionesDTOsetterID.getMetodoPagoID()).get();

                    List<Publicacion> publicaciones = new ArrayList<>();
                    for (Integer publicacionId : publicacionesDTOsetterID.getPublicacionesID()) {
                        if (publicacionRepositorio.existsById(publicacionId)) {
                            publicaciones.add(publicacionRepositorio.findById(publicacionId).get());
                        } else {
                            return new ResponseEntity<>("No se pudo agregar la compra, id de publicacion invalido", HttpStatus.BAD_REQUEST);
                        }
                    }

                    CompraRealizada compraRealizada = CompraRealizada.agregarCompra(publicaciones, metodoPago);

                    Cliente cliente = clienteRepositorio.findById(clienteId).get();
                    cliente.agregarCompra(compraRealizada);
                    clienteRepositorio.save(cliente);
                    return new ResponseEntity<>(cliente, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("No se pudo agregar la compra, metodo de pago invalido", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("No se pudo agregar la compra, id invalido", HttpStatus.BAD_REQUEST);
            }
        //}catch (Exception e){
        //    return new ResponseEntity<>("No se pudo agregar la compra, campos invalidos", HttpStatus.BAD_REQUEST);
        //}
    }

}
