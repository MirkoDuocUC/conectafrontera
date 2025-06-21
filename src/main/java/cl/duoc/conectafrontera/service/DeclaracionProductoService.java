package cl.duoc.conectafrontera.service;

import cl.duoc.conectafrontera.dto.DeclaracionJuradaResponseDTO;
import cl.duoc.conectafrontera.dto.DeclaracionProductoRequestDTO;
import cl.duoc.conectafrontera.dto.DeclaracionProductoResponseDTO;
import cl.duoc.conectafrontera.entity.DeclaracionJurada;
import cl.duoc.conectafrontera.entity.DeclaracionProducto;
import cl.duoc.conectafrontera.entity.Producto;

import java.util.List;

public interface DeclaracionProductoService {
    DeclaracionProductoResponseDTO crearRelacion(DeclaracionProductoRequestDTO dto);
    List<DeclaracionProductoResponseDTO> obtenerTodas();
    void eliminarRelacion(Long idDeclaracion, Long idProducto);
    List<Producto> obtenerProductosPorDeclaracion(Long idDeclaracion);
    List<DeclaracionProducto> obtenerRelacionesPorProducto(Long idProducto);
    List<DeclaracionJuradaResponseDTO> obtenerDeclaracionesPorProducto(Long idProducto);




}
