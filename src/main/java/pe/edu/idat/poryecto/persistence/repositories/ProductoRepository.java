package pe.edu.idat.poryecto.persistence.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.idat.poryecto.persistence.entities.Producto;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT producto FROM Producto producto WHERE CONCAT(producto.id,'',producto.nombre,'',producto.precio) LIKE %?1%")
    List<Producto> buscar(String palabraClave);

    Producto findByNombre(String nombre);

    Page<Producto> findAllByDeletedFalse(Pageable pageable);
}
