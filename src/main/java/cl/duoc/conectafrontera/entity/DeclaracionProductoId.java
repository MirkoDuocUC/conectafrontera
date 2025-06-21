package cl.duoc.conectafrontera.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeclaracionProductoId implements Serializable {

    private Long declaracion;
    private Long producto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeclaracionProductoId that)) return false;
        return Objects.equals(declaracion, that.declaracion) &&
                Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(declaracion, producto);
    }
}
