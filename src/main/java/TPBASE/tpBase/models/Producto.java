package TPBASE.tpBase.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

public class Producto {
    private String categoria;
    private String nombre;
    private String descripcion;
    private Integer precioBase;
    private Integer tiempoFabricacion;
    private List<AreaPersonalizacion> areaPersonalizaciones;
    private List<AtributoProducto> atributos;

    private Vendedor vendedor;

    public void personalizarArea(AreaPersonalizacion areaPersonalizaciones){
        // verificar que el area este en las areasPersonalizacionPermitidas
        // aregar a la lista de personalizaciones
    }

    public void eliminarPersonalizacion(AreaPersonalizacion areaPersonalizaciones){
        // codigo
    }

    public Integer precioTotal(){
        // precio base + precio de cada personalizacion
        return 0;
    }

}
