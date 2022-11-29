package TPBASE.tpBase;

import TPBASE.tpBase.entidades.actores.Vendedor;
import TPBASE.tpBase.entidades.controladores.PublicacionControlador;
import TPBASE.tpBase.entidades.enums.EnumEstado;
import TPBASE.tpBase.entidades.enums.EnumMetodoPago;
import TPBASE.tpBase.entidades.metodosPagos.MetodoPago;
import TPBASE.tpBase.entidades.productos.*;
import TPBASE.tpBase.entidades.productos.Categoria;
import TPBASE.tpBase.entidades.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TpBaseApplication {
	// REPOSITORIOS
	@Autowired
	CategoriaRepositorio categoriaRepositorio;
	@Autowired
	TipoPersonalizacionRepositorio tipoPersonalizacionRepositorio;
	@Autowired
	AreaPersonalizacionRepositorio areaPersonalizacionRepositorio;
	@Autowired
	PosiblePersonalizacionRepositorio posiblePersonalizacionRepositorio;
	@Autowired
	ProductoBaseRepositorio productoBaseRepositorio;
	@Autowired
	PersonalizacionRepositorio personalizacionRepositorio;
	@Autowired
	MetodoPagoRepositorio metodoPagoRepositorio;
	@Autowired
	VendedorRepositorio vendedorRepositorio;
	@Autowired
	PublicacionRepositorio publicacionRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(TpBaseApplication.class, args);
	}

	@Bean
	public CommandLineRunner init() {
		return (args) -> {
			if (args.length > 0) {
				System.out.println(args[0]);
			}

			Categoria catRemeras = categoriaRepositorio.save(new Categoria("REMERAS"));

			TipoPersonalizacion tipoImg = tipoPersonalizacionRepositorio.save(new TipoPersonalizacion("IMAGEN"));
			TipoPersonalizacion tipoTxt = tipoPersonalizacionRepositorio.save(new TipoPersonalizacion("TEXTO"));

			AreaPersonalizacion areaPecho = areaPersonalizacionRepositorio.save(new AreaPersonalizacion("PECHO"));
			AreaPersonalizacion areaEspalda = areaPersonalizacionRepositorio.save(new AreaPersonalizacion("ESPALDA"));

			PosiblePersonalizacion posPechoImg = posiblePersonalizacionRepositorio.save(new PosiblePersonalizacion(areaPecho, tipoImg));
			PosiblePersonalizacion posPechoTxt = posiblePersonalizacionRepositorio.save(new PosiblePersonalizacion(areaPecho, tipoTxt));
			PosiblePersonalizacion posEspaldaImg = posiblePersonalizacionRepositorio.save(new PosiblePersonalizacion(areaEspalda, tipoImg));

			List<PosiblePersonalizacion> listPosiblesPersonalizaciones = new ArrayList<>();
			listPosiblesPersonalizaciones.add(posPechoImg);
			listPosiblesPersonalizaciones.add(posEspaldaImg);

			ProductoBase productoBase = productoBaseRepositorio.save(new ProductoBase(catRemeras, "REMERA SPIDERMAN", "descripcion", 360, 2, listPosiblesPersonalizaciones));

			Personalizacion personalizacionPechoImg = personalizacionRepositorio.save(new Personalizacion(posPechoImg, "imagen de telarania", "linkContenido", 50));

			List<Personalizacion> personalizaciones = new ArrayList<>();
			personalizaciones.add(personalizacionPechoImg);

			MetodoPago metodoPagoEfectivo = metodoPagoRepositorio.save(new MetodoPago(EnumMetodoPago.EFECTIVO));
			MetodoPago metodoPagoCredVisa = metodoPagoRepositorio.save(new MetodoPago(EnumMetodoPago.CREDITO_VISA));

			List<MetodoPago> metodosPagos = new ArrayList<>();
			metodosPagos.add(metodoPagoEfectivo);
			metodosPagos.add(metodoPagoCredVisa);

			Vendedor vendedor1 = vendedorRepositorio.save(new Vendedor("mail@gmail.com", "123", "NIKE", metodosPagos));

			Publicacion publicacion = (new Publicacion(EnumEstado.DISPONIBLE, productoBase, personalizaciones, vendedor1));
			/*
			Integer precioTotal = publicacion.getProductoBase().getPrecioBase();
			for (Personalizacion p : personalizaciones){
				precioTotal = precioTotal + p.getPrecio();
			}
			publicacion.setPrecioTotal(precioTotal); */
			publicacion = publicacionRepositorio.save(publicacion);



		};
	}
}

