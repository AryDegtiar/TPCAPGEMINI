package TPBASE.tpBase.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "productoBase")
public class ProductoBase extends EntidadPersistente{
    @Transient
    private Categoria Categoria;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "precioBase")
    private Integer precioBase;
    @Column(name = "tiempoFabricacion")
    private Integer tiempoFabricacion;

    @Transient
    private List<AreaPersonalizacion> areaPersonalizaciones;

    // puede no ir, si llego se hace sino nop C:
    @Transient
    private List<AtributoProducto> atributos;

    public ProductoBase(List<AreaPersonalizacion> areaPersonalizaciones,List<AtributoProducto> atributos) {
        this.areaPersonalizaciones = areaPersonalizaciones;
        this.atributos = atributos;
    }

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
