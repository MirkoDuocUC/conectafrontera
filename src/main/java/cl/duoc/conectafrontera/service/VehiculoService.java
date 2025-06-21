package cl.duoc.conectafrontera.service;

import cl.duoc.conectafrontera.dto.VehiculoRequestDTO;
import cl.duoc.conectafrontera.dto.VehiculoResponseDTO;

import java.util.List;

public interface VehiculoService {
    VehiculoResponseDTO registrarVehiculo(VehiculoRequestDTO dto);
    VehiculoResponseDTO obtenerVehiculoPorId(Long id);
    List<VehiculoResponseDTO> listarVehiculos();
    VehiculoResponseDTO actualizarVehiculo(Long id, VehiculoRequestDTO dto);
    void eliminarVehiculo(Long id);
}
