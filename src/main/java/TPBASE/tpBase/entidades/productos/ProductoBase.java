package TPBASE.tpBase.entidades.productos;

import TPBASE.tpBase.entidades.superclases.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "producto_base")
public class ProductoBase extends EntidadPersistente {
    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "precioBase")
    private Integer precioBase;
    @Column(name = "tiempoFabricacion")
    private Integer tiempoFabricacion;

    @ManyToMany
    private List<PosiblePersonalizacion> posiblePersonalizaciones;

    @Transient // puede no ir, si llego se hace sino nop C:
    private List<AtributoProducto> atributos;

    public ProductoBase() {
        this.posiblePersonalizaciones = new ArrayList<>();
        this.atributos = new ArrayList<>();
    }

    public ProductoBase(Categoria categoria, String nombre, String descripcion, Integer precioBase, Integer tiempoFabricacion) {
        this();
        this.categoria = categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.tiempoFabricacion = tiempoFabricacion;
    }

    public void addPosiblePersonalizacion(PosiblePersonalizacion posiblePersonalizacion){
        this.posiblePersonalizaciones.add(posiblePersonalizacion);
    }

}
