package cl.duoc.conectafrontera.service.impl;

import cl.duoc.conectafrontera.dto.DocumentoRequestDTO;
import cl.duoc.conectafrontera.dto.DocumentoResponseDTO;
import cl.duoc.conectafrontera.entity.Documento;
import cl.duoc.conectafrontera.repository.DocumentoRepository;
import cl.duoc.conectafrontera.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoServiceImpl implements DocumentoService {

    private final DocumentoRepository documentoRepository;

    @Override
    public DocumentoResponseDTO crearDocumento(DocumentoRequestDTO dto) {
        Documento documento = Documento.builder()
                .tipo(dto.getTipo())
                .numero(dto.getNumero())
                .fechaEmision(dto.getFechaEmision())
                .fechaVencimiento(dto.getFechaVencimiento())
                .build();
        return toDTO(documentoRepository.save(documento));
    }

    @Override
    public List<DocumentoResponseDTO> obtenerDocumentos() {
        return documentoRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public DocumentoResponseDTO actualizarDocumento(Long id, DocumentoRequestDTO dto) {
        Documento doc = documentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Documento no encontrado"));
        doc.setTipo(dto.getTipo());
        doc.setNumero(dto.getNumero());
        doc.setFechaEmision(dto.getFechaEmision());
        doc.setFechaVencimiento(dto.getFechaVencimiento());
        return toDTO(documentoRepository.save(doc));
    }

    @Override
    public void eliminarDocumento(Long id) {
        documentoRepository.deleteById(id);
    }

    private DocumentoResponseDTO toDTO(Documento d) {
        DocumentoResponseDTO dto = new DocumentoResponseDTO();
        dto.setId(d.getIdDocumento());
        dto.setTipo(d.getTipo());
        dto.setNumero(d.getNumero());
        dto.setFechaEmision(d.getFechaEmision());
        dto.setFechaVencimiento(d.getFechaVencimiento());
        return dto;
    }
}
