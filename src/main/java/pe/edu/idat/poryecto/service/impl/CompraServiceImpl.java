package pe.edu.idat.poryecto.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.idat.poryecto.persistence.entities.Compra;
import pe.edu.idat.poryecto.persistence.entities.Producto;
import pe.edu.idat.poryecto.persistence.entities.Usuario;
import pe.edu.idat.poryecto.persistence.repositories.CompraRepository;
import pe.edu.idat.poryecto.persistence.repositories.ProductoRepository;
import pe.edu.idat.poryecto.persistence.repositories.UsuarioRepository;
import pe.edu.idat.poryecto.service.CompraService;
import pe.edu.idat.poryecto.service.ProductoService;
import pe.edu.idat.poryecto.service.UsuarioService;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    private final ProductoService productoService;

    @Override
    public Compra insertar(Compra c, Usuario u) {
        c.setUsuario(u);
        return compraRepository.save(c);
    }

    @Override
    public Producto addProductoCompra(Producto p, Compra c) {
        p.setCompras(c);
        return productoService.saveProducto(p);
    }

    @Override
    public Compra insertarC(Compra c) {
        return compraRepository.save(c);
    }
    @Override
    public Compra insertarId(Compra compra, Long idUsuario) {
        Usuario usuario = usuarioService.usuarioById(idUsuario);
        compra.setUsuario(usuario);
        return compraRepository.save(compra);

    }



    @Override
    public Compra findById(Long id) {
        return compraRepository.findById(id).get();
    }
}
