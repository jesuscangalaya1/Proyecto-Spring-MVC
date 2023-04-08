package pe.edu.idat.poryecto.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductoDTO {

    private Long id;
    private String nombre;
    private Double precio;
    private String fotos;
    private Integer cantidad;
    private String descripcion;

}
