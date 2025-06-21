package cl.duoc.conectafrontera.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministradorResponseDTO {
    private Long idUsuario;
    private String nombre;
    private String correo;
    private String nivelPermiso;
}

