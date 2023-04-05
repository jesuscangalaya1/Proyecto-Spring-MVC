package pe.edu.idat.poryecto.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.poryecto.persistence.entities.Producto;
import pe.edu.idat.poryecto.service.ProductoService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CatalogoController {

    private final ProductoService productoService;

    @GetMapping("/catalogo")
    public String catalogo(Model model, Producto producto) {
        List<Producto> productos = productoService.listCatalogo(producto);
        model.addAttribute("listCatalogo", productos);
        return "user/catalogoProductos";
    }

    @GetMapping("/catalogoView/{id}")
    public String catalogoView(@PathVariable Long id, Model model) {
        Producto producto = productoService.productoById(id);
        model.addAttribute("producto", producto);
        return "user/verCatalogo";
    }

    @PostMapping("/addCarrito")
    public String detalleCompra(@ModelAttribute("id") Long id, @RequestParam("cantidad") int cantidad, Model model) {
        Producto producto = productoService.productoById(id);
        double total = producto.getPrecio() * cantidad;
        model.addAttribute("producto", producto);
        model.addAttribute("cantidad", cantidad);
        model.addAttribute("total", total);
        return "user/detalleCompra";
    }

}
