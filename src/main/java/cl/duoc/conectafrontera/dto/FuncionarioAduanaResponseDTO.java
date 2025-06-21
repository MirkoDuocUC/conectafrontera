package cl.duoc.conectafrontera.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioAduanaResponseDTO {
    private Long idUsuario;
    private String nombre;
    private String correo;
    private String puesto;
}
