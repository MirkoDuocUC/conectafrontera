package cl.duoc.conectafrontera.service;

import cl.duoc.conectafrontera.dto.ViajeroRequestDTO;
import cl.duoc.conectafrontera.dto.ViajeroResponseDTO;

import java.util.List;

public interface ViajeroService {
    ViajeroResponseDTO registrarViajero(ViajeroRequestDTO dto);
    List<ViajeroResponseDTO> obtenerViajeros();
    ViajeroResponseDTO actualizarViajero(Long id, ViajeroRequestDTO dto);

}
