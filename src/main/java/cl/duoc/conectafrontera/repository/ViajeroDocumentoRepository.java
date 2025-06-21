package cl.duoc.conectafrontera.repository;

import cl.duoc.conectafrontera.entity.ViajeroDocumento;
import cl.duoc.conectafrontera.entity.ViajeroDocumentoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViajeroDocumentoRepository extends JpaRepository<ViajeroDocumento, ViajeroDocumentoId> {
    List<ViajeroDocumento> findByIdViajero_Id(Long idViajero);

}
