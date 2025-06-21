package cl.duoc.conectafrontera.service.impl;

import cl.duoc.conectafrontera.dto.ReporteEstadisticoRequestDTO;
import cl.duoc.conectafrontera.dto.ReporteEstadisticoResponseDTO;
import cl.duoc.conectafrontera.entity.ReporteEstadistico;
import cl.duoc.conectafrontera.repository.ReporteEstadisticoRepository;
import cl.duoc.conectafrontera.service.ReporteEstadisticoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteEstadisticoServiceImpl implements ReporteEstadisticoService {

    private final ReporteEstadisticoRepository repository;

    @Override
    public ReporteEstadisticoResponseDTO crear(ReporteEstadisticoRequestDTO dto) {
        ReporteEstadistico reporte = ReporteEstadistico.builder()
                .tipo(dto.getTipo())
                .fechaGeneracion(dto.getFechaGeneracion())
                .build();

        return toDTO(repository.save(reporte));
    }

    @Override
    public List<ReporteEstadisticoResponseDTO> listar() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private ReporteEstadisticoResponseDTO toDTO(ReporteEstadistico r) {
        ReporteEstadisticoResponseDTO dto = new ReporteEstadisticoResponseDTO();
        dto.setIdReporte(r.getIdReporte());
        dto.setTipo(r.getTipo());
        dto.setFechaGeneracion(r.getFechaGeneracion());
        return dto;
    }
}
