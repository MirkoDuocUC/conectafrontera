package cl.duoc.conectafrontera.controller;

import cl.duoc.conectafrontera.dto.DeclaracionJuradaRequestDTO;
import cl.duoc.conectafrontera.dto.DeclaracionJuradaResponseDTO;
import cl.duoc.conectafrontera.service.DeclaracionJuradaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/declaraciones")
@RequiredArgsConstructor
public class DeclaracionJuradaController {

    private final DeclaracionJuradaService service;

    @PreAuthorize("hasRole('VIAJERO')")
    @PostMapping
    public DeclaracionJuradaResponseDTO crear(@Valid @RequestBody DeclaracionJuradaRequestDTO dto) {
        return service.crear(dto);
    }

    @PreAuthorize("hasRole('VIAJERO')")
    @GetMapping
    public List<DeclaracionJuradaResponseDTO> listar() {
        return service.listar();
    }

    @PreAuthorize("hasAnyRole('VIAJERO', 'FUNCIONARIO_ADUANA')")
    @GetMapping("/{id}")
    public DeclaracionJuradaResponseDTO obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PreAuthorize("hasRole('FUNCIONARIO_ADUANA')")
    @PutMapping("/{id}")
    public DeclaracionJuradaResponseDTO actualizar(@PathVariable Long id, @RequestBody DeclaracionJuradaRequestDTO dto) {
        return service.actualizar(id, dto);
    }

    @PreAuthorize("hasRole('VIAJERO')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
