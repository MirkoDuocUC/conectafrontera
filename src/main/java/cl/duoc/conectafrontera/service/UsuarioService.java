package cl.duoc.conectafrontera.service;

import cl.duoc.conectafrontera.dto.UsuarioRequestDTO;
import cl.duoc.conectafrontera.dto.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto);
    List<UsuarioResponseDTO> obtenerTodos();
    UsuarioResponseDTO obtenerPorId(Long id);
    void eliminarUsuario(Long id);
}
