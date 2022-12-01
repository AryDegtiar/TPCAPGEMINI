package TPBASE.tpBase.entidades.controladores;

import TPBASE.tpBase.entidades.productos.AreaPersonalizacion;
import TPBASE.tpBase.entidades.productos.PosiblePersonalizacion;
import TPBASE.tpBase.entidades.repositorios.AreaPersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.PosiblePersonalizacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestController
public class AreaControladorComplemento {

    @Autowired
    AreaPersonalizacionRepositorio areaPersonalizacionRepositorio;

    @Autowired
    PosiblePersonalizacionRepositorio posiblePersonalizacionRepositorio;

    @DeleteMapping(path = {"/areapersonalizacion/{areaID}"})
    @Transactional
    public @ResponseBody ResponseEntity<AreaPersonalizacion> darDeBajaArea(@PathVariable("areaID") Integer areaID){
        AreaPersonalizacion area;
        if (areaPersonalizacionRepositorio.existsById(areaID)){
            area = areaPersonalizacionRepositorio.findById(areaID).get();
            area.setActivo(false);
            return new ResponseEntity<AreaPersonalizacion>(area, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }

        // modificar todas lass posibles personalizaciones que tengan a el area anulada para que aparezca como inactivo
        //boolean b = posiblePersonalizacionRepositorio.existsByAreaPersonalizacionId(areaID);
        //boolean b = posiblePersonalizacionRepositorio.existsByAreaPersonalizacion(area);
        //List<PosiblePersonalizacion> p = posiblePersonalizacionRepositorio.findAllByAreaPersonalizacion(area);
    }

}

