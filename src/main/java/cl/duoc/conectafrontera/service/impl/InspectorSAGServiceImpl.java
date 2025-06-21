package cl.duoc.conectafrontera.service.impl;

import cl.duoc.conectafrontera.dto.InspectorSAGRequestDTO;
import cl.duoc.conectafrontera.dto.InspectorSAGResponseDTO;
import cl.duoc.conectafrontera.entity.InspectorSAG;
import cl.duoc.conectafrontera.entity.Usuario;
import cl.duoc.conectafrontera.repository.InspectorSAGRepository;
import cl.duoc.conectafrontera.repository.UsuarioRepository;
import cl.duoc.conectafrontera.service.InspectorSAGService;
import cl.duoc.conectafrontera.util.RolUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InspectorSAGServiceImpl implements InspectorSAGService {

    private final UsuarioRepository usuarioRepository;
    private final InspectorSAGRepository inspectorRepository;

    @Override
    @Transactional
    public InspectorSAGResponseDTO registrarInspector(InspectorSAGRequestDTO dto) {
        Usuario usuario = Usuario.builder()
                .nombre(dto.getNombre())
                .correo(dto.getCorreo())
                .contrasena(dto.getContrasena())
                .rol(RolUsuario.INSPECTOR_SAG)
                .build();
        usuario = usuarioRepository.save(usuario);

        InspectorSAG inspector = InspectorSAG.builder()
                .usuario(usuario)
                .especialidad(dto.getEspecialidad())
                .build();
        inspector = inspectorRepository.save(inspector);

        return toDTO(inspector);
    }

    @Override
    public List<InspectorSAGResponseDTO> obtenerInspectores() {
        return inspectorRepository.findAll().stream().map(this::toDTO).toList();
    }

    private InspectorSAGResponseDTO toDTO(InspectorSAG inspector) {
        InspectorSAGResponseDTO dto = new InspectorSAGResponseDTO();
        dto.setIdUsuario(inspector.getId());
        dto.setNombre(inspector.getUsuario().getNombre());
        dto.setCorreo(inspector.getUsuario().getCorreo());
        dto.setEspecialidad(inspector.getEspecialidad());
        return dto;
    }

    @Override
    public InspectorSAGResponseDTO obtenerPorId(Long id) {
        InspectorSAG inspector = inspectorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inspector no encontrado"));
        return toDTO(inspector);
    }

    @Override
    @Transactional
    public InspectorSAGResponseDTO actualizarInspector(Long id, InspectorSAGRequestDTO dto) {
        InspectorSAG inspector = inspectorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inspector no encontrado"));

        Usuario usuario = inspector.getUsuario();
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(dto.getContrasena());
        usuarioRepository.save(usuario);

        inspector.setEspecialidad(dto.getEspecialidad());
        inspector = inspectorRepository.save(inspector);

        return toDTO(inspector);
    }

    @Override
    @Transactional
    public void eliminarInspector(Long id) {
        if (!inspectorRepository.existsById(id)) {
            throw new IllegalArgumentException("Inspector no encontrado");
        }
        inspectorRepository.deleteById(id);
        usuarioRepository.deleteById(id);
    }

}
