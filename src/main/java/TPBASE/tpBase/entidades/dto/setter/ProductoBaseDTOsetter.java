package TPBASE.tpBase.entidades.dto.setter;

import TPBASE.tpBase.entidades.productos.Categoria;
import TPBASE.tpBase.entidades.productos.PosiblePersonalizacion;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Getter
public class ProductoBaseDTOsetter {

    private Integer categoriaId;

    private String nombre;

    private String descripcion;

    private Integer precioBase;

    private Integer tiempoFabricacion;

    private List<Integer> posiblePersonalizacionesId;

}

