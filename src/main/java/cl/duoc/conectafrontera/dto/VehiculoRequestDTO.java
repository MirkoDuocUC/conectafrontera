package cl.duoc.conectafrontera.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VehiculoRequestDTO {
    private String patente;
    private String tipo;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalidaEstimada;
}
