package cl.duoc.conectafrontera.controller;

import cl.duoc.conectafrontera.dto.ProductoRequestDTO;
import cl.duoc.conectafrontera.dto.ProductoResponseDTO;
import cl.duoc.conectafrontera.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PreAuthorize("hasRole('INSPECTOR_SAG')")
    @PostMapping
    public ProductoResponseDTO crear(@RequestBody ProductoRequestDTO dto) {
        return productoService.crearProducto(dto);
    }

    @PreAuthorize("hasAnyRole('INSPECTOR_SAG','FUNCIONARIO_ADUANA')")
    @GetMapping
    public List<ProductoResponseDTO> listar() {
        return productoService.listarProductos();
    }

    @PreAuthorize("hasAnyRole('INSPECTOR_SAG', 'FUNCIONARIO_ADUANA')")
    @GetMapping("/{id}")
    public ProductoResponseDTO obtener(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id);
    }

    @PreAuthorize("hasAnyRole('INSPECTOR_SAG', 'FUNCIONARIO_ADUANA')")
    @PutMapping("/{id}")
    public ProductoResponseDTO actualizar(@PathVariable Long id, @RequestBody ProductoRequestDTO dto) {
        return productoService.actualizarProducto(id, dto);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productoService.eliminarProducto(id);
    }
}
