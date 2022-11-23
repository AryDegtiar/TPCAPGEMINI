package TPBASE.tpBase.entidades.productos;

import lombok.Getter;
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
    private String categoria;

    public Categoria(String categoria) {
        this.categoria = categoria;
    }

    public Categoria() {

    }
}
