package cl.duoc.conectafrontera.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@IdClass(ViajeroDocumentoId.class)
@Table(name = "viajero_documento")
public class ViajeroDocumento {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_viajero", referencedColumnName = "id_usuario")
    private Viajero idViajero;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_documento", referencedColumnName = "id_documento")
    private Documento idDocumento;
}
