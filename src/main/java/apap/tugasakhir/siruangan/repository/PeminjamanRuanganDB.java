package apap.tugasakhir.siruangan.repository;

import apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeminjamanRuanganDB extends JpaRepository<PeminjamanRuanganModel, Long> {
    List<PeminjamanRuanganModel> findByRuanganIdRuangan(Long idRuangan);
    PeminjamanRuanganModel findByIdPeminjamanRuangan(Long idPeminjamanRuangan);
}
