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

import java.util.HashMap;
import java.util.Map;

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
			Categoria catZapatillas = categoriaRepositorio.save(new Categoria("ZAPATILLAS"));

			TipoPersonalizacion tipoImg = tipoPersonalizacionRepositorio.save(new TipoPersonalizacion("IMAGEN"));
			TipoPersonalizacion tipoTxt = tipoPersonalizacionRepositorio.save(new TipoPersonalizacion("TEXTO"));

			AreaPersonalizacion areaPecho = areaPersonalizacionRepositorio.save(new AreaPersonalizacion("PECHO"));
			AreaPersonalizacion areaEspalda = areaPersonalizacionRepositorio.save(new AreaPersonalizacion("ESPALDA"));


			PosiblePersonalizacion posPechoImg = posiblePersonalizacionRepositorio.save(new PosiblePersonalizacion(areaPecho, tipoImg));
			PosiblePersonalizacion posPechoTxt = posiblePersonalizacionRepositorio.save(new PosiblePersonalizacion(areaPecho, tipoTxt));
			PosiblePersonalizacion posEspaldaImg = posiblePersonalizacionRepositorio.save(new PosiblePersonalizacion(areaEspalda, tipoImg));
			PosiblePersonalizacion posEspaldaTxt = posiblePersonalizacionRepositorio.save(new PosiblePersonalizacion(areaEspalda, tipoTxt));

			ProductoBase productoBase = new ProductoBase(catRemeras, "REMERA PECHO ESPALDA IMAGEN", "descripcion", 200, 2);
			productoBase.addPosiblePersonalizacion(posPechoImg);
			productoBase.addPosiblePersonalizacion(posEspaldaImg);
			productoBase = productoBaseRepositorio.save(productoBase);

			ProductoBase productoBase2 = new ProductoBase(catRemeras, "REMERA PECHO IMG TXT", "descripcion", 500, 2);
			productoBase2.addPosiblePersonalizacion(posPechoImg);
			productoBase2.addPosiblePersonalizacion(posPechoTxt);
			productoBase2 = productoBaseRepositorio.save(productoBase2);

			ProductoBase productoBase3 = new ProductoBase(catPantalones, "PANTALON PECHO ESPALDA IMG TEX", "descripcion", 600, 2);
			productoBase3.addPosiblePersonalizacion(posPechoImg);
			productoBase3.addPosiblePersonalizacion(posPechoTxt);
			productoBase3.addPosiblePersonalizacion(posEspaldaImg);
			productoBase3 = productoBaseRepositorio.save(productoBase3);

			ProductoBase productoBase4 = new ProductoBase(catPantalones, "PANTALON PECHO ESPALDA IMG", "descripcion", 900, 4);
			productoBase4.addPosiblePersonalizacion(posPechoImg);
			productoBase4.addPosiblePersonalizacion(posEspaldaImg);
			productoBase4 = productoBaseRepositorio.save(productoBase4);

			ProductoBase productoBase5 = new ProductoBase(catZapatillas, "ZAPATILLA PECHO TEX", "descripcion", 600, 2);;
			productoBase5.addPosiblePersonalizacion(posPechoTxt);
			productoBase5 = productoBaseRepositorio.save(productoBase5);

			ProductoBase productoBase6 = new ProductoBase(catZapatillas, "ZAPATILLA PECHO ESPALDA TEX", "descripcion", 300, 2);
			productoBase6.addPosiblePersonalizacion(posEspaldaTxt);
			productoBase6.addPosiblePersonalizacion(posPechoTxt);
			productoBase6 = productoBaseRepositorio.save(productoBase6);

			Personalizacion personalizacionPechoImg = personalizacionRepositorio.save(new Personalizacion(posPechoImg, "imagen de telarania", "linkContenido", 50));
			Personalizacion personalizacionPechoTxt = personalizacionRepositorio.save(new Personalizacion(posPechoTxt, "texto bambino", "linkContenido", 60));
			Personalizacion personalizacionEspaldaImg = personalizacionRepositorio.save(new Personalizacion(posEspaldaImg, "imagen bob sponja", "linkContenido", 70));
			Personalizacion personalizacionEspaldaImg2 = personalizacionRepositorio.save(new Personalizacion(posEspaldaImg, "ñandu", "linkContenido", 100));

			Personalizacion persoProd4 = personalizacionRepositorio.save(new Personalizacion(posPechoImg, "imagen de ñandu", "linkContenido", 50));
			Personalizacion persoProd5 = personalizacionRepositorio.save(new Personalizacion(posPechoTxt, "texto de cordon", "linkContenido", 10));
			Personalizacion persoProd6 = personalizacionRepositorio.save(new Personalizacion(posEspaldaTxt, "texto de marca", "linkContenido", 80));
			Personalizacion persoProd7_1 = personalizacionRepositorio.save(new Personalizacion(posPechoImg, "Imangen logo capgemini", "linkContenido", 80));
			Personalizacion persoProd7_2 = personalizacionRepositorio.save(new Personalizacion(posEspaldaImg, "Imangen logo capgemini atras", "linkContenido", 10));

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

			Publicacion publicacion = (new Publicacion(EnumEstado.DISPONIBLE, productoBase, vendedor1, "https://http2.mlstatic.com/D_NQ_NP_2X_686172-MLA50151706938_052022-F.webp", "Spiderman blanca", "Remera blanca con tela 100% algodon de excelente calidad, todas las tallas disponibles"));
			publicacion.addPersonalizacion(personalizacionEspaldaImg);
			publicacion = publicacionRepositorio.save(publicacion);

			Publicacion publicacion2 = (new Publicacion(EnumEstado.PAUSADO, productoBase2, vendedor2, "https://http2.mlstatic.com/D_NQ_NP_2X_873157-MLA48728952944_012022-F.webp", "Spiderman roja", "Remera roja con tela 100% algodon de excelente calidad, todas las tallas disponibles"));
			publicacion2.addPersonalizacion(personalizacionPechoImg);
			publicacion2.addPersonalizacion(personalizacionPechoTxt);
			publicacion2 = publicacionRepositorio.save(publicacion2);

			Publicacion publicacion5 = (new Publicacion(EnumEstado.DISPONIBLE, productoBase5, vendedor1, "https://www.moov.com.ar/on/demandware.static/-/Sites-dabra-catalog/default/dw79114c7c/products/NI_CZ4149-200/NI_CZ4149-200-1.JPG", "Air Max Furyosa", "Air Max Furyosa con tela 100% algodon de excelente calidad, todas las tallas disponibles"));
			publicacion5.addPersonalizacion(persoProd5);
			publicacion5 = publicacionRepositorio.save(publicacion5);

			Publicacion publicacion6 = (new Publicacion(EnumEstado.DISPONIBLE, productoBase6, vendedor2, "https://www.moov.com.ar/on/demandware.static/-/Sites-dabra-catalog/default/dw3f2de115/products/PU_380591-08/PU_380591-08-1.JPG", "Future Rider Twofold", "Future Rider Twofold con tela 100% algodon de excelente calidad, todas las tallas disponibles"));
			publicacion6.addPersonalizacion(persoProd6);
			publicacion6 = publicacionRepositorio.save(publicacion6);

			Publicacion publicacion3 = (new Publicacion(EnumEstado.DISPONIBLE, productoBase3, vendedor1, "https://securtexweb.com/wp-content/uploads/2021/05/pcemGaucho.png", "Cargo marron", "Cargo marron con tela 100% algodon de excelente calidad, todas las tallas disponibles"));
			publicacion3.addPersonalizacion(personalizacionEspaldaImg2);
			publicacion3 = publicacionRepositorio.save(publicacion3);

			Publicacion publicacion4 = (new Publicacion(EnumEstado.DISPONIBLE, productoBase4, vendedor2, "https://http2.mlstatic.com/D_NQ_NP_986919-MLA51037796775_082022-O.webp", "Jean roto", "Jean roto con tela 100% algodon de excelente calidad, todas las tallas disponibles"));
			publicacion4.addPersonalizacion(persoProd4);
			publicacion4 = publicacionRepositorio.save(publicacion4);



		};
	}
}

