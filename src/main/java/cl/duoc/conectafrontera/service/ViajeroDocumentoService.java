package cl.duoc.conectafrontera.service;

import cl.duoc.conectafrontera.dto.ViajeroDocumentoRequestDTO;

import java.util.List;

public interface ViajeroDocumentoService {
    void asignarDocumento(ViajeroDocumentoRequestDTO dto);
    List<ViajeroDocumentoRequestDTO> obtenerDocumentosPorViajero(Long idViajero);
}
