package TPBASE.tpBase.entidades.productos;

import TPBASE.tpBase.entidades.enums.EnumEstado;
import TPBASE.tpBase.entidades.actores.Vendedor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "publicacion")
public class Publicacion {
    @Getter
    @Id
    @GeneratedValue
    private Integer publi_id;

    @Enumerated(EnumType.STRING)
    private EnumEstado estadoPublicacion;

    @ManyToOne
    @JoinColumn(name = "productoBase", referencedColumnName = "prodbase_id")
    private ProductoBase productoBase;
    @OneToMany
    @JoinColumn(name = "publicacionID", referencedColumnName = "publi_id")
    private List<Personalizacion> personalizaciones; 

    @Column(name = "precioTotal")
    private Integer precioTotal;

    @ManyToOne
    @JoinColumn(name = "publicacion_x_vendedor")
    private Vendedor vendedor;

    public Publicacion(EnumEstado estadoPublicacion, ProductoBase productoBase, List<Personalizacion> personalizaciones, Vendedor vendedor) {
        this.estadoPublicacion = estadoPublicacion;
        this.productoBase = productoBase;
        this.personalizaciones = personalizaciones;
        this.vendedor = vendedor;
        obtenerPrecioTotal(productoBase, personalizaciones);
    }

    public void obtenerPrecioTotal(ProductoBase productoBase, List<Personalizacion> personalizaciones){
        Integer precioTotal = productoBase.getPrecioBase();
        for (Personalizacion p : personalizaciones){
            precioTotal = precioTotal + p.getPrecio();
        }
        this.setPrecioTotal(precioTotal);
    }

    public Publicacion() {
    }

    public void agregarPersonalizacion(Personalizacion personalizacion){
        this.personalizaciones.add(personalizacion);
    }

}
