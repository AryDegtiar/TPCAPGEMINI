package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.actores.Vendedor;
import TPBASE.tpBase.entidades.dto.setter.PublicacionDTOsetter;
import TPBASE.tpBase.entidades.dto.setterSoloID.PersonalizacionesDTOsetterID;
import TPBASE.tpBase.entidades.dto.setterSoloID.ProductoBaseDTOsetterID;
import TPBASE.tpBase.entidades.dto.setterSoloID.VendedorDTOsetterID;
import TPBASE.tpBase.entidades.productos.Personalizacion;
import TPBASE.tpBase.entidades.productos.PosiblePersonalizacion;
import TPBASE.tpBase.entidades.productos.ProductoBase;
import TPBASE.tpBase.entidades.productos.Publicacion;
import TPBASE.tpBase.entidades.repositorios.PersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.ProductoBaseRepositorio;
import TPBASE.tpBase.entidades.repositorios.PublicacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.VendedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RepositoryRestController
public class PublicacionControladorComplemento {

    @Autowired
    PublicacionRepositorio repo;
    @Autowired
    PersonalizacionRepositorio personalizacionRepo;
    @Autowired
    ProductoBaseRepositorio productoBaseRepo;
    @Autowired
    VendedorRepositorio vendedorRepo;

    @PostMapping(path = "/publicacion")
    public @ResponseBody ResponseEntity<?> agregarPublicacion(@RequestBody PublicacionDTOsetter publicacionDTOsetter) {
        try {
            boolean notFoundProductoBase = false;
            ProductoBase productoBase = null;
            if (productoBaseRepo.existsById(publicacionDTOsetter.getProductoBaseId())) {
                productoBase = productoBaseRepo.findById(publicacionDTOsetter.getProductoBaseId()).get();
            } else {
                notFoundProductoBase = true;
            }

            boolean notFoundVendedor = false;
            Vendedor vendedor = null;
            if (vendedorRepo.existsById(publicacionDTOsetter.getVendedorId())) {
                vendedor = vendedorRepo.findById(publicacionDTOsetter.getVendedorId()).get();
            } else {
                notFoundVendedor = true;
            }

            boolean notFoundPersonalizacion = false;
            List<Personalizacion> personalizaciones = new ArrayList<>();
            for (Integer personalizacionId : publicacionDTOsetter.getPersonalizacionesId()) {
                if (personalizacionRepo.existsById(personalizacionId)) {
                    Personalizacion personalizacion = personalizacionRepo.findById(personalizacionId).get();
                    personalizaciones.add(personalizacion);
                } else {
                    notFoundPersonalizacion = true;
                }
            }

            if (notFoundProductoBase == true || notFoundVendedor == true || notFoundPersonalizacion == true) {
                return new ResponseEntity<>("Error, ids invalidos",HttpStatus.BAD_REQUEST);
            } else {
                Publicacion publicacion = new Publicacion();
                publicacion.setEstadoPublicacion(publicacionDTOsetter.getEstadoPublicacion());
                publicacion.setProductoBase(productoBase);
                publicacion.setPersonalizaciones(personalizaciones);
                publicacion.setVendedor(vendedor);
                publicacion.calcularPrecioTotal();

                repo.save(publicacion);
                return new ResponseEntity<Publicacion>(publicacion, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>("No se pudo cargar la publicacion, campos invalidos",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = {"/publicacion/{publicacionID}"})
    @Transactional
    public @ResponseBody ResponseEntity<Publicacion> darDeBajaArea(@PathVariable("publicacionID") Integer publicacionID){
        if (repo.existsById(publicacionID)){
            Publicacion posiblePersonalizacion = repo.findById(publicacionID).get();
            posiblePersonalizacion.setActivo(false);
            return new ResponseEntity<Publicacion>(posiblePersonalizacion, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //PATCH
    @Transactional
    @PatchMapping(path = {"/publicacion/{id}/productoBase"})
    public @ResponseBody ResponseEntity<?> modificarProductoBase(@PathVariable("id") Integer id, @RequestBody ProductoBaseDTOsetterID productoBaseDTOsetterID){
        try {
            if (repo.existsById(id)) {
                Publicacion publicacion = repo.findById(id).get();
                if (productoBaseRepo.existsById(productoBaseDTOsetterID.getProductoBaseId())) {
                    ProductoBase productoBase = productoBaseRepo.findById(productoBaseDTOsetterID.getProductoBaseId()).get();
                    publicacion.setProductoBase(productoBase);
                    publicacion.calcularPrecioTotal();
                    return new ResponseEntity<Publicacion>(publicacion, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("No se encontro el producto base", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("No se encontro la publicacion", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("No se pudo modificar el producto base, campos invalidos",HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PatchMapping(path = {"/publicacion/{id}/vendedor"})
    public @ResponseBody ResponseEntity<?> modificarVendedor(@PathVariable("id") Integer id, @RequestBody VendedorDTOsetterID vendedorDTOsetterID){
        try {
            if (repo.existsById(id)) {
                Publicacion publicacion = repo.findById(id).get();
                if (vendedorRepo.existsById(vendedorDTOsetterID.getVendedorId())) {
                    Vendedor vendedor = vendedorRepo.findById(vendedorDTOsetterID.getVendedorId()).get();
                    publicacion.setVendedor(vendedor);
                    return new ResponseEntity<Publicacion>(publicacion, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("No se encontro el vendedor", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("No se encontro la publicacion", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("No se pudo modificar el vendedor, campos invalidos",HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PatchMapping(path = {"/publicacion/{id}/personalizaciones"})
    public @ResponseBody ResponseEntity<?> modificarPersonalizaciones(@PathVariable("id") Integer id, @RequestBody PersonalizacionesDTOsetterID personalizacionesDTOsetterID){
        try {
            if (repo.existsById(id)) {
                Publicacion publicacion = repo.findById(id).get();
                List<Personalizacion> personalizaciones = new ArrayList<>();
                for (Integer personalizacionId : personalizacionesDTOsetterID.getPersonalizacionesId()) {
                    if (personalizacionRepo.existsById(personalizacionId)) {
                        Personalizacion personalizacion = personalizacionRepo.findById(personalizacionId).get();
                        personalizaciones.add(personalizacion);
                    } else {
                        return new ResponseEntity<>("No se encontro la personalizacion", HttpStatus.BAD_REQUEST);
                    }
                }
                publicacion.setPersonalizaciones(personalizaciones);
                publicacion.calcularPrecioTotal();
                return new ResponseEntity<Publicacion>(publicacion, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se encontro la publicacion", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("No se pudo modificar las personalizaciones, campos invalidos",HttpStatus.BAD_REQUEST);
        }
    }

}