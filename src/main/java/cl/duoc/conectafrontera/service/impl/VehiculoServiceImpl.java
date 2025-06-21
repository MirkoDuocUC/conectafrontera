package cl.duoc.conectafrontera.service.impl;

import cl.duoc.conectafrontera.dto.VehiculoRequestDTO;
import cl.duoc.conectafrontera.dto.VehiculoResponseDTO;
import cl.duoc.conectafrontera.entity.Vehiculo;
import cl.duoc.conectafrontera.repository.VehiculoRepository;
import cl.duoc.conectafrontera.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    @Override
    public VehiculoResponseDTO registrarVehiculo(VehiculoRequestDTO dto) {
        Vehiculo vehiculo = Vehiculo.builder()
                .patente(dto.getPatente())
                .tipo(dto.getTipo())
                .fechaIngreso(dto.getFechaIngreso())
                .fechaSalidaEstimada(dto.getFechaSalidaEstimada())
                .build();
        return toDTO(vehiculoRepository.save(vehiculo));
    }

    @Override
    public VehiculoResponseDTO obtenerVehiculoPorId(Long id) {
        return vehiculoRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
    }

    @Override
    public List<VehiculoResponseDTO> listarVehiculos() {
        return vehiculoRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public VehiculoResponseDTO actualizarVehiculo(Long id, VehiculoRequestDTO dto) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        vehiculo.setPatente(dto.getPatente());
        vehiculo.setTipo(dto.getTipo());
        vehiculo.setFechaIngreso(dto.getFechaIngreso());
        vehiculo.setFechaSalidaEstimada(dto.getFechaSalidaEstimada());

        return toDTO(vehiculoRepository.save(vehiculo));
    }

    @Override
    public void eliminarVehiculo(Long id) {
        vehiculoRepository.deleteById(id);
    }

    private VehiculoResponseDTO toDTO(Vehiculo v) {
        VehiculoResponseDTO dto = new VehiculoResponseDTO();
        dto.setId(v.getId());
        dto.setPatente(v.getPatente());
        dto.setTipo(v.getTipo());
        dto.setFechaIngreso(v.getFechaIngreso());
        dto.setFechaSalidaEstimada(v.getFechaSalidaEstimada());
        return dto;
    }
}
