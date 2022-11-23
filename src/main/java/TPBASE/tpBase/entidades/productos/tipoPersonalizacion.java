package TPBASE.tpBase.entidades.productos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "tipo_personalizacion")
public class tipoPersonalizacion {
    @Getter
    @Id
    @GeneratedValue
    private Integer tipoperso_id;

    @Column(name = "tipo")
    private String tipo;
    // NO VA
    //private Integer precio;
    //private String contenido; //se le puede agregar el contenido que va a ser como un link de muestra o algo asi
}
