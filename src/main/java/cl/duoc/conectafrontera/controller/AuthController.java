package cl.duoc.conectafrontera.controller;

import cl.duoc.conectafrontera.dto.UsuarioRequestDTO;
import cl.duoc.conectafrontera.dto.UsuarioResponseDTO;
import cl.duoc.conectafrontera.dto.AuthRequest;
import cl.duoc.conectafrontera.dto.AuthResponse;
import cl.duoc.conectafrontera.entity.Usuario;
import cl.duoc.conectafrontera.repository.UsuarioRepository;
import cl.duoc.conectafrontera.security.JwtUtil;
import cl.duoc.conectafrontera.util.RolUsuario;
import cl.duoc.conectafrontera.service.UsuarioService;
import cl.duoc.conectafrontera.dto.ErrorResponseDTO;  // Importamos el ErrorResponseDTO
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getContrasena(), usuario.getContrasena())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
        }

        String token = jwtUtil.generarToken(usuario.getCorreo(), usuario.getRol().name());

        return ResponseEntity.ok(new AuthResponse(token, usuario.getRol().name(), usuario.getNombre()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsuarioRequestDTO requestDTO) {
        try {
            // Convierte el rol recibido a mayúsculas
            String rolStr = requestDTO.getRol().toString().toUpperCase();

            // Convertir el String a RolUsuario
            RolUsuario rol = RolUsuario.valueOf(rolStr);  // Lanza IllegalArgumentException si el rol no es válido

            String contrasenaEncriptada = passwordEncoder.encode(requestDTO.getContrasena());

            Usuario usuario = Usuario.builder()
                    .nombre(requestDTO.getNombre())
                    .correo(requestDTO.getCorreo())
                    .contrasena(contrasenaEncriptada)
                    .rol(rol)
                    .build();

            usuarioRepository.save(usuario);

            UsuarioResponseDTO responseDTO = new UsuarioResponseDTO();
            responseDTO.setIdUsuario(usuario.getIdUsuario());
            responseDTO.setNombre(usuario.getNombre());
            responseDTO.setCorreo(usuario.getCorreo());
            responseDTO.setRol(usuario.getRol());

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (IllegalArgumentException e) {
            // Aquí devolvemos un ErrorResponseDTO con el mensaje de error
            ErrorResponseDTO errorResponse = new ErrorResponseDTO("Rol inválido.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
