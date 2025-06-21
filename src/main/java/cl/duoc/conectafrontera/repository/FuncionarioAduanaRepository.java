package cl.duoc.conectafrontera.repository;

import cl.duoc.conectafrontera.entity.FuncionarioAduana;
import cl.duoc.conectafrontera.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioAduanaRepository extends JpaRepository<FuncionarioAduana, Long> {
    void deleteByUsuario(Usuario usuario);

}
