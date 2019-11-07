package apap.tugasakhir.siruangan.repository;

import apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeminjamanRuanganDB extends JpaRepository<PeminjamanRuanganModel, Long> {
}
