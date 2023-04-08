package pe.edu.idat.poryecto.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Compra compras;



    @Transient
    public String getPhotosImagePath() {
        if (fotos == null) return null;
        return "/producto-fotos/" + id + "/" + fotos;
    }
    public Producto(Long id, String nombre, Double precio, String fotos, Integer cantidad, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fotos = fotos;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }


}
