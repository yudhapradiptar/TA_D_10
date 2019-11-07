package apap.tugasakhir.siruangan.repository;

import apap.tugasakhir.siruangan.model.PengadaanFasilitasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PengadaanFasilitasDB extends JpaRepository<PengadaanFasilitasModel, Long> {
}
