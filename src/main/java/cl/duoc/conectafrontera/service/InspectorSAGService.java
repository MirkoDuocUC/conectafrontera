package cl.duoc.conectafrontera.service;

import cl.duoc.conectafrontera.dto.InspectorSAGRequestDTO;
import cl.duoc.conectafrontera.dto.InspectorSAGResponseDTO;

import java.util.List;

public interface InspectorSAGService {
    InspectorSAGResponseDTO registrarInspector(InspectorSAGRequestDTO dto);
    List<InspectorSAGResponseDTO> obtenerInspectores();
    InspectorSAGResponseDTO obtenerPorId(Long id);
    InspectorSAGResponseDTO actualizarInspector(Long id, InspectorSAGRequestDTO dto);
    void eliminarInspector(Long id);
}

