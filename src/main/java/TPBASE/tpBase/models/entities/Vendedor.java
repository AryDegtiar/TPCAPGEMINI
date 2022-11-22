package TPBASE.tpBase.models.entities;

import TPBASE.tpBase.models.controladores.EntidadPersistente;
import TPBASE.tpBase.models.enums.MetodoPago;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Vendedor extends EntidadPersistente {
    private String usuario;
    private List<MetodoPago> metodosPago;

    public void agregarProducto(){

    }

    public void pausarProducto(){

    }

    public void eliminarProducto(){

    }
}
