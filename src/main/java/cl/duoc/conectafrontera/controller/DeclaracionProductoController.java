package cl.duoc.conectafrontera.controller;

import cl.duoc.conectafrontera.dto.DeclaracionJuradaResponseDTO;
import cl.duoc.conectafrontera.dto.DeclaracionProductoRequestDTO;
import cl.duoc.conectafrontera.dto.DeclaracionProductoResponseDTO;
import cl.duoc.conectafrontera.entity.DeclaracionJurada;
import cl.duoc.conectafrontera.entity.DeclaracionProducto;
import cl.duoc.conectafrontera.entity.Producto;
import cl.duoc.conectafrontera.service.DeclaracionProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/declaracion-productos")
@RequiredArgsConstructor
public class DeclaracionProductoController {

    private final DeclaracionProductoService declaracionProductoService;

    @PreAuthorize("hasRole('VIAJERO')")
    @PostMapping
    public DeclaracionProductoResponseDTO crear(@RequestBody DeclaracionProductoRequestDTO dto) {
        return declaracionProductoService.crearRelacion(dto);
    }

    @PreAuthorize("hasRole('VIAJERO')")
    @GetMapping
    public List<DeclaracionProductoResponseDTO> listar() {
        return declaracionProductoService.obtenerTodas();
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'FUNCIONARIO_ADUANA')")
    @DeleteMapping("/{idDeclaracion}/{idProducto}")
    public void eliminar(@PathVariable Long idDeclaracion, @PathVariable Long idProducto) {
        declaracionProductoService.eliminarRelacion(idDeclaracion, idProducto);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'FUNCIONARIO_ADUANA')")
    @GetMapping("/declaracion/{idDeclaracion}/productos")
    public List<Producto> obtenerProductosPorDeclaracion(@PathVariable Long idDeclaracion) {
        return declaracionProductoService.obtenerProductosPorDeclaracion(idDeclaracion);
    }
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'FUNCIONARIO_ADUANA')")
    @GetMapping("/producto/{idProducto}/declaraciones")
    public List<DeclaracionJuradaResponseDTO> obtenerDeclaracionesPorProducto(@PathVariable Long idProducto) {
        return declaracionProductoService.obtenerDeclaracionesPorProducto(idProducto);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'FUNCIONARIO_ADUANA')")
    @GetMapping("/producto/{idProducto}/relaciones")
    public List<DeclaracionProducto> obtenerPorProducto(@PathVariable Long idProducto) {
        return declaracionProductoService.obtenerRelacionesPorProducto(idProducto);
    }


}
