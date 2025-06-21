package cl.duoc.conectafrontera.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "declaracion_jurada")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeclaracionJurada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_declaracion")
    private Long id;

    @Column(nullable = false)
    private String estado = "PENDIENTE";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_viajero")
    private Viajero viajero;
}

