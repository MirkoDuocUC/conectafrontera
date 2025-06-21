package cl.duoc.conectafrontera.controller;

import cl.duoc.conectafrontera.dto.ViajeroRequestDTO;
import cl.duoc.conectafrontera.dto.ViajeroResponseDTO;
import cl.duoc.conectafrontera.service.ViajeroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viajeros")
@RequiredArgsConstructor
public class ViajeroController {

    private final ViajeroService viajeroService;

    @PreAuthorize("hasRole('VIAJERO')")
    @PostMapping
    public ViajeroResponseDTO registrar(@Valid @RequestBody ViajeroRequestDTO dto) {
        return viajeroService.registrarViajero(dto);
    }

    @PreAuthorize("hasRole('VIAJERO')")
    @GetMapping
    public List<ViajeroResponseDTO> listar() {
        return viajeroService.obtenerViajeros();
    }

    @PreAuthorize("hasRole('VIAJERO')")
    @PutMapping("/{id}")
    public ViajeroResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody ViajeroRequestDTO dto) {
        return viajeroService.actualizarViajero(id, dto);
    }

}
