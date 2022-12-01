package TPBASE.tpBase.entidades.productos;

import TPBASE.tpBase.entidades.enums.EnumEstado;
import TPBASE.tpBase.entidades.actores.Vendedor;
import TPBASE.tpBase.entidades.superclases.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "publicacion")
public class Publicacion extends EntidadPersistente {
    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    private EnumEstado estadoPublicacion;

    @ManyToOne
    @JoinColumn(name = "productoBase", referencedColumnName = "id")
    private ProductoBase productoBase;

    @OneToMany
    //@JoinColumn(name = "publicacion", referencedColumnName = "id") //comento por que tira error
    private List<Personalizacion> personalizaciones; 

    @Column(name = "precioTotal")
    private Integer precioTotal;

    @ManyToOne
    @JoinColumn(name = "publicacion_x_vendedor")
    private Vendedor vendedor;

    public Publicacion(EnumEstado estadoPublicacion, ProductoBase productoBase, Vendedor vendedor) {
        this();
        this.estadoPublicacion = estadoPublicacion;
        this.productoBase = productoBase;
        this.vendedor = vendedor;
    }

    public void calcularPrecioTotal(){
        Integer precioTotal = this.productoBase.getPrecioBase();
        for (Personalizacion p : this.personalizaciones){
            precioTotal = precioTotal + p.getPrecio();
        }
        this.setPrecioTotal(precioTotal);
    }

    public Publicacion() {
        this.personalizaciones = new ArrayList<>();
    }

    public void addPersonalizacion(Personalizacion personalizacion){
        this.personalizaciones.add(personalizacion);
        this.calcularPrecioTotal();
    }

}
