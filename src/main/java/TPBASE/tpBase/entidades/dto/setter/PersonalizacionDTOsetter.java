package TPBASE.tpBase.entidades.dto.setter;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Getter
public class PersonalizacionDTOsetter {

    private Integer posiblePersonalizacionId;

    private String nombre;

    private String contenido;

    private Integer precio;
}
