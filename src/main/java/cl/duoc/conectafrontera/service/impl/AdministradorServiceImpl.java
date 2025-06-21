package cl.duoc.conectafrontera.service.impl;

import cl.duoc.conectafrontera.dto.AdministradorRequestDTO;
import cl.duoc.conectafrontera.dto.AdministradorResponseDTO;
import cl.duoc.conectafrontera.entity.Administrador;
import cl.duoc.conectafrontera.entity.Usuario;
import cl.duoc.conectafrontera.repository.AdministradorRepository;
import cl.duoc.conectafrontera.repository.UsuarioRepository;
import cl.duoc.conectafrontera.service.AdministradorService;
import cl.duoc.conectafrontera.util.RolUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministradorServiceImpl implements AdministradorService {

    private final UsuarioRepository usuarioRepository;
    private final AdministradorRepository administradorRepository;

    @Override
    @Transactional
    public AdministradorResponseDTO registrarAdministrador(AdministradorRequestDTO dto) {
        Usuario nuevoUsuario = Usuario.builder()
                .nombre(dto.getNombre())
                .correo(dto.getCorreo())
                .contrasena(dto.getContrasena())
                .rol(RolUsuario.ADMINISTRADOR)
                .build();

        nuevoUsuario = usuarioRepository.save(nuevoUsuario);

        Administrador administrador = Administrador.builder()
                .usuario(nuevoUsuario)
                .nivelPermiso(dto.getNivelPermiso())
                .build();

        administrador = administradorRepository.save(administrador);

        return toDTO(administrador);
    }

    @Override
    public List<AdministradorResponseDTO> obtenerAdministradores() {
        return administradorRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public AdministradorResponseDTO actualizarAdministrador(Long id, AdministradorRequestDTO dto) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        Usuario usuario = administrador.getUsuario();
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(dto.getContrasena());
        usuarioRepository.save(usuario);

        administrador.setNivelPermiso(dto.getNivelPermiso());
        administradorRepository.save(administrador);

        return toDTO(administrador);
    }

    @Override
    public void eliminarAdministrador(Long id) {
        administradorRepository.deleteById(id);
        usuarioRepository.deleteById(id);
    }

    private AdministradorResponseDTO toDTO(Administrador administrador) {
        AdministradorResponseDTO dto = new AdministradorResponseDTO();
        dto.setIdUsuario(administrador.getIdUsuario());
        dto.setNombre(administrador.getUsuario().getNombre());
        dto.setCorreo(administrador.getUsuario().getCorreo());
        dto.setNivelPermiso(administrador.getNivelPermiso());
        return dto;
    }
}
