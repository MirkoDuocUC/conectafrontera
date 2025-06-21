package cl.duoc.conectafrontera.dto;

import cl.duoc.conectafrontera.util.RolUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioRequestDTO {
    @NotBlank
    private String nombre;

    @Email
    private String correo;

    @NotBlank
    private String contrasena;

    private RolUsuario rol;
}
