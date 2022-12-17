package TPBASE.tpBase.entidades.dto.setter;

import TPBASE.tpBase.entidades.actores.Vendedor;
import TPBASE.tpBase.entidades.enums.EnumEstado;
import TPBASE.tpBase.entidades.productos.Personalizacion;
import TPBASE.tpBase.entidades.productos.ProductoBase;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
public class PublicacionDTOsetter {

    private EnumEstado estadoPublicacion;

    private Integer productoBaseId;

    private List<Integer> personalizacionesId;

    private Integer vendedorId;

    private String urlImagen;

}
