package TPBASE.tpBase.entidades.dto;

import TPBASE.tpBase.entidades.actores.Vendedor;
import TPBASE.tpBase.entidades.metodosPagos.MetodoPago;
import TPBASE.tpBase.entidades.productos.ProductoBase;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Projection(name = "VendedorDTOgetter", types = Vendedor.class)
public interface VendedorDTOgetter {

    String getMail();

    String getContrasenia();

    String getNombreTienda();

    List<MetodoPago> getMetodoPagos();
}
