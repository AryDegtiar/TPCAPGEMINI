package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.actores.Vendedor;
import TPBASE.tpBase.entidades.dto.setter.PublicacionDTOsetter;
import TPBASE.tpBase.entidades.dto.setterSoloID.PersonalizacionesDTOsetterID;
import TPBASE.tpBase.entidades.dto.setterSoloID.ProductoBaseDTOsetterID;
import TPBASE.tpBase.entidades.dto.setterSoloID.VendedorDTOsetterID;
import TPBASE.tpBase.entidades.enums.EnumEstado;
import TPBASE.tpBase.entidades.enums.EnumMetodoPago;
import TPBASE.tpBase.entidades.metodosPagos.MetodoPago;
import TPBASE.tpBase.entidades.productos.Categoria;
import TPBASE.tpBase.entidades.productos.Personalizacion;
import TPBASE.tpBase.entidades.productos.ProductoBase;
import TPBASE.tpBase.entidades.productos.Publicacion;
import TPBASE.tpBase.entidades.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
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
    @Autowired
    CategoriaRepositorio categoriaRepo;

    private final EntityManager em;

    public PublicacionControladorComplemento(EntityManager em) {
        this.em = em;
    }

    @GetMapping(path = {"/publicacion","/publicacion/"})
    public ResponseEntity<?> getPublicacionesActivas(@RequestParam(name = "vendedorId", required = false) Integer vendedorId,
                                                     @RequestParam(name = "activo", required = false) Boolean activo,
                                                     @RequestParam(name = "categoriaId", required = false) Integer categoriaId,
                                                     @RequestParam(name = "estado", required = false) String estadoPublicacion){
        try {
            if (vendedorId != null || activo != null || estadoPublicacion != null || categoriaId != null) {

                String query = null;

                String queryVendedor = null;
                if (vendedorId != null) {
                    Vendedor vendedor = vendedorRepo.findById(vendedorId).orElse(null);
                    if (vendedor == null) {
                        return new ResponseEntity<>("No existe el vendedor con id " + vendedorId, HttpStatus.NOT_FOUND);
                    }
                    queryVendedor = "p.vendedor.id =" + vendedorId;
                    if (query == null) {
                        query = "SELECT p FROM Publicacion p WHERE " + queryVendedor;
                    } else {
                        query = query + " AND " + queryVendedor;
                    }
                }

                String queryActivo = null;
                if (activo != null) {
                    queryActivo = "p.activo =" + activo;
                    if (query == null) {
                        query = "SELECT p FROM Publicacion p WHERE " + queryActivo;
                    } else {
                        query = query + " AND " + queryActivo;
                    }
                }

                String queryCategoria = null;
                if (categoriaId != null) {
                    Categoria categoria = categoriaRepo.findById(categoriaId).orElse(null);
                    if (categoria == null) {
                        return new ResponseEntity<>("No existe la categoria con id " + categoriaId, HttpStatus.NOT_FOUND);
                    }
                    queryCategoria = "p.productoBase.categoria.id =" + categoriaId;
                    if (query == null) {
                        query = "SELECT p FROM Publicacion p WHERE " + queryCategoria;
                    } else {
                        query = query + " AND " + queryCategoria;
                    }

                }

                // esta parte es medio choclo feo pero si no lo hago de esta forma el enum no me lo reconoce
                if (estadoPublicacion != null) {
                    System.out.println("ENTREEEEEEEEEE");
                    switch (estadoPublicacion) {
                        case "DISPONIBLE":
                            if (query == null) {
                                query = "SELECT p FROM Publicacion p WHERE p.estadoPublicacion = :estado";
                                return new ResponseEntity<>(em.createQuery(query).setParameter("estado", EnumEstado.DISPONIBLE).getResultList(), HttpStatus.OK);
                            } else {
                                return new ResponseEntity<>(em.createQuery(query + " AND p.estadoPublicacion = :estado").setParameter("estado", EnumEstado.DISPONIBLE).getResultList(), HttpStatus.OK);
                            }
                        case "PAUSADO":
                            if (query == null) {
                                query = "SELECT p FROM Publicacion p WHERE p.estadoPublicacion = :estado";
                                return new ResponseEntity<>(em.createQuery(query).setParameter("estado", EnumEstado.PAUSADO).getResultList(), HttpStatus.OK);
                            } else {
                                return new ResponseEntity<>(em.createQuery(query + " AND p.estadoPublicacion = :estado").setParameter("estado", EnumEstado.PAUSADO).getResultList(), HttpStatus.OK);
                            }
                        case "CANCELADO":
                            if (query == null) {
                                query = "SELECT p FROM Publicacion p WHERE p.estadoPublicacion = :estado";
                                return new ResponseEntity<>(em.createQuery(query).setParameter("estado", EnumEstado.CANCELADO).getResultList(), HttpStatus.OK);
                            } else {
                                return new ResponseEntity<>(em.createQuery(query + " AND p.estadoPublicacion = :estado").setParameter("estado", EnumEstado.CANCELADO).getResultList(), HttpStatus.OK);
                            }
                        default:
                            return new ResponseEntity<>("No existe el estado de publicacion " + estadoPublicacion, HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity<>(em.createQuery(query).getResultList(), HttpStatus.OK);
                }

            } else {
                return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Hubo un error con la peticion", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    // este get esta hecho para que haya una equivalencia en la vista de producto al agregar al carrito desde front
    @GetMapping(path = {"/publicacion/{id}"})
    public ResponseEntity<?> getPublicacion(@PathVariable Integer id){
        try {
            Publicacion publicacion = repo.findById(id).orElse(null);
            if (publicacion == null) {
                return new ResponseEntity<>("No existe la publicacion con id " + id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(publicacion, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Hubo un error con la peticion", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
    @PatchMapping(path = {"/publicacion/{id}/productoBase"})
    public @ResponseBody ResponseEntity<?> modificarProductoBase(@PathVariable("id") Integer id, @RequestBody ProductoBaseDTOsetterID productoBaseDTOsetterID){
        try {
            if (repo.existsById(id)) {
                Publicacion publicacion = repo.findById(id).get();
                if (productoBaseRepo.existsById(productoBaseDTOsetterID.getProductoBaseId())) {
                    ProductoBase productoBase = productoBaseRepo.findById(productoBaseDTOsetterID.getProductoBaseId()).get();
                    publicacion.setProductoBase(productoBase);
                    publicacion.calcularPrecioTotal();
                    publicacion = repo.save(publicacion);
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

    @PatchMapping(path = {"/publicacion/{id}/vendedor"})
    public @ResponseBody ResponseEntity<?> modificarVendedor(@PathVariable("id") Integer id, @RequestBody VendedorDTOsetterID vendedorDTOsetterID){
        try {
            if (repo.existsById(id)) {
                Publicacion publicacion = repo.findById(id).get();
                if (vendedorRepo.existsById(vendedorDTOsetterID.getVendedorId())) {
                    Vendedor vendedor = vendedorRepo.findById(vendedorDTOsetterID.getVendedorId()).get();
                    publicacion.setVendedor(vendedor);
                    publicacion = repo.save(publicacion);
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
                publicacion = repo.save(publicacion);
                return new ResponseEntity<Publicacion>(publicacion, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se encontro la publicacion", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("No se pudo modificar las personalizaciones, campos invalidos",HttpStatus.BAD_REQUEST);
        }
    }

}