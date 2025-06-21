package cl.duoc.conectafrontera.controller;

import cl.duoc.conectafrontera.dto.UsuarioRequestDTO;
import cl.duoc.conectafrontera.dto.UsuarioResponseDTO;
import cl.duoc.conectafrontera.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping
    public UsuarioResponseDTO crear(@Valid @RequestBody UsuarioRequestDTO dto) {
        return usuarioService.crearUsuario(dto);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping
    public List<UsuarioResponseDTO> listar() {
        return usuarioService.obtenerTodos();
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public UsuarioResponseDTO obtener(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PutMapping("/{id}")
    public UsuarioResponseDTO actualizar(@PathVariable Long id, @RequestBody @Valid UsuarioRequestDTO dto) {
        return usuarioService.actualizarUsuario(id, dto);
    }


}