package cl.duoc.conectafrontera.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sistema_externo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SistemaExterno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sistema")
    private Long idSistema;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(name = "tipo_api", length = 50, nullable = false)
    private String tipoApi;
}
