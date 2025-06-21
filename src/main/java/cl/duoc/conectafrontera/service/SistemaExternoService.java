package cl.duoc.conectafrontera.service;

import cl.duoc.conectafrontera.dto.SistemaExternoRequestDTO;
import cl.duoc.conectafrontera.dto.SistemaExternoResponseDTO;

import java.util.List;

public interface SistemaExternoService {
    SistemaExternoResponseDTO crear(SistemaExternoRequestDTO dto);
    List<SistemaExternoResponseDTO> listar();
    SistemaExternoResponseDTO actualizar(Long id, SistemaExternoRequestDTO dto);
    void eliminar(Long id);
}
