package cl.duoc.conectafrontera.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SistemaExternoResponseDTO {
    private Long idSistema;
    private String nombre;
    private String tipoApi;
}
