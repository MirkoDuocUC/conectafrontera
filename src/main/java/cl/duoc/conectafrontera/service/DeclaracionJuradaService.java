package cl.duoc.conectafrontera.service;

import cl.duoc.conectafrontera.dto.DeclaracionJuradaRequestDTO;
import cl.duoc.conectafrontera.dto.DeclaracionJuradaResponseDTO;

import java.util.List;

public interface DeclaracionJuradaService {
    DeclaracionJuradaResponseDTO crear(DeclaracionJuradaRequestDTO dto);
    List<DeclaracionJuradaResponseDTO> listar();
    DeclaracionJuradaResponseDTO obtenerPorId(Long id);
    DeclaracionJuradaResponseDTO actualizar(Long id, DeclaracionJuradaRequestDTO dto);
    void eliminar(Long id);
}
