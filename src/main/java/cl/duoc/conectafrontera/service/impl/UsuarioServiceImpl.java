package cl.duoc.conectafrontera.service.impl;

import cl.duoc.conectafrontera.dto.UsuarioRequestDTO;
import cl.duoc.conectafrontera.dto.UsuarioResponseDTO;
import cl.duoc.conectafrontera.entity.Usuario;
import cl.duoc.conectafrontera.repository.UsuarioRepository;
import cl.duoc.conectafrontera.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder; // Inyectamos el PasswordEncoder

    @Override
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto) {
        // Encriptar la contraseña antes de guardarla
        String contrasenaEncriptada = passwordEncoder.encode(dto.getContrasena());

        Usuario usuario = Usuario.builder()
                .nombre(dto.getNombre())
                .correo(dto.getCorreo())
                .contrasena(contrasenaEncriptada) // Guardamos la contraseña encriptada
                .rol(dto.getRol())
                .build();

        usuario = usuarioRepository.save(usuario);
        return toResponseDTO(usuario);
    }

    @Override
    public List<UsuarioResponseDTO> obtenerTodos() {
        return usuarioRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    @Override
    public UsuarioResponseDTO obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return toResponseDTO(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setRol(usuario.getRol());
        return dto;
    }
}
