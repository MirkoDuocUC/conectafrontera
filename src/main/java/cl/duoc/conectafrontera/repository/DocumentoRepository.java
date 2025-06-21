package cl.duoc.conectafrontera.repository;

import cl.duoc.conectafrontera.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
