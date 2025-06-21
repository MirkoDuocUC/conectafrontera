package cl.duoc.conectafrontera.repository;

import cl.duoc.conectafrontera.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
