package cl.duoc.conectafrontera.service.impl;

import cl.duoc.conectafrontera.dto.ProductoRequestDTO;
import cl.duoc.conectafrontera.dto.ProductoResponseDTO;
import cl.duoc.conectafrontera.entity.Producto;
import cl.duoc.conectafrontera.repository.ProductoRepository;
import cl.duoc.conectafrontera.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public ProductoResponseDTO crearProducto(ProductoRequestDTO dto) {
        Producto producto = Producto.builder()
                .nombre(dto.getNombre())
                .categoria(dto.getCategoria())
                .build();
        return toDTO(productoRepository.save(producto));
    }

    @Override
    public List<ProductoResponseDTO> listarProductos() {
        return productoRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public ProductoResponseDTO obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        return toDTO(producto);
    }

    @Override
    public ProductoResponseDTO actualizarProducto(Long id, ProductoRequestDTO dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        producto.setNombre(dto.getNombre());
        producto.setCategoria(dto.getCategoria());
        return toDTO(productoRepository.save(producto));
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    private ProductoResponseDTO toDTO(Producto producto) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setIdProducto(producto.getIdProducto());
        dto.setNombre(producto.getNombre());
        dto.setCategoria(producto.getCategoria());
        return dto;
    }
}
