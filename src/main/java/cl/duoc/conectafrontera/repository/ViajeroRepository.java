package cl.duoc.conectafrontera.repository;

import cl.duoc.conectafrontera.entity.Usuario;
import cl.duoc.conectafrontera.entity.Viajero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViajeroRepository extends JpaRepository<Viajero, Long> {
    void deleteByUsuario(Usuario usuario);

}
