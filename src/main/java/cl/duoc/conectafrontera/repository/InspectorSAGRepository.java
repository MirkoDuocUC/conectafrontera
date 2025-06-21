package cl.duoc.conectafrontera.repository;

import cl.duoc.conectafrontera.entity.InspectorSAG;
import cl.duoc.conectafrontera.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectorSAGRepository extends JpaRepository<InspectorSAG, Long> {
    void deleteByUsuario(Usuario usuario);

}
