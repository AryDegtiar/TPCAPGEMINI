package TPBASE.tpBase.entidades.dto.setterSoloID;

import lombok.Getter;

import java.util.List;

@Getter
public class PublicacionesDTOsetterID {
    private List<Integer> publicacionesId;
    private Integer metodoPagoId;
    private String direccionEnvio;
}
