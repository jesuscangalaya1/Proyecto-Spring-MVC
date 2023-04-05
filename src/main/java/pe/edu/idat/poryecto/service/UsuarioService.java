package pe.edu.idat.poryecto.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import pe.edu.idat.poryecto.dtos.ProductoDTO;
import pe.edu.idat.poryecto.dtos.UsuarioDTO;
import pe.edu.idat.poryecto.persistence.entities.Usuario;

import java.util.List;


public interface UsuarioService{

    Usuario guardar(UsuarioDTO registroDTO);

    List<Usuario> listarUsuarios();
    Usuario usuarioById(Long id);
}
