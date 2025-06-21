package cl.duoc.conectafrontera.repository;

import cl.duoc.conectafrontera.entity.Administrador;
import cl.duoc.conectafrontera.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    void deleteByUsuario(Usuario usuario);

}
