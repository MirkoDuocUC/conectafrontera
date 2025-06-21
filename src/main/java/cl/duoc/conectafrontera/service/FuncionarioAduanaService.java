package cl.duoc.conectafrontera.service;

import cl.duoc.conectafrontera.dto.FuncionarioAduanaRequestDTO;
import cl.duoc.conectafrontera.dto.FuncionarioAduanaResponseDTO;

import java.util.List;

public interface FuncionarioAduanaService {
    FuncionarioAduanaResponseDTO crear(FuncionarioAduanaRequestDTO dto);
    List<FuncionarioAduanaResponseDTO> listar();
    FuncionarioAduanaResponseDTO obtenerPorId(Long id);
    FuncionarioAduanaResponseDTO actualizar(Long id, FuncionarioAduanaRequestDTO dto);
    void eliminar(Long id);
}
