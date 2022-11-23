package TPBASE.tpBase.entidades.productos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "producto_base")
public class ProductoBase {
    @Getter
    @Id
    @GeneratedValue
    private Integer prodbase_id;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "cat_id")
    private Categoria categoria;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "precioBase")
    private Integer precioBase;
    @Column(name = "tiempoFabricacion")
    private Integer tiempoFabricacion;



    @OneToMany // revisar y fiajarse que cre una table intermedia
    private List<AreaPersonalizacion> areaPersonalizaciones;

    // puede no ir, si llego se hace sino nop C:
    @Transient // revisar y fiajarse que cre una table intermedia
    private List<AtributoProducto> atributos;

    public ProductoBase() {
        this.areaPersonalizaciones = new ArrayList<>();
        this.atributos = new ArrayList<>();
    }

}
