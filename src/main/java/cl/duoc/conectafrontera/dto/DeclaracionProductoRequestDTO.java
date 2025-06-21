package cl.duoc.conectafrontera.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeclaracionProductoRequestDTO {
    private Long idDeclaracion;
    private Long idProducto;
}
