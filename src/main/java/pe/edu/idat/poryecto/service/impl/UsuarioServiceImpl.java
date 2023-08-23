package pe.edu.idat.poryecto.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.idat.poryecto.dtos.UsuarioDTO;
import pe.edu.idat.poryecto.persistence.entities.Producto;
import pe.edu.idat.poryecto.persistence.entities.Rol;
import pe.edu.idat.poryecto.persistence.entities.Usuario;
import pe.edu.idat.poryecto.persistence.repositories.UsuarioRepository;
import pe.edu.idat.poryecto.service.UsuarioService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Usuario guardar(UsuarioDTO registroDTO) {
        Usuario usuario = new Usuario(registroDTO.getNombre(),
                registroDTO.getApellido(), registroDTO.getEmail(),
                passwordEncoder.encode(registroDTO.getPassword()), Arrays.asList(new Rol("ROLE_USER")));
        return usuarioRepository.save(usuario);
    }
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    @Override
    public Usuario usuarioById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID de usuario no puede ser nulo");
        }
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findByUsername(String name) {
        return usuarioRepository.findByNombre(name);
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

}

