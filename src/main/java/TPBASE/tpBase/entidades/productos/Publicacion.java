package TPBASE.tpBase.entidades.productos;

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

    @ManyToOne
    @JoinColumn(name = "productoBase", referencedColumnName = "prodbase_id")
    private ProductoBase productoBase;
    @OneToMany // revisar y fiajarse que cre una table intermedia
    private List<Personalizacion> personalizaciones; // podria ser un set de personalizaciones
    @Column(name = "precioTotal")
    private Integer precioTotal;

    public void agregarPersonalizacion(Personalizacion personalizacion){
        this.personalizaciones.add(personalizacion);
    }

}
