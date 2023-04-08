package pe.edu.idat.poryecto.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;
import pe.edu.idat.poryecto.config.FileUploadUtil;
import pe.edu.idat.poryecto.dtos.ProductoDTO;
import pe.edu.idat.poryecto.persistence.entities.Producto;
import pe.edu.idat.poryecto.service.ProductoService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;


    @GetMapping("/home")
    public String viewHomePage(Model model) {
        return findPaginated(1, "id", "asc", model);
    }

    @RequestMapping("/indexProducto")
    public String project1(Model model, @Param("palabraClave") String palabraClave) {
        List<Producto> productos = productoService.listProductos(palabraClave);
        model.addAttribute("listProduct", productos);
        model.addAttribute("palabraClave", palabraClave);
        return "producto/indexProducto";
    }

    @RequestMapping("/showNewProductForm")
    public String project2(Model model) {
        Producto producto = new Producto();
        model.addAttribute("producto", producto);
        return "producto/Add_Product";
    }

    @PostMapping("/saveProducto")
    public RedirectView guardarProducto(@ModelAttribute("producto") @Valid ProductoDTO producto,
                                        @RequestParam("image") MultipartFile multipartFile
    ) throws IOException {
        if (producto.getPrecio() == null || producto.getPrecio().describeConstable().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El precio no puede estar vacío.");
        }
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del producto no puede estar vacío.");
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        producto.setFotos(fileName);
        Producto saveProduct = productoService.guardarProducto(producto);
        String upload = "producto-fotos/" + saveProduct.getId();
        FileUploadUtil.saveFile(upload, fileName, multipartFile);
        return new RedirectView("/home", true);
    }


    @GetMapping("/showFormForUpdate/{id}")
    public String updateImage(@PathVariable Long id, Model model) {
        Producto producto = productoService.productoById(id);
        model.addAttribute("producto", producto);
        return "producto/Edit_producto";
    }

    @GetMapping("/deleteProducto/{id}")
    @Transactional
    public String deleteProducto(@PathVariable Long id) {
        productoService.deleteProductoById(id);
        return "redirect:/home";
    }

   @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {

        int pageSize = 3;
        Page<Producto> page = productoService.paginacion(pageNo, pageSize, sortField, sortDir);
        List<Producto> listProduct = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listProduct", listProduct);
        return "producto/indexProducto";

    }


}
