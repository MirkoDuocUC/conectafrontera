package cl.duoc.conectafrontera.controller;

import cl.duoc.conectafrontera.dto.AdministradorRequestDTO;
import cl.duoc.conectafrontera.dto.AdministradorResponseDTO;
import cl.duoc.conectafrontera.service.AdministradorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administradores")
@RequiredArgsConstructor
public class AdministradorController {

    private final AdministradorService administradorService;

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping
    public AdministradorResponseDTO registrar(@Valid @RequestBody AdministradorRequestDTO dto) {
        return administradorService.registrarAdministrador(dto);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping
    public List<AdministradorResponseDTO> listar() {
        return administradorService.obtenerAdministradores();
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PutMapping("/{id}")
    public AdministradorResponseDTO actualizar(@PathVariable Long id, @RequestBody AdministradorRequestDTO dto) {
        return administradorService.actualizarAdministrador(id, dto);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        administradorService.eliminarAdministrador(id);
    }
}
