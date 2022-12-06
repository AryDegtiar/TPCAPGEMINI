package TPBASE.tpBase.entidades.productos;

import TPBASE.tpBase.entidades.enums.EnumEstado;
import TPBASE.tpBase.entidades.actores.Vendedor;
import TPBASE.tpBase.entidades.superclases.EntidadPersistente;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "publicacion")
public class Publicacion extends EntidadPersistente {
    @NonNull
    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private EnumEstado estadoPublicacion;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "productoBase", referencedColumnName = "id")
    private ProductoBase productoBase;

    @NonNull @NotEmpty
    @OneToMany
    //@JoinColumn(name = "publicacion", referencedColumnName = "id") //comento por que tira error
    private List<Personalizacion> personalizaciones; 

    @NonNull
    @Column(name = "precioTotal")
    private Integer precioTotal;

    @NonNull
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
