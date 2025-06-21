package cl.duoc.conectafrontera.service;

import cl.duoc.conectafrontera.dto.ReporteEstadisticoRequestDTO;
import cl.duoc.conectafrontera.dto.ReporteEstadisticoResponseDTO;

import java.util.List;

public interface ReporteEstadisticoService {
    ReporteEstadisticoResponseDTO crear(ReporteEstadisticoRequestDTO dto);
    List<ReporteEstadisticoResponseDTO> listar();
    void eliminar(Long id);
}
