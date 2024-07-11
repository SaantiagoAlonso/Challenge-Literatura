package co.ucentral.scastillos.consultar_libros.repositorios;

import co.ucentral.scastillos.consultar_libros.entidades.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoLibro extends JpaRepository<Libros,Long> {
    @Query("SELECT l FROM Libros l WHERE :idioma = l.idiomas")
    List<Libros> findLibrosByIdioma(List<String> idioma);

}
