package apap.tugasakhir.siruangan.repository;

import apap.tugasakhir.siruangan.model.FasilitasRuanganModel;
import apap.tugasakhir.siruangan.model.RuanganModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FasilitasRuanganDB extends JpaRepository<FasilitasRuanganModel, Long> {
    List<FasilitasRuanganModel> findAllByRuangan (RuanganModel ruangan);
}
