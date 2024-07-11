package co.ucentral.scastillos.consultar_libros.repositorios;

import co.ucentral.scastillos.consultar_libros.entidades.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepoAutor extends JpaRepository<Autores,Long> {
    Optional<Autores> findBynombre(String nombre);

    @Query("SELECT a FROM  Autores a Where :anio >= a.fechaNacimiento AND :anio < a.fechaMuerte")
    List<Autores> autoresPorAnio(int anio);
}
