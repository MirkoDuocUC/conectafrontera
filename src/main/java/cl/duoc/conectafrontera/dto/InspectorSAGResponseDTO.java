package cl.duoc.conectafrontera.dto;

import lombok.Data;

@Data
public class InspectorSAGResponseDTO {
    private Long idUsuario;
    private String nombre;
    private String correo;
    private String especialidad;
}
