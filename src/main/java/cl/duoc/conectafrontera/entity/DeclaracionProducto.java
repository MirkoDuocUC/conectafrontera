package cl.duoc.conectafrontera.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "declaracion_producto")
@IdClass(DeclaracionProductoId.class)
public class DeclaracionProducto {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_declaracion", nullable = false)
    private DeclaracionJurada declaracion;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
}
