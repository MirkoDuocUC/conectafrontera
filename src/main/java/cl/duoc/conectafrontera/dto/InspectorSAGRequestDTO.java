package cl.duoc.conectafrontera.dto;

import lombok.Data;

@Data
public class InspectorSAGRequestDTO {
    private String nombre;
    private String correo;
    private String contrasena;
    private String especialidad;
}
