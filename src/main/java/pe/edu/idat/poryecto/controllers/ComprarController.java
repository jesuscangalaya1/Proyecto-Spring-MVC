package pe.edu.idat.poryecto.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.poryecto.persistence.entities.Compra;
import pe.edu.idat.poryecto.persistence.entities.Producto;
import pe.edu.idat.poryecto.persistence.entities.Usuario;
import pe.edu.idat.poryecto.service.CompraService;
import pe.edu.idat.poryecto.service.ProductoService;
import pe.edu.idat.poryecto.service.UsuarioService;

import java.security.Principal;
import java.util.Collections;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class ComprarController {

    private final CompraService compraService;
    private final UsuarioService usuarioService;
    private final ProductoService productoService;

    @PostMapping("/procesar")
    public String procesarCompra(@RequestParam(name = "idProducto") Long idProducto,
                                 @RequestParam(name = "cantidad") Integer cantidad,
                                 Principal principal) {
        String email = principal.getName();
        Usuario usuario = usuarioService.findByEmail(email);
        // Obtener el producto a comprar
        Producto producto = productoService.productoById(idProducto);

        // Calcular el precio total
        double total = cantidad * producto.getPrecio();

        // Crear la compra y asignar el usuario y producto comprado
        Compra compra = new Compra();
        compra.setFechaCompra(new Date());
        compra.setUsuario(usuario);
        compra.setProducto(producto);
        compra.setCantidad(cantidad);
        compra.setPrecio(producto.getPrecio());
        compra.setTotal(total);

        // Guardar la compra en la base de datos
        compraService.insertarId(compra, usuario.getId());

        // Actualizar el objeto Producto con la relación de la compra y el usuario
        producto.setCompras(compra);
        producto.setUsuario(usuario);

        // Guardar el producto en la base de datos
        productoService.saveProducto(producto);

        return "redirect:/factura/" + compra.getId();
    }

    @GetMapping("/factura/{id}")
    public String mostrarFactura(@PathVariable Long id, Model model) {
        Compra compra = compraService.findById(id);
        if (compra == null) {
            return "redirect:/";
        }
        model.addAttribute("compra", compra);
        model.addAttribute("productos", compra.getProducto());
        model.addAttribute("total_compra", compra.getTotal());
        return "user/FacturaCompras";
    }

}
