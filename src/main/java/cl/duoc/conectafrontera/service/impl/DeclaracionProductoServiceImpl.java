package cl.duoc.conectafrontera.service.impl;

import cl.duoc.conectafrontera.dto.DeclaracionJuradaResponseDTO;
import cl.duoc.conectafrontera.dto.DeclaracionProductoRequestDTO;
import cl.duoc.conectafrontera.dto.DeclaracionProductoResponseDTO;
import cl.duoc.conectafrontera.entity.*;
import cl.duoc.conectafrontera.repository.*;
import cl.duoc.conectafrontera.service.DeclaracionProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeclaracionProductoServiceImpl implements DeclaracionProductoService {

    private final DeclaracionProductoRepository declaracionProductoRepository;
    private final DeclaracionJuradaRepository declaracionJuradaRepository;
    private final ProductoRepository productoRepository;

    @Override
    public DeclaracionProductoResponseDTO crearRelacion(DeclaracionProductoRequestDTO dto) {
        DeclaracionJurada declaracion = declaracionJuradaRepository.findById(dto.getIdDeclaracion())
                .orElseThrow(() -> new IllegalArgumentException("Declaración no encontrada"));

        Producto producto = productoRepository.findById(dto.getIdProducto())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        DeclaracionProducto entity = DeclaracionProducto.builder()
                .declaracion(declaracion)
                .producto(producto)
                .build();

        declaracionProductoRepository.save(entity);

        return new DeclaracionProductoResponseDTO(dto.getIdDeclaracion(), dto.getIdProducto());
    }

    @Override
    public List<DeclaracionProductoResponseDTO> obtenerTodas() {
        return declaracionProductoRepository.findAll().stream()
                .map(dp -> new DeclaracionProductoResponseDTO(
                        dp.getDeclaracion().getId(),
                        dp.getProducto().getIdProducto()))
                .toList();
    }

    @Override
    public void eliminarRelacion(Long idDeclaracion, Long idProducto) {
        DeclaracionProductoId id = new DeclaracionProductoId(idDeclaracion, idProducto);
        declaracionProductoRepository.deleteById(id);
    }

    @Override
    public List<Producto> obtenerProductosPorDeclaracion(Long idDeclaracion) {
        return declaracionProductoRepository.findByDeclaracion_Id(idDeclaracion)
                .stream()
                .map(DeclaracionProducto::getProducto)
                .toList();
    }

    @Override
    public List<DeclaracionJuradaResponseDTO> obtenerDeclaracionesPorProducto(Long idProducto) {
        return declaracionProductoRepository.findByProducto_IdProducto(idProducto)
                .stream()
                .map(dp -> toDTO(dp.getDeclaracion()))
                .toList();
    }



    @Override
    public List<DeclaracionProducto> obtenerRelacionesPorProducto(Long idProducto) {
        return declaracionProductoRepository.findByProducto_IdProducto(idProducto);
    }

    private DeclaracionJuradaResponseDTO toDTO(DeclaracionJurada declaracion) {
        DeclaracionJuradaResponseDTO dto = new DeclaracionJuradaResponseDTO();
        dto.setId(declaracion.getId());
        dto.setEstado(declaracion.getEstado());

        if (declaracion.getViajero() != null && declaracion.getViajero().getUsuario() != null) {
            dto.setIdViajero(declaracion.getViajero().getId());
            dto.setNombreViajero(declaracion.getViajero().getUsuario().getNombre());
        }

        return dto;
    }



}
