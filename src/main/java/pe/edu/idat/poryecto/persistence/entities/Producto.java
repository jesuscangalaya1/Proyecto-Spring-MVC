package pe.edu.idat.poryecto.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Double precio;

    private String fotos;
    private Integer cantidad;
    private String descripcion;


    @Transient
    public String getPhotosImagePath() {
        if (fotos == null) return null;
        return "/producto-fotos/" + id + "/" + fotos;
    }

}
