package co.ucentral.scastillos.consultar_libros;

import co.ucentral.scastillos.consultar_libros.principal.Principal;
import co.ucentral.scastillos.consultar_libros.repositorios.RepoAutor;
import co.ucentral.scastillos.consultar_libros.repositorios.RepoLibro;
import co.ucentral.scastillos.consultar_libros.servicios.ServicioLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ConsultarLibrosApplication implements CommandLineRunner {
	@Autowired
	RepoLibro repoLibro;
	@Autowired
	RepoAutor repoAutor;
	@Autowired
	ServicioLibros servicioLibros;

	public static void main(String[] args) {
		SpringApplication.run(ConsultarLibrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal(repoLibro,repoAutor,servicioLibros);
		principal.muestraElMenu();

	}

}
