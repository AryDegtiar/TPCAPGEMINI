package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.productos.PosiblePersonalizacion;
import TPBASE.tpBase.entidades.productos.ProductoBase;
import TPBASE.tpBase.entidades.repositorios.PosiblePersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.ProductoBaseRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;


@RepositoryRestController
public class ProductoBaseControladorComplemento {

    @Autowired
    ProductoBaseRepositorio repo;

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