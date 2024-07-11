package co.ucentral.scastillos.consultar_libros.entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Libros {

    @Id
    private Long id_libro;

    @Column
    private String titulo;

    @Column
    private Integer descargas;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autores autor;

    @Column
    //@ElementCollection
    private List<String> idiomas;

    @Override
    public String toString() {
        return "-------------------------------" + "\n" +
                " id_libro=" + id_libro + "\n" +
                " titulo='" + titulo + '\n' +
                " descargas=" + descargas + '\n' +
                " autor=" + autor.getNombre() + '\n' +
                " idiomas=" + idiomas + '\n' ;
    }
}
