package TPBASE.tpBase;

import TPBASE.tpBase.entidades.actores.Vendedor;
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

			ProductoBase productoBase = new ProductoBase(catRemeras, "REMERA SPIDERMAN", "descripcion", 360, 2);
			productoBase.addPosiblePersonalizacion(posPechoImg);
			productoBase.addPosiblePersonalizacion(posEspaldaImg);
			productoBase = productoBaseRepositorio.save(productoBase);

			Personalizacion personalizacionPechoImg = personalizacionRepositorio.save(new Personalizacion(posPechoImg, "imagen de telarania", "linkContenido", 50));
			Personalizacion personalizacionPechoTxt = personalizacionRepositorio.save(new Personalizacion(posPechoTxt, "texto bambino", "linkContenido", 50));

			MetodoPago metodoPagoEfectivo = metodoPagoRepositorio.save(new MetodoPago(EnumMetodoPago.EFECTIVO));
			MetodoPago metodoPagoCredVisa = metodoPagoRepositorio.save(new MetodoPago(EnumMetodoPago.CREDITO_VISA));

			Vendedor vendedor1 = new Vendedor("mail@gmail.com", "123", "NIKE");
			vendedor1.addMetodoPago(metodoPagoEfectivo);
			vendedor1.addMetodoPago(metodoPagoCredVisa);
			vendedor1 = vendedorRepositorio.save(vendedor1);

			Publicacion publicacion = (new Publicacion(EnumEstado.DISPONIBLE, productoBase, vendedor1));
			publicacion.addPersonalizacion(personalizacionPechoImg);
			publicacion.addPersonalizacion(personalizacionPechoTxt);
			publicacion = publicacionRepositorio.save(publicacion);


		};
	}
}

