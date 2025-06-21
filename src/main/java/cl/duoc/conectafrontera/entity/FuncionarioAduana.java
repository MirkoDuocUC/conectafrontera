package cl.duoc.conectafrontera.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "funcionario_aduana")
public class FuncionarioAduana {

    @Id
    @Column(name = "id_usuario", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "puesto", length = 100)
    private String puesto;
}
