package apap.tugasakhir.siruangan.repository;

import apap.tugasakhir.siruangan.model.FasilitasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FasilitasDB extends JpaRepository<FasilitasModel, Long> {
}
