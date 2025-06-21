package cl.duoc.conectafrontera.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Usamos una clave secreta aleatoria de 256 bits generada automáticamente
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);  // Esto genera una clave de 256 bits

    private final long expirationMs = 86400000; // 24 horas en milisegundos

    // Genera el JWT con el correo y rol
    public String generarToken(String correo, String rol) {
        return Jwts.builder()
                .setSubject(correo)
                .claim("role", rol)  // Incluye el rol como claim
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    // Obtiene el correo desde el token
    public String obtenerCorreoDesdeToken(String token) {
        return Jwts.parserBuilder()  // Usamos parserBuilder para la nueva versión de jjwt
                .setSigningKey(key)  // Establecemos la clave para verificar la firma
                .build()
                .parseClaimsJws(token)  // Parseamos el JWT
                .getBody()
                .getSubject();  // Obtenemos el sujeto (correo)
    }

    // Obtiene el rol desde el token
    public String obtenerRolDesdeToken(String token) {
        return (String) Jwts.parserBuilder()  // Usamos parserBuilder para parsear el token
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role");  // Extraemos el rol desde las claims
    }

    // Valida el token
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()  // Usamos parserBuilder para la nueva versión de jjwt
                    .setSigningKey(key)  // Establecemos la clave para verificar la firma
                    .build()
                    .parseClaimsJws(token);  // Intentamos parsear el token
            return true;  // Si no hay excepción, el token es válido
        } catch (JwtException | IllegalArgumentException e) {
            return false;  // Si el token es inválido o ha expirado, lo atrapamos aquí
        }
    }
}
