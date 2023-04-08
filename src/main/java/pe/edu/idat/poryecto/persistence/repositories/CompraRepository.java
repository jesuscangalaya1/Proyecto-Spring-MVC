package pe.edu.idat.poryecto.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.idat.poryecto.persistence.entities.Compra;
import pe.edu.idat.poryecto.persistence.entities.Usuario;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Long> {

    List<Compra> findByUsuario(Usuario usuario);

}
