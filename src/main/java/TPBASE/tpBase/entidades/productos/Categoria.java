package TPBASE.tpBase.entidades.productos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "categoria")
public class Categoria {
    @Getter
    @Id
    @GeneratedValue
    private Integer cat_id;

    @Column(name = "categoria")
    private String nombre;

    public Categoria(String categoria) {
        this.nombre = categoria;
    }

    public Categoria() {

    }
}
