package cl.duoc.conectafrontera.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentoResponseDTO {
    private Long id;
    private String tipo;
    private String numero;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
}
