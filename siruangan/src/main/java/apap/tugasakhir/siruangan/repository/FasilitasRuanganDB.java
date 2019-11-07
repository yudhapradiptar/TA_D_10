package apap.tugasakhir.siruangan.repository;

import apap.tugasakhir.siruangan.model.FasilitasRuanganModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FasilitasRuanganDB extends JpaRepository<FasilitasRuanganModel, Long> {
}
