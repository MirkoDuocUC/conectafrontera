package cl.duoc.conectafrontera.repository;

import cl.duoc.conectafrontera.entity.DeclaracionProducto;
import cl.duoc.conectafrontera.entity.DeclaracionProductoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeclaracionProductoRepository extends JpaRepository<DeclaracionProducto, DeclaracionProductoId> {

    List<DeclaracionProducto> findByDeclaracion_Id(Long idDeclaracion);

    List<DeclaracionProducto> findByProducto_IdProducto(Long idProducto);





}
