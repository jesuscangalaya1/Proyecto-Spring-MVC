package pe.edu.idat.poryecto.service;

import org.springframework.data.domain.Page;
import pe.edu.idat.poryecto.dtos.ProductoDTO;
import pe.edu.idat.poryecto.persistence.entities.Producto;

import java.util.List;

public interface ProductoService {

    Producto guardarProducto(ProductoDTO productoDTO);
    Producto productoById(Long id);
    void deleteProductoById(Long id);
    List<Producto> listProductos(String palabraClave);
    List<Producto> listCatalogo(Producto producto);
    Page<Producto> paginacionCatalogo(int pageNo, int pageSize, String sortField, String sortDir, Producto producto);

    Page<Producto> paginacion(int pageNo, int pageSize, String sortField,String sortDir);
}
