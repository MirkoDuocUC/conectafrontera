package cl.duoc.conectafrontera.service;

import cl.duoc.conectafrontera.dto.DocumentoRequestDTO;
import cl.duoc.conectafrontera.dto.DocumentoResponseDTO;

import java.util.List;

public interface DocumentoService {
    DocumentoResponseDTO crearDocumento(DocumentoRequestDTO dto);
    List<DocumentoResponseDTO> obtenerDocumentos();
    DocumentoResponseDTO actualizarDocumento(Long id, DocumentoRequestDTO dto);
    void eliminarDocumento(Long id);
}
