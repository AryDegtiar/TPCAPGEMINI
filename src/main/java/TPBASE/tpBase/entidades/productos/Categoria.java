package TPBASE.tpBase.entidades.productos;

import TPBASE.tpBase.entidades.superclases.EntidadPersistente;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter @Setter
@Entity
@Table(name = "categoria")
public class Categoria extends EntidadPersistente {
    @NonNull @NotBlank
    @Column(name = "categoria")
    private String nombre;

    public Categoria(String categoria) {
        this.nombre = categoria;
    }

    public Categoria() {

    }
}
