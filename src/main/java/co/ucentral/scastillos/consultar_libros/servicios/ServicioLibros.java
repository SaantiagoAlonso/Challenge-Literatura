package co.ucentral.scastillos.consultar_libros.servicios;

import co.ucentral.scastillos.consultar_libros.dto.AutorDto;
import co.ucentral.scastillos.consultar_libros.dto.LibroDto;
import co.ucentral.scastillos.consultar_libros.entidades.Autores;
import co.ucentral.scastillos.consultar_libros.entidades.Libros;
import co.ucentral.scastillos.consultar_libros.repositorios.RepoAutor;
import co.ucentral.scastillos.consultar_libros.repositorios.RepoLibro;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicioLibros {


    RepoLibro repoLibro;

    RepoAutor repoAutor;
    public ServicioLibros(RepoLibro repoLibro, RepoAutor repoAutor) {
        this.repoAutor = repoAutor;
        this.repoLibro = repoLibro;
    }
    public void guardarLibro(LibroDto libro, AutorDto autor){
        if(repoLibro.existsById(libro.id_libro())){
            System.out.println("--------------------------------------------------------");
            System.out.println("Este libro ya se encuentra guardado en la base de Datos ");
            System.out.println("Puede Consultarlo usando la opcion 2 ");
            System.out.println("--------------------------------------------------------");
        }else{
            Optional<Autores> optionalAutor = repoAutor.findBynombre(libro.autor().nombre());
            if(optionalAutor.isPresent()){
                Autores autorExistente  = optionalAutor.get();

                Libros newLibro = Libros.builder()
                        .id_libro(libro.id_libro())
                        .titulo(libro.titulo())
                        .idiomas(libro.idiomas())
                        .descargas(libro.descargas())
                        .build();

                newLibro.setAutor(autorExistente);
                repoLibro.save(newLibro);
            }else{

                Autores nuevoAutor = Autores.builder()
                        .nombre(autor.nombre())
                        .fechaMuerte(autor.fechaMuerte())
                        .fechaNacimiento(autor.fechaNacimiento())
                        .build();

                repoAutor.save(nuevoAutor);


                Libros newLibro = Libros.builder()
                        .id_libro(libro.id_libro())
                        .titulo(libro.titulo())
                        .idiomas(libro.idiomas())
                        .descargas(libro.descargas())
                        .build();
                newLibro.setAutor(nuevoAutor);

                repoLibro.save(newLibro);

            }
        }
    }

    public List<Libros> listarLibros() {
        return repoLibro.findAll();
    }

    public List<Autores> listarAutores() {
        return repoAutor.findAll();
    }

    public List<Autores> listarAutoresVivos(int anio) {
        return repoAutor.autoresPorAnio(anio);
    }

   /* public List<Libros> listarLibrosIdioma(String idioma) {
        return repoLibro.findAll().stream()
                .filter(l -> l.getIdiomas().contains(idioma))
                .collect(Collectors.toList());
    }*/

    public List<Libros> listarLibrosIdioma(List<String> idioma) {
        return repoLibro.findLibrosByIdioma(idioma);
    }



}
