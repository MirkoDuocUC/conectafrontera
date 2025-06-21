package cl.duoc.conectafrontera.controller;

import cl.duoc.conectafrontera.dto.VehiculoRequestDTO;
import cl.duoc.conectafrontera.dto.VehiculoResponseDTO;
import cl.duoc.conectafrontera.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {

    private final VehiculoService vehiculoService;

    @PreAuthorize("hasRole('FUNCIONARIO_ADUANA')")
    @PostMapping
    public VehiculoResponseDTO registrar(@RequestBody VehiculoRequestDTO dto) {
        return vehiculoService.registrarVehiculo(dto);
    }

    @PreAuthorize("hasRole('FUNCIONARIO_ADUANA')")
    @GetMapping
    public List<VehiculoResponseDTO> listar() {
        return vehiculoService.listarVehiculos();
    }

    @PreAuthorize("hasRole('FUNCIONARIO_ADUANA')")
    @GetMapping("/{id}")
    public VehiculoResponseDTO obtenerPorId(@PathVariable Long id) {
        return vehiculoService.obtenerVehiculoPorId(id);
    }

    @PreAuthorize("hasRole('FUNCIONARIO_ADUANA')")
    @PutMapping("/{id}")
    public VehiculoResponseDTO actualizar(@PathVariable Long id, @RequestBody VehiculoRequestDTO dto) {
        return vehiculoService.actualizarVehiculo(id, dto);
    }

    @PreAuthorize("hasRole('FUNCIONARIO_ADUANA')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        vehiculoService.eliminarVehiculo(id);
    }
}
