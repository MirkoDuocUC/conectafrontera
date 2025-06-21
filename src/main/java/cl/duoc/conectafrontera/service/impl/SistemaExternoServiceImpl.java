package cl.duoc.conectafrontera.service.impl;

import cl.duoc.conectafrontera.dto.SistemaExternoRequestDTO;
import cl.duoc.conectafrontera.dto.SistemaExternoResponseDTO;
import cl.duoc.conectafrontera.entity.SistemaExterno;
import cl.duoc.conectafrontera.repository.SistemaExternoRepository;
import cl.duoc.conectafrontera.service.SistemaExternoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SistemaExternoServiceImpl implements SistemaExternoService {

    private final SistemaExternoRepository repository;

    @Override
    public SistemaExternoResponseDTO crear(SistemaExternoRequestDTO dto) {
        SistemaExterno sistema = SistemaExterno.builder()
                .nombre(dto.getNombre())
                .tipoApi(dto.getTipoApi())
                .build();

        return toDTO(repository.save(sistema));
    }

    @Override
    public List<SistemaExternoResponseDTO> listar() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public SistemaExternoResponseDTO actualizar(Long id, SistemaExternoRequestDTO dto) {
        SistemaExterno sistema = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrado"));
        sistema.setNombre(dto.getNombre());
        sistema.setTipoApi(dto.getTipoApi());
        return toDTO(repository.save(sistema));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private SistemaExternoResponseDTO toDTO(SistemaExterno sistema) {
        SistemaExternoResponseDTO dto = new SistemaExternoResponseDTO();
        dto.setIdSistema(sistema.getIdSistema());
        dto.setNombre(sistema.getNombre());
        dto.setTipoApi(sistema.getTipoApi());
        return dto;
    }
}
