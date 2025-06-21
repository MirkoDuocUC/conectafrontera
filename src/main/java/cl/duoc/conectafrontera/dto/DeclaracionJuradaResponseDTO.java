package cl.duoc.conectafrontera.dto;

import lombok.Data;

@Data
public class DeclaracionJuradaResponseDTO {
    private Long id;
    private String estado;
    private Long idViajero;
    private String nombreViajero;
}
