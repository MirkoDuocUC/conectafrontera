package cl.duoc.conectafrontera.service;

import cl.duoc.conectafrontera.dto.AdministradorRequestDTO;
import cl.duoc.conectafrontera.dto.AdministradorResponseDTO;

import java.util.List;

public interface AdministradorService {
    AdministradorResponseDTO registrarAdministrador(AdministradorRequestDTO dto);
    List<AdministradorResponseDTO> obtenerAdministradores();
    AdministradorResponseDTO actualizarAdministrador(Long id, AdministradorRequestDTO dto);
    void eliminarAdministrador(Long id);
}
