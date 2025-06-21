
package cl.duoc.conectafrontera.dto;
import cl.duoc.conectafrontera.util.RolUsuario;
import lombok.*;

@Getter @Setter
public class AuthRequest {
    private String correo;
    private String contrasena;
    private String nombre;
    private RolUsuario rol; // para registro
}
