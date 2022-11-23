package TPBASE.tpBase;

import TPBASE.tpBase.entidades.productos.*;
import TPBASE.tpBase.entidades.productos.Categoria;
import TPBASE.tpBase.entidades.repositorios.AreaPersonalizacionRepositorio;
import TPBASE.tpBase.entidades.repositorios.ProductoBaseRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TpBaseApplication {

	@Autowired
	ProductoBaseRepositorio productoBaseRepositorio;

	@Autowired
	AreaPersonalizacionRepositorio areaPersonalizacionRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(TpBaseApplication.class, args);
	}

	@Bean
	public CommandLineRunner init() {
		return (args) -> {
			if (args.length > 0) {
				System.out.println(args[0]);
			}

			Categoria categoria = new Categoria("Remera");

			TipoPersonalizacion tipo1 = new TipoPersonalizacion("imagen");
			TipoPersonalizacion tipo2 = new TipoPersonalizacion("texto");
			List<TipoPersonalizacion> tipos = new ArrayList<>();
			tipos.add(tipo1);
			tipos.add(tipo2);

			AreaPersonalizacion area1 = new AreaPersonalizacion("Pecho", tipos);
			AreaPersonalizacion area2 = new AreaPersonalizacion("Espalda", tipos);
			List<AreaPersonalizacion> areas = new ArrayList<>();
			areas.add(area1);
			areas.add(area2);

			ProductoBase productoBase = new ProductoBase(categoria,"Remera Capitan Amercia S", "Rermera 100% tela algodon re pro",200, 5, areas);

			//areaRepositorio.save(area1);

			//productoBaseRepositorio.save(new ProductoBase(categoria,"Remera Capitan Amercia S", "Rermera 100% tela algodon re pro",200, 5, areas));

			//productoBaseRepositorio.save(productoBase);

			System.out.println(productoBase);

		};
	}
}

