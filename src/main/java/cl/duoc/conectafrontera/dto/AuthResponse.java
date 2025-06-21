// AuthResponse.java
package cl.duoc.conectafrontera.dto;
import lombok.*;

@Getter @Setter @AllArgsConstructor
public class AuthResponse {
    private String token;
    private String rol;
    private String nombre;
}
