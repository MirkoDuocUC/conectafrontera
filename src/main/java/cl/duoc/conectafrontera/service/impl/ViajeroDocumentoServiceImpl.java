package cl.duoc.conectafrontera.service.impl;

import cl.duoc.conectafrontera.dto.ViajeroDocumentoRequestDTO;
import cl.duoc.conectafrontera.entity.Documento;
import cl.duoc.conectafrontera.entity.Viajero;
import cl.duoc.conectafrontera.entity.ViajeroDocumento;
import cl.duoc.conectafrontera.repository.DocumentoRepository;
import cl.duoc.conectafrontera.repository.ViajeroDocumentoRepository;
import cl.duoc.conectafrontera.repository.ViajeroRepository;
import cl.duoc.conectafrontera.service.ViajeroDocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViajeroDocumentoServiceImpl implements ViajeroDocumentoService {

    private final ViajeroDocumentoRepository viajeroDocumentoRepository;
    private final ViajeroRepository viajeroRepository;
    private final DocumentoRepository documentoRepository;

    @Override
    public void asignarDocumento(ViajeroDocumentoRequestDTO dto) {
        Viajero viajero = viajeroRepository.findById(dto.getIdViajero())
                .orElseThrow(() -> new RuntimeException("Viajero no encontrado"));
        Documento documento = documentoRepository.findById(dto.getIdDocumento())
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));

        ViajeroDocumento vd = ViajeroDocumento.builder()
                .idViajero(viajero)
                .idDocumento(documento)
                .build();

        viajeroDocumentoRepository.save(vd);
    }

    @Override
    public List<ViajeroDocumentoRequestDTO> obtenerDocumentosPorViajero(Long idViajero) {
        return viajeroDocumentoRepository.findByIdViajero_Id(idViajero)
                .stream()
                .map(vd -> {
                    ViajeroDocumentoRequestDTO dto = new ViajeroDocumentoRequestDTO();
                    dto.setIdViajero(vd.getIdViajero().getId());
                    dto.setIdDocumento(vd.getIdDocumento().getIdDocumento());
                    return dto;
                }).toList();
    }
}
