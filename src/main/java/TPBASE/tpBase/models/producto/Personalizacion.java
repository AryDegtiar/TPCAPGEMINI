package TPBASE.tpBase.models.producto;

import TPBASE.tpBase.models.controladores.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter @Setter
@Entity
public class Personalizacion extends EntidadPersistente {
    @Column(name = "seleccionArea")
    private String seleccionArea;
    @Column(name = "seleccionTipo")
    private String seleccionTipo;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "contenido")
    private String contenido;
    @Column(name = "precio")
    private Integer precio;
}
