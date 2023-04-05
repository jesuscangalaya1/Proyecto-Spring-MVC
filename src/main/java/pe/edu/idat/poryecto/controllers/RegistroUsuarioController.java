package pe.edu.idat.poryecto.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.idat.poryecto.dtos.UsuarioDTO;
import pe.edu.idat.poryecto.service.UsuarioService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registros")
public class RegistroUsuarioController {

    private final UsuarioService usuarioService;


    @ModelAttribute("usuario")
    public UsuarioDTO retornarNuevoUsuarioRegistroDTO() {
        return new UsuarioDTO();
    }

    @GetMapping
    public String mostrarFormularioDeRegistro() {
        return "user/registro";
    }

    @PostMapping
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioDTO registroDTO) {
        usuarioService.guardar(registroDTO);
        return "redirect:/registros?success=true";
    }

}
