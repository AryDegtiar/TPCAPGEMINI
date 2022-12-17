package TPBASE.tpBase;

import TPBASE.tpBase.entidades.actores.Cliente;
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
	@Autowired
	ClienteRepositorio clienteRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(TpBaseApplication.class, args);
	}

	@Bean
	public CommandLineRunner init() {
		return (args) -> {
			if (args.length > 0) {
				System.out.println(args[0]);
			}

			Cliente cliente = clienteRepositorio.save(new Cliente("root@root.com", "123"));

			Categoria catRemeras = categoriaRepositorio.save(new Categoria("REMERAS"));
			Categoria catPantalones = categoriaRepositorio.save(new Categoria("PANTALONES"));

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

			ProductoBase productoBase2 = new ProductoBase(catRemeras, "REMERA SPIDERMAN", "descripcion", 360, 2);
			productoBase2.addPosiblePersonalizacion(posPechoImg);
			productoBase2.addPosiblePersonalizacion(posPechoTxt);
			productoBase2 = productoBaseRepositorio.save(productoBase2);

			Personalizacion personalizacionPechoImg = personalizacionRepositorio.save(new Personalizacion(posPechoImg, "imagen de telarania", "linkContenido", 50));
			Personalizacion personalizacionPechoTxt = personalizacionRepositorio.save(new Personalizacion(posPechoTxt, "texto bambino", "linkContenido", 60));
			Personalizacion personalizacionEspaldaImg = personalizacionRepositorio.save(new Personalizacion(posEspaldaImg, "imagen bob sponja", "linkContenido", 70));

			MetodoPago metodoPagoEfectivo = metodoPagoRepositorio.save(new MetodoPago(EnumMetodoPago.EFECTIVO));
			MetodoPago metodoPagoCredVisa = metodoPagoRepositorio.save(new MetodoPago(EnumMetodoPago.CREDITO_VISA));
			MetodoPago metodoPagoDebVisa = metodoPagoRepositorio.save(new MetodoPago(EnumMetodoPago.DEBITO_VISA));
			MetodoPago metodoPagoPagoFacil = metodoPagoRepositorio.save(new MetodoPago(EnumMetodoPago.PAGOFACIL));
			MetodoPago metodoPagoTransBancaria = metodoPagoRepositorio.save(new MetodoPago(EnumMetodoPago.TRANSFERENCIA_BANCARIA));

			Vendedor vendedor1 = new Vendedor("mail@gmail.com", "123", "NIKE");
			vendedor1.addMetodoPago(metodoPagoEfectivo);
			vendedor1.addMetodoPago(metodoPagoCredVisa);
			vendedor1 = vendedorRepositorio.save(vendedor1);

			Vendedor vendedor2 = new Vendedor("puma@gmail.com", "123", "PUMA");
			vendedor2.addMetodoPago(metodoPagoEfectivo);
			vendedor2.addMetodoPago(metodoPagoTransBancaria);
			vendedor2.addMetodoPago(metodoPagoDebVisa);
			vendedor2.addMetodoPago(metodoPagoPagoFacil);
			vendedor2 = vendedorRepositorio.save(vendedor2);

			Publicacion publicacion = (new Publicacion(EnumEstado.DISPONIBLE, productoBase, vendedor1, "https://http2.mlstatic.com/D_NQ_NP_2X_686172-MLA50151706938_052022-F.webp"));
			publicacion.addPersonalizacion(personalizacionPechoImg);
			publicacion.addPersonalizacion(personalizacionPechoTxt);
			publicacion = publicacionRepositorio.save(publicacion);

			Publicacion publicacion2 = (new Publicacion(EnumEstado.DISPONIBLE, productoBase, vendedor2, "https://http2.mlstatic.com/D_NQ_NP_2X_873157-MLA48728952944_012022-F.webp"));
			publicacion2.addPersonalizacion(personalizacionPechoImg);
			publicacion2.addPersonalizacion(personalizacionPechoTxt);
			publicacion2 = publicacionRepositorio.save(publicacion2);



		};
	}
}

