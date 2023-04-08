package pe.edu.idat.poryecto.service;

import pe.edu.idat.poryecto.persistence.entities.Compra;
import pe.edu.idat.poryecto.persistence.entities.Producto;
import pe.edu.idat.poryecto.persistence.entities.Usuario;

public interface CompraService {

    Compra insertar(Compra c, Usuario u);
    Producto addProductoCompra(Producto p, Compra c);
    public Compra insertarId(Compra compra, Long idUsuario);
    Compra insertarC(Compra c);
    Compra findById(Long id);

}
