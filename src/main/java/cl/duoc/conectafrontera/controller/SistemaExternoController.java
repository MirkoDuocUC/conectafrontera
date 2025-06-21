package cl.duoc.conectafrontera.controller;

import cl.duoc.conectafrontera.dto.SistemaExternoRequestDTO;
import cl.duoc.conectafrontera.dto.SistemaExternoResponseDTO;
import cl.duoc.conectafrontera.service.SistemaExternoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sistemas-externos")
@RequiredArgsConstructor
public class SistemaExternoController {

    private final SistemaExternoService service;

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping
    public SistemaExternoResponseDTO crear(@RequestBody SistemaExternoRequestDTO dto) {
        return service.crear(dto);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping
    public List<SistemaExternoResponseDTO> listar() {
        return service.listar();
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PutMapping("/{id}")
    public SistemaExternoResponseDTO actualizar(@PathVariable Long id,
                                                @RequestBody SistemaExternoRequestDTO dto) {
        return service.actualizar(id, dto);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
