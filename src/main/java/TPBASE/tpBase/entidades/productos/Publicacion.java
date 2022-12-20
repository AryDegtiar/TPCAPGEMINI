package TPBASE.tpBase.entidades.productos;

import TPBASE.tpBase.entidades.enums.EnumEstado;
import TPBASE.tpBase.entidades.actores.Vendedor;
import TPBASE.tpBase.entidades.superclases.EntidadPersistente;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
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

    @NonNull
    @OneToMany
    @JoinColumn(name = "publicacionId", referencedColumnName = "id") //comento por que tira error
    private List<Personalizacion> personalizaciones; 

    @NonNull
    @Column(name = "precioTotal")
    private Integer precioTotal;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "publicacion_x_vendedor")
    private Vendedor vendedor;

    @NonNull
    @Column(name = "urlImagen")
    private String urlImagen;

    @NonNull
    @Column(name = "nombre")
    private String nombre;

    @NonNull
    @Column(name = "descripcion")
    private String descripcion;

    @NonNull
    @Column(name = "cantidadClicks")
    private Integer cantidadVisitas;

    public Publicacion(EnumEstado estadoPublicacion, ProductoBase productoBase, Vendedor vendedor, String urlImagen, String nombre, String descripcion) {
        this();
        this.estadoPublicacion = estadoPublicacion;
        this.productoBase = productoBase;
        this.vendedor = vendedor;
        this.urlImagen = urlImagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadVisitas = 0;
    }

    public void calcularPrecioTotal(){
        Integer precioTotal = this.productoBase.getPrecioBase();
        for (Personalizacion p : this.personalizaciones){
            precioTotal = precioTotal + p.getPrecio();
        }
        this.setPrecioTotal(precioTotal);
    }

    public void sumarVisita(){
        this.cantidadVisitas++;
    }

    public Publicacion() {
        this.personalizaciones = new ArrayList<>();
        this.cantidadVisitas = 0;
    }

    public void addPersonalizacion(Personalizacion personalizacion){
        this.personalizaciones.add(personalizacion);
        this.calcularPrecioTotal();
    }

}
