package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.dto.setter.ProductoBaseDTOsetter;
import TPBASE.tpBase.entidades.metodosPagos.MetodoPago;
import TPBASE.tpBase.entidades.productos.Categoria;
import TPBASE.tpBase.entidades.productos.PosiblePersonalizacion;
import TPBASE.tpBase.entidades.productos.ProductoBase;
import TPBASE.tpBase.entidades.repositorios.CategoriaRepositorio;
import TPBASE.tpBase.entidades.repositorios.PosiblePersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.ProductoBaseRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@RepositoryRestController
public class ProductoBaseControladorComplemento {

    @Autowired
    ProductoBaseRepositorio repo;
    @Autowired
    PosiblePersonalizacionRepositorio posiblePersoRepo;
    @Autowired
    CategoriaRepositorio categoriaRepo;

    @PostMapping(path = "/produtobase")
    public @ResponseBody ResponseEntity<ProductoBase> agregarPosiblePersonalizacion(@RequestBody ProductoBaseDTOsetter productoBaseDTOsetter){
        Categoria categoria = categoriaRepo.findById(productoBaseDTOsetter.getCategoriaId()).get();
        List<PosiblePersonalizacion> posiblesPersonalizaciones = new ArrayList<>();
        for(Integer posiblePersonalizacionId : productoBaseDTOsetter.getPosiblePersonalizacionesId()){
            PosiblePersonalizacion posiblePersonalizacion = posiblePersoRepo.findById(posiblePersonalizacionId).get();
            posiblesPersonalizaciones.add(posiblePersonalizacion);
        }

        ProductoBase productoBase = new ProductoBase();
        productoBase.setCategoria(categoria);
        productoBase.setNombre(productoBaseDTOsetter.getNombre());
        productoBase.setDescripcion(productoBaseDTOsetter.getDescripcion());
        productoBase.setPrecioBase(productoBaseDTOsetter.getPrecioBase());
        productoBase.setTiempoFabricacion(productoBaseDTOsetter.getTiempoFabricacion());
        productoBase.setPosiblePersonalizaciones(posiblesPersonalizaciones);

        repo.save(productoBase);
        return new ResponseEntity<ProductoBase>(productoBase, HttpStatus.OK);
    }

    @DeleteMapping(path = {"/productobase/{productoBaseID}"})
    @Transactional
    public @ResponseBody ResponseEntity<ProductoBase> darDeBajaArea(@PathVariable("productoBaseID") Integer productoBaseID){
        if (repo.existsById(productoBaseID)){
            ProductoBase productoBase = repo.findById(productoBaseID).get();
            productoBase.setActivo(false);
            return new ResponseEntity<ProductoBase>(productoBase, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}