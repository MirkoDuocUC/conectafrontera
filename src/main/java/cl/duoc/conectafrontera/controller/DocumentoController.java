package cl.duoc.conectafrontera.controller;

import cl.duoc.conectafrontera.dto.DocumentoRequestDTO;
import cl.duoc.conectafrontera.dto.DocumentoResponseDTO;
import cl.duoc.conectafrontera.service.DocumentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
@RequiredArgsConstructor
public class DocumentoController {

    private final DocumentoService documentoService;

    @PreAuthorize("hasRole('FUNCIONARIO_ADUANA')")
    @PostMapping
    public DocumentoResponseDTO crear(@Valid @RequestBody DocumentoRequestDTO dto) {
        return documentoService.crearDocumento(dto);
    }

    @PreAuthorize("hasRole('FUNCIONARIO_ADUANA')")
    @GetMapping
    public List<DocumentoResponseDTO> listar() {
        return documentoService.obtenerDocumentos();
    }

    @PreAuthorize("hasRole('FUNCIONARIO_ADUANA')")
    @PutMapping("/{id}")
    public DocumentoResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody DocumentoRequestDTO dto) {
        return documentoService.actualizarDocumento(id, dto);
    }

    @PreAuthorize("hasRole('FUNCIONARIO_ADUANA')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        documentoService.eliminarDocumento(id);
    }
}
