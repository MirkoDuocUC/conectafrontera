package cl.duoc.conectafrontera.service.impl;

import cl.duoc.conectafrontera.dto.UsuarioRequestDTO;
import cl.duoc.conectafrontera.dto.UsuarioResponseDTO;
import cl.duoc.conectafrontera.entity.*;
import cl.duoc.conectafrontera.repository.*;
import cl.duoc.conectafrontera.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    private final AdministradorRepository administradorRepository;
    private final FuncionarioAduanaRepository funcionarioAduanaRepository;
    private final InspectorSAGRepository inspectorSAGRepository;
    private final ViajeroRepository viajeroRepository;

    @Override
    @Transactional
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByCorreo(dto.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        usuario.setRol(dto.getRol());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // Crear la entidad correspondiente al rol
        switch (dto.getRol()) {
            case ADMINISTRADOR -> {
                Administrador admin = new Administrador();
                admin.setUsuario(usuarioGuardado);
                administradorRepository.save(admin);
            }
            case FUNCIONARIO_ADUANA -> {
                FuncionarioAduana func = new FuncionarioAduana();
                func.setUsuario(usuarioGuardado);
                funcionarioAduanaRepository.save(func);
            }
            case INSPECTOR_SAG -> {
                InspectorSAG inspector = new InspectorSAG();
                inspector.setUsuario(usuarioGuardado);
                inspectorSAGRepository.save(inspector);
            }
            case VIAJERO -> {
                Viajero viajero = new Viajero();
                viajero.setUsuario(usuarioGuardado);
                viajeroRepository.save(viajero); // Guardar la entidad Viajero
            }
        }

        return mapToDto(usuarioGuardado);
    }


    @Override
    public List<UsuarioResponseDTO> obtenerTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    @Transactional
    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        if (dto.getContrasena() != null && !dto.getContrasena().isBlank()) {
            usuario.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        }
        usuario.setRol(dto.getRol());

        return mapToDto(usuarioRepository.save(usuario));
    }

    @Override
    @Transactional
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Eliminar entidad correspondiente antes del usuario
        switch (usuario.getRol()) {
            case ADMINISTRADOR -> administradorRepository.deleteByUsuario(usuario);
            case FUNCIONARIO_ADUANA -> funcionarioAduanaRepository.deleteByUsuario(usuario);
            case INSPECTOR_SAG -> inspectorSAGRepository.deleteByUsuario(usuario);
            case VIAJERO -> viajeroRepository.deleteByUsuario(usuario); // Eliminar el Viajero
        }

        usuarioRepository.delete(usuario);
    }


    private UsuarioResponseDTO mapToDto(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setRol(usuario.getRol());
        return dto;
    }
}
