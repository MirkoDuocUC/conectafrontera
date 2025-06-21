package cl.duoc.conectafrontera.service;

import cl.duoc.conectafrontera.dto.ProductoRequestDTO;
import cl.duoc.conectafrontera.dto.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {
    ProductoResponseDTO crearProducto(ProductoRequestDTO dto);
    List<ProductoResponseDTO> listarProductos();
    ProductoResponseDTO obtenerProductoPorId(Long id);
    ProductoResponseDTO actualizarProducto(Long id, ProductoRequestDTO dto);
    void eliminarProducto(Long id);
}
