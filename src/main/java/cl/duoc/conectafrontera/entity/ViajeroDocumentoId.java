package cl.duoc.conectafrontera.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViajeroDocumentoId implements Serializable {
    private Long idViajero;
    private Long idDocumento;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViajeroDocumentoId that)) return false;
        return Objects.equals(idViajero, that.idViajero) &&
                Objects.equals(idDocumento, that.idDocumento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idViajero, idDocumento);
    }
}
