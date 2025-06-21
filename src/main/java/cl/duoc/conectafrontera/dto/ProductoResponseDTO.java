package cl.duoc.conectafrontera.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoResponseDTO {
    private Long idProducto;
    private String nombre;
    private String categoria;
}
