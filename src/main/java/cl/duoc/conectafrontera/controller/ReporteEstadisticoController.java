package cl.duoc.conectafrontera.controller;

import cl.duoc.conectafrontera.dto.ReporteEstadisticoRequestDTO;
import cl.duoc.conectafrontera.dto.ReporteEstadisticoResponseDTO;
import cl.duoc.conectafrontera.service.ReporteEstadisticoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteEstadisticoController {

    private final ReporteEstadisticoService service;

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping
    public ReporteEstadisticoResponseDTO crear(@RequestBody ReporteEstadisticoRequestDTO dto) {
        return service.crear(dto);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping
    public List<ReporteEstadisticoResponseDTO> listar() {
        return service.listar();
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
