package cl.duoc.conectafrontera.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReporteEstadisticoResponseDTO {
    private Long idReporte;
    private String tipo;
    private LocalDate fechaGeneracion;
}
