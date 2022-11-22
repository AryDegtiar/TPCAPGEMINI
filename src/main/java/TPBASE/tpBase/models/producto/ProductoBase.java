package TPBASE.tpBase.models.producto;

import TPBASE.tpBase.models.controladores.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "productoBase")
public class ProductoBase extends EntidadPersistente {
    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
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

    public ProductoBase() {
        this.areaPersonalizaciones = new ArrayList<>();
        this.atributos = new ArrayList<>();
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
