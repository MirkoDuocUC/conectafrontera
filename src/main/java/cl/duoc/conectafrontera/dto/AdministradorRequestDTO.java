package cl.duoc.conectafrontera.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministradorRequestDTO {
    private String nombre;
    private String correo;
    private String contrasena;
    private String nivelPermiso;
}
