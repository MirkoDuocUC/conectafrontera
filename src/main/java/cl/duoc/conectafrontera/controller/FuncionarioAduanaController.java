package cl.duoc.conectafrontera.controller;

import cl.duoc.conectafrontera.dto.FuncionarioAduanaRequestDTO;
import cl.duoc.conectafrontera.dto.FuncionarioAduanaResponseDTO;
import cl.duoc.conectafrontera.service.FuncionarioAduanaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios-aduana")
@RequiredArgsConstructor
public class FuncionarioAduanaController {

    private final FuncionarioAduanaService funcionarioService;

    @PreAuthorize("hasRole('FUNCIONARIO_ADUANA')")
    @PostMapping
    public FuncionarioAduanaResponseDTO crear(@Valid @RequestBody FuncionarioAduanaRequestDTO dto) {
        return funcionarioService.crear(dto);
    }

    @PreAuthorize("hasRole('FUNCIONARIO_ADUANA')")
    @GetMapping
    public List<FuncionarioAduanaResponseDTO> listar() {
        return funcionarioService.listar();
    }

    @PreAuthorize("hasRole('FUNCIONARIO_ADUANA')")
    @GetMapping("/{id}")
    public FuncionarioAduanaResponseDTO obtenerPorId(@PathVariable Long id) {
        return funcionarioService.obtenerPorId(id);
    }

    @PreAuthorize("hasRole('FUNCIONARIO_ADUANA')")
    @PutMapping("/{id}")
    public FuncionarioAduanaResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody FuncionarioAduanaRequestDTO dto) {
        return funcionarioService.actualizar(id, dto);
    }

    @PreAuthorize("hasRole('FUNCIONARIO_ADUANA')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        funcionarioService.eliminar(id);
    }
}
