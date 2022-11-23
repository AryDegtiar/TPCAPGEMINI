package TPBASE.tpBase;

import TPBASE.tpBase.entidades.productos.Categoria;
import TPBASE.tpBase.entidades.repositorios.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TpBaseApplication {

	@Autowired
	CategoriaRepositorio categoriaRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(TpBaseApplication.class, args);
	}

	@Bean
	public CommandLineRunner init() {
		return (args) -> {
			if (args.length > 0) {
				System.out.println(args[0]);
			}

			categoriaRepositorio.save(new Categoria("Remera"));
			categoriaRepositorio.save(new Categoria("Campera"));
			categoriaRepositorio.save(new Categoria("Gorra"));

		};
	}
}

