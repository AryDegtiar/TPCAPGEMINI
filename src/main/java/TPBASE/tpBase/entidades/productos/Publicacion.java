package TPBASE.tpBase.entidades.productos;

import TPBASE.tpBase.entidades.enums.EnumEstado;
import TPBASE.tpBase.entidades.modelos.Vendedor;
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
    @OneToMany // revisar y fiajarse que cre una table intermedia
    @JoinColumn(name = "publicacionID", referencedColumnName = "publi_id")
    private List<Personalizacion> personalizaciones; // podria ser un set de personalizaciones

    @Column(name = "precioTotal")
    private Integer precioTotal;

    @ManyToOne
    @JoinColumn(name = "publicacion_x_vendedor")
    private Vendedor vendedor;

    public void agregarPersonalizacion(Personalizacion personalizacion){
        this.personalizaciones.add(personalizacion);
    }

}
