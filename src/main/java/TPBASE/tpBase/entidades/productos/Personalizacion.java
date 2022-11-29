package TPBASE.tpBase.entidades.productos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "personalizacion")
public class Personalizacion {
    @Getter
    @Id
    @GeneratedValue
    private Integer perso_id;

    @ManyToOne
    private PosiblePersonalizacion posiblePersonalizacion;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "contenido")
    private String contenido;
    @Column(name = "precio")
    private Integer precio;

    public Personalizacion(PosiblePersonalizacion posiblePersonalizacion, String nombre, String contenido, Integer precio) {
        this.posiblePersonalizacion = posiblePersonalizacion;
        this.nombre = nombre;
        this.contenido = contenido;
        this.precio = precio;
    }
    public Personalizacion() {
    }
}
