package cl.duoc.conectafrontera.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReporteEstadisticoRequestDTO {
    private String tipo;
    private LocalDate fechaGeneracion;
}
