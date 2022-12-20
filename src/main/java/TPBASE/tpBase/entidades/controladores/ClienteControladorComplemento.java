package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.actores.Cliente;
import TPBASE.tpBase.entidades.actores.Vendedor;
import TPBASE.tpBase.entidades.compras.CantidadXProducto;
import TPBASE.tpBase.entidades.compras.CompraRealizada;
import TPBASE.tpBase.entidades.dto.setter.ClienteDTOsetter;
import TPBASE.tpBase.entidades.dto.setterSoloID.PublicacionesDTOsetterID;
import TPBASE.tpBase.entidades.metodosPagos.MetodoPago;
import TPBASE.tpBase.entidades.productos.Publicacion;
import TPBASE.tpBase.entidades.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RepositoryRestController
public class ClienteControladorComplemento {

    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private MetodoPagoRepositorio metodoPagoRepositorio;
    @Autowired
    private PublicacionRepositorio publicacionRepositorio;
    @Autowired
    private CompraRealizadaRepositorio compraRealizadaRepositorio;
    @Autowired
    private CantidadXProductoRepositorio cantidadXProductoRepositorio;

    private final EntityManager em;

    public ClienteControladorComplemento(EntityManager em) {
        this.em = em;
    }

    @GetMapping("/cliente/login")
    public ResponseEntity<?> getLogIn(@RequestParam(name = "email") String email,
                                      @RequestParam(name = "password") String password) {

        List<Cliente> resultCli = em.createQuery("SELECT c FROM Cliente c WHERE c.mail = :email AND c.contrasenia = :password", Cliente.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList();
        if (resultCli.size() == 0){
            return new ResponseEntity<>("Usuario o contraseña invalidos", HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(resultCli.get(0), HttpStatus.OK);
        }

    }

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

    // COMPRAS
    @PostMapping("/cliente/{id}/compraRealizadas")
    public @ResponseBody ResponseEntity<?> agregarCompra(@PathVariable("id") Integer clienteId, @RequestBody PublicacionesDTOsetterID publicacionesDTOsetterID) {
        try {
            if (clienteRepositorio.existsById(clienteId)) {
                Cliente cliente = clienteRepositorio.findById(clienteId).get();
                List<Publicacion> publicaciones = new ArrayList<>();
                for (Integer publicacionId : publicacionesDTOsetterID.getPublicacionesId()) {
                    if (publicacionRepositorio.existsById(publicacionId)) {
                        publicaciones.add(publicacionRepositorio.findById(publicacionId).get());
                    } else {
                        return new ResponseEntity<>("No se pudo agregar la compra, id de publicacion invalido", HttpStatus.BAD_REQUEST);
                    }
                }
                // mapeo para contar la cantidad de cada producto
                Map<Publicacion, Integer> cantidadXProducto = new HashMap<>();
                for (Publicacion publicacion : publicaciones) {
                    if (cantidadXProducto.containsKey(publicacion)) {
                        cantidadXProducto.put(publicacion, cantidadXProducto.get(publicacion) + 1);
                    } else {
                        cantidadXProducto.put(publicacion, 1);
                    }
                }
                //convierto a cantidadXProducto y guardo cada cantidadxproducto
                List<CantidadXProducto> cantidadXProductos = new ArrayList<>();
                for (Publicacion publicacion : cantidadXProducto.keySet()) {
                    CantidadXProducto cantidadXProducto1 = new CantidadXProducto(publicacion, cantidadXProducto.get(publicacion));
                    cantidadXProductoRepositorio.save(cantidadXProducto1);
                    cantidadXProductos.add(cantidadXProducto1);
                }
                //creo la compra y la guardo
                MetodoPago metodoPago = metodoPagoRepositorio.findById(publicacionesDTOsetterID.getMetodoPagoId()).get();
                Vendedor vendedor = publicaciones.get(0).getVendedor();
                CompraRealizada compraRealizada = new CompraRealizada(cantidadXProductos, metodoPago, vendedor, publicacionesDTOsetterID.getDireccionEnvio());
                compraRealizadaRepositorio.save(compraRealizada);

                cliente.agregarCompra(compraRealizada);
                clienteRepositorio.save(cliente);
                return new ResponseEntity<>(compraRealizada, HttpStatus.OK);

            } else {
                return new ResponseEntity<>("No se pudo agregar la compra, id de cliente invalido", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("No se pudo agregar la compra, campos invalidos", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cliente/{id}/compraRealizadas/{idCompra}")
    public ResponseEntity<?> postCompraId(){
        return new ResponseEntity<>("No se puede modificar las compras realidas", HttpStatus.BAD_REQUEST);
    }
    @PatchMapping("/cliente/{id}/compraRealizadas/{idCompra}")
    public ResponseEntity<?> patchCompra(){
        return new ResponseEntity<>("No se puede modificar las compras realidas", HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/cliente/{id}/compraRealizadas/{idCompra}")
    public ResponseEntity<?> putCompra(){
        return new ResponseEntity<>("No se puede modificar las compras realidas", HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/cliente/{id}/compraRealizadas/{idCompra}")
    public ResponseEntity<?> modificarCompra(){
        return new ResponseEntity<>("No se puede eliminar las compras realidas", HttpStatus.BAD_REQUEST);
    }

}
