package cl.duoc.conectafrontera.service.impl;

import cl.duoc.conectafrontera.dto.FuncionarioAduanaRequestDTO;
import cl.duoc.conectafrontera.dto.FuncionarioAduanaResponseDTO;
import cl.duoc.conectafrontera.entity.FuncionarioAduana;
import cl.duoc.conectafrontera.entity.Usuario;
import cl.duoc.conectafrontera.repository.FuncionarioAduanaRepository;
import cl.duoc.conectafrontera.repository.UsuarioRepository;
import cl.duoc.conectafrontera.service.FuncionarioAduanaService;
import cl.duoc.conectafrontera.util.RolUsuario;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioAduanaServiceImpl implements FuncionarioAduanaService {

    private final FuncionarioAduanaRepository funcionarioAduanaRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public FuncionarioAduanaResponseDTO crear(FuncionarioAduanaRequestDTO dto) {
        if (usuarioRepository.findByCorreo(dto.getCorreo()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con ese correo.");
        }

        Usuario usuario = Usuario.builder()
                .nombre(dto.getNombre())
                .correo(dto.getCorreo())
                .contrasena(dto.getContrasena())
                .rol(RolUsuario.FUNCIONARIO_ADUANA)
                .build();

        usuario = usuarioRepository.save(usuario);

        FuncionarioAduana funcionario = FuncionarioAduana.builder()
                .usuario(usuario)
                .puesto(dto.getPuesto())
                .build();

        funcionario = funcionarioAduanaRepository.save(funcionario);

        return toDTO(funcionario);
    }

    @Override
    public List<FuncionarioAduanaResponseDTO> listar() {
        return funcionarioAduanaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public FuncionarioAduanaResponseDTO obtenerPorId(Long id) {
        FuncionarioAduana funcionario = funcionarioAduanaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionario no encontrado"));
        return toDTO(funcionario);
    }

    @Override
    @Transactional
    public FuncionarioAduanaResponseDTO actualizar(Long id, FuncionarioAduanaRequestDTO dto) {
        FuncionarioAduana funcionario = funcionarioAduanaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionario no encontrado"));

        Usuario usuario = funcionario.getUsuario();
        if (!usuario.getCorreo().equals(dto.getCorreo())
                && usuarioRepository.findByCorreo(dto.getCorreo()).isPresent()) {
            throw new IllegalArgumentException("Correo ya en uso.");
        }

        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(dto.getContrasena());
        usuarioRepository.save(usuario);

        funcionario.setPuesto(dto.getPuesto());
        funcionario = funcionarioAduanaRepository.save(funcionario);

        return toDTO(funcionario);
    }

    @Override
    public void eliminar(Long id) {
        if (!funcionarioAduanaRepository.existsById(id)) {
            throw new EntityNotFoundException("Funcionario no encontrado");
        }
        funcionarioAduanaRepository.deleteById(id);
        usuarioRepository.deleteById(id);
    }

    private FuncionarioAduanaResponseDTO toDTO(FuncionarioAduana funcionario) {
        FuncionarioAduanaResponseDTO dto = new FuncionarioAduanaResponseDTO();
        dto.setIdUsuario(funcionario.getId());
        dto.setNombre(funcionario.getUsuario().getNombre());
        dto.setCorreo(funcionario.getUsuario().getCorreo());
        dto.setPuesto(funcionario.getPuesto());
        return dto;
    }
}
