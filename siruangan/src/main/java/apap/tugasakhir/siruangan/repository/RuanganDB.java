package apap.tugasakhir.siruangan.repository;

import apap.tugasakhir.siruangan.model.RuanganModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuanganDB extends JpaRepository<RuanganModel, Long> {
    Optional<RuanganModel> findByIdRuangan(Long idRuangan);
}
