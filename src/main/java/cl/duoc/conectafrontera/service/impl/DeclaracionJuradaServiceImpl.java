package cl.duoc.conectafrontera.service.impl;

import cl.duoc.conectafrontera.dto.DeclaracionJuradaRequestDTO;
import cl.duoc.conectafrontera.dto.DeclaracionJuradaResponseDTO;
import cl.duoc.conectafrontera.entity.DeclaracionJurada;
import cl.duoc.conectafrontera.entity.Viajero;
import cl.duoc.conectafrontera.repository.DeclaracionJuradaRepository;
import cl.duoc.conectafrontera.repository.ViajeroRepository;
import cl.duoc.conectafrontera.service.DeclaracionJuradaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeclaracionJuradaServiceImpl implements DeclaracionJuradaService {

    private final DeclaracionJuradaRepository declaracionRepo;
    private final ViajeroRepository viajeroRepo;

    @Override
    public DeclaracionJuradaResponseDTO crear(DeclaracionJuradaRequestDTO dto) {
        Viajero viajero = viajeroRepo.findById(dto.getIdViajero())
                .orElseThrow(() -> new EntityNotFoundException("Viajero no encontrado"));

        DeclaracionJurada declaracion = DeclaracionJurada.builder()
                .estado(dto.getEstado() != null ? dto.getEstado() : "PENDIENTE")
                .viajero(viajero)
                .build();

        declaracion = declaracionRepo.save(declaracion);
        return toDTO(declaracion);
    }

    @Override
    public List<DeclaracionJuradaResponseDTO> listar() {
        return declaracionRepo.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public DeclaracionJuradaResponseDTO obtenerPorId(Long id) {
        DeclaracionJurada declaracion = declaracionRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Declaración no encontrada"));
        return toDTO(declaracion);
    }

    @Override
    public DeclaracionJuradaResponseDTO actualizar(Long id, DeclaracionJuradaRequestDTO dto) {
        DeclaracionJurada declaracion = declaracionRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Declaración no encontrada"));

        if (dto.getEstado() != null) declaracion.setEstado(dto.getEstado());

        if (dto.getIdViajero() != null) {
            Viajero viajero = viajeroRepo.findById(dto.getIdViajero())
                    .orElseThrow(() -> new EntityNotFoundException("Viajero no encontrado"));
            declaracion.setViajero(viajero);
        }

        return toDTO(declaracionRepo.save(declaracion));
    }

    @Override
    public void eliminar(Long id) {
        declaracionRepo.deleteById(id);
    }

    private DeclaracionJuradaResponseDTO toDTO(DeclaracionJurada d) {
        DeclaracionJuradaResponseDTO dto = new DeclaracionJuradaResponseDTO();
        dto.setId(d.getId());
        dto.setEstado(d.getEstado());
        dto.setIdViajero(d.getViajero().getId());
        dto.setNombreViajero(d.getViajero().getUsuario().getNombre());
        return dto;
    }
}
