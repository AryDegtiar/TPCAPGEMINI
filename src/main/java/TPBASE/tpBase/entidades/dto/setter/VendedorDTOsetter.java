package TPBASE.tpBase.entidades.dto.setter;

import TPBASE.tpBase.entidades.metodosPagos.MetodoPago;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.validation.constraints.*;
import java.util.List;

@Getter
public class VendedorDTOsetter {

    private String mail;

    private String contrasenia;

    private String nombreTienda;

    private List<Integer> metodosPagosId;
}
