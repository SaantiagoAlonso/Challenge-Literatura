package co.ucentral.scastillos.consultar_libros.principal;

import co.ucentral.scastillos.consultar_libros.dto.AutorDto;
import co.ucentral.scastillos.consultar_libros.dto.LibroDto;
import co.ucentral.scastillos.consultar_libros.repositorios.RepoAutor;
import co.ucentral.scastillos.consultar_libros.repositorios.RepoLibro;
import co.ucentral.scastillos.consultar_libros.servicios.ConsumirApi;
import co.ucentral.scastillos.consultar_libros.servicios.ConvierteDatos;
import co.ucentral.scastillos.consultar_libros.servicios.ServicioLibros;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@ComponentScan
public class Principal {

    RepoLibro repoLibro;
    RepoAutor repoAutor;
    ServicioLibros servicioLibros;

    private Scanner teclado = new Scanner(System.in);
    ConsumirApi api = new ConsumirApi();

    public Principal(RepoLibro repoLibro, RepoAutor repoAutor, ServicioLibros servicioLibros) {
        this.repoLibro = repoLibro;
        this.repoAutor = repoAutor;
        this.servicioLibros = new ServicioLibros(repoLibro,repoAutor);
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {

            var menu = """
                    1 - Buscar Libro
                    2 - Listar Libros Guardados
                    3 - Listar Autores Guardados
                    4 - Listar Autores Vivos en un Determinado Año
                    5 - Listar Libros por Idioma      
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAño();
                    break;
                case 5:
                    listarLibroPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void getDatosLibros() {
        ConvierteDatos convierteDatos = new ConvierteDatos();
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        String json = api.obtenerDatos("http://gutendex.com/books/?search=" + nombreLibro.replace(" ", "+"));
        AutorDto autor = convierteDatos.extraerAutor(convierteDatos.convertir(json));
        LibroDto libro = convierteDatos.extraerLibro(convierteDatos.convertir(json),autor);
        //System.out.println(libro.toString()+ " " + autor.toString());
        System.out.println("-----------------------");
        System.out.println(String.format(
                """
                Titulo: %s 
                Autor: %s
                Idioma: %s
                Descargas: %s 
                """,
                libro.titulo(), libro.autor().nombre(), libro.idiomas(), libro.descargas()
        ));
        servicioLibros.guardarLibro(libro,autor);

    }

    private void buscarLibro() {
        getDatosLibros();

    }
    private void listarLibrosRegistrados() {
        System.out.println(servicioLibros.listarLibros().toString());
    }
    private void listarAutoresRegistrados() {
        System.out.println(servicioLibros.listarAutores());

    }

    private void listarAutoresVivosPorAño() {
        System.out.println("ingrese un año");
        int anio = teclado.nextInt();
        //System.out.println(servicioLibros.listarAutoresVivos(anio));
        System.out.println(servicioLibros.listarAutoresVivos(anio).toString());
    }
    private void listarLibroPorIdioma() {
        System.out.println("ingrese idioma");
        String prefijo = teclado.next();
        List<String> idioma = new ArrayList<>();
        idioma.add(prefijo);
        System.out.println(servicioLibros.listarLibrosIdioma(idioma));
    }
}
