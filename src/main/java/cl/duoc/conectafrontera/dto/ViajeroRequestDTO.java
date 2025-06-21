package cl.duoc.conectafrontera.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ViajeroRequestDTO {
    @NotBlank
    private String nombre;

    @Email
    private String correo;

    @NotBlank
    private String contrasena;
}
