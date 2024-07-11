package co.ucentral.scastillos.consultar_libros.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Autores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private Integer fechaNacimiento;

    @Column
    private Integer fechaMuerte;

    @Override
    public String toString() {
        return  "---------------------------------" + "\n" +
                "  id: " + id + "\n" +
                " Nombre: " + nombre + '\n' +
                " fechaNacimiento: " + fechaNacimiento + "\n" +
                " fechaMuerte: " + fechaMuerte +"\n"
                ;
    }
}
