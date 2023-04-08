package pe.edu.idat.poryecto.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import pe.edu.idat.poryecto.dtos.ProductoDTO;
import pe.edu.idat.poryecto.persistence.entities.Producto;
import pe.edu.idat.poryecto.persistence.repositories.ProductoRepository;
import pe.edu.idat.poryecto.service.ProductoService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public Producto guardarProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto(
                productoDTO.getId(),
                productoDTO.getNombre(),
                productoDTO.getPrecio(),
                productoDTO.getFotos(),
                productoDTO.getCantidad(),
                productoDTO.getDescripcion()
                );
        return productoRepository.save(producto);
    }

    @Override
    public Producto productoById(Long id) {
        Optional<Producto> optional = productoRepository.findById(id);
        Producto producto = null;
        if (optional.isPresent()){
            producto = optional.get();
        }else {
            throw new RuntimeException("Producto No encontrado por id::"+id);
        }
        return producto;
    }

    @Override
    public void deleteProductoById(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> listProductos(String palabraClave) {
        if (palabraClave != null){
            return productoRepository.buscar(palabraClave);
        }else
            return productoRepository.findAll();
    }

    @Override
    public List<Producto> listCatalogo(Producto producto) {
        return productoRepository.findAll();
    }

    @Override
    public Page<Producto> paginacion(int pageNo, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);
        return productoRepository.findAll(pageable);
    }

    @Override
    public Producto saveProducto(Producto p) {
        return productoRepository.save(p);
    }

    @Override
    public Page<Producto> paginacionCatalogo(int pageNo, int pageSize, String sortField, String sortDir, Producto producto) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Producto> example = Example.of(producto, matcher);
        Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);
        return productoRepository.findAll(example, pageable);
    }

}
