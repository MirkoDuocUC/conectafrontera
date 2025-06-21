package cl.duoc.conectafrontera.security;

import cl.duoc.conectafrontera.entity.Usuario;
import lombok.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
public class UsuarioDetails implements UserDetails {

    private final Usuario usuario;

    public UsuarioDetails(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> usuario.getRol().name());
    }

    @Override public String getPassword() { return usuario.getContrasena(); }
    @Override public String getUsername() { return usuario.getCorreo(); }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
