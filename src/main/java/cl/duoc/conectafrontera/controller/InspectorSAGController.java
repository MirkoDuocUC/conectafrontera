package cl.duoc.conectafrontera.controller;

import cl.duoc.conectafrontera.dto.InspectorSAGRequestDTO;
import cl.duoc.conectafrontera.dto.InspectorSAGResponseDTO;
import cl.duoc.conectafrontera.service.InspectorSAGService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inspectores")
@RequiredArgsConstructor
public class InspectorSAGController {

    private final InspectorSAGService inspectorService;

    @PreAuthorize("hasRole('INSPECTOR_SAG')")
    @PostMapping
    public InspectorSAGResponseDTO registrar(@Valid @RequestBody InspectorSAGRequestDTO dto) {
        return inspectorService.registrarInspector(dto);
    }

    @PreAuthorize("hasRole('INSPECTOR_SAG')")
    @GetMapping
    public List<InspectorSAGResponseDTO> listar() {
        return inspectorService.obtenerInspectores();
    }

    @PreAuthorize("hasRole('INSPECTOR_SAG')")
    @GetMapping("/{id}")
    public InspectorSAGResponseDTO obtener(@PathVariable Long id) {
        return inspectorService.obtenerPorId(id);
    }

    @PreAuthorize("hasRole('INSPECTOR_SAG')")
    @PutMapping("/{id}")
    public InspectorSAGResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody InspectorSAGRequestDTO dto) {
        return inspectorService.actualizarInspector(id, dto);
    }

    @PreAuthorize("hasRole('INSPECTOR_SAG')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        inspectorService.eliminarInspector(id);
    }

}
