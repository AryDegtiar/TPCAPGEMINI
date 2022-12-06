package TPBASE.tpBase.entidades.productos;

import TPBASE.tpBase.entidades.superclases.EntidadPersistente;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
@Entity
@Table(name = "personalizacion")
public class Personalizacion extends EntidadPersistente {
    @NonNull
    @ManyToOne
    private PosiblePersonalizacion posiblePersonalizacion;
    @NonNull @NotBlank
    @Column(name = "nombre")
    private String nombre;
    @NonNull @NotBlank
    @Column(name = "contenido")
    private String contenido;
    @NonNull
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
