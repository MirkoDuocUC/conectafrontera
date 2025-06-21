package cl.duoc.conectafrontera.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reporte_estadistico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReporteEstadistico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte")
    private Long idReporte;

    @Column(length = 50, nullable = false)
    private String tipo;

    @Column(name = "fecha_generacion", nullable = false)
    private LocalDate fechaGeneracion;
}
