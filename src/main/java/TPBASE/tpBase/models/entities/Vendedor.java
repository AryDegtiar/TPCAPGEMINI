package TPBASE.tpBase.models.entities;

import TPBASE.tpBase.models.controladores.EntidadPersistente;
import TPBASE.tpBase.models.enums.MetodoPago;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.List;

@Getter
@Setter
public class Vendedor extends EntidadPersistente {
    @Column(name = "usuario")
    private String usuario;
    @Transient
    private List<MetodoPago> metodosPago;

    public void agregarProducto(){

    }

    public void pausarProducto(){

    }

    public void eliminarProducto(){

    }
}
