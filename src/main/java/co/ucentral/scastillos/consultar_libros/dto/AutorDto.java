package co.ucentral.scastillos.consultar_libros.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;


@Builder
public record AutorDto(
        Long id,

        String nombre,

        Integer fechaNacimiento,

        Integer fechaMuerte

) {
}
