package co.ucentral.scastillos.consultar_libros.dto;

import co.ucentral.scastillos.consultar_libros.entidades.Autores;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;



@Builder
public record LibroDto(
        Long id_libro,

        String titulo,

        Integer descargas,

        AutorDto autor,

        List<String> idiomas

) {
}
