package cl.duoc.conectafrontera.controller;

import cl.duoc.conectafrontera.dto.ViajeroDocumentoRequestDTO;
import cl.duoc.conectafrontera.service.ViajeroDocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viajero-documentos")
@RequiredArgsConstructor
public class ViajeroDocumentoController {

    private final ViajeroDocumentoService service;

    @PreAuthorize("hasRole('VIAJERO')")
    @PostMapping
    public void asignarDocumento(@RequestBody ViajeroDocumentoRequestDTO dto) {
        service.asignarDocumento(dto);
    }

    @PreAuthorize("hasRole('VIAJERO')")
    @GetMapping("/{idViajero}")
    public List<ViajeroDocumentoRequestDTO> obtenerPorViajero(@PathVariable Long idViajero) {
        return service.obtenerDocumentosPorViajero(idViajero);
    }
}
