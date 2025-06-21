package cl.duoc.conectafrontera.dto;

import cl.duoc.conectafrontera.util.RolUsuario;
import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Long idUsuario;
    private String nombre;
    private String correo;
    private RolUsuario rol;
}
