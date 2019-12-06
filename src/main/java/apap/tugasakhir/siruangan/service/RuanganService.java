package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.FasilitasRuanganModel;
import apap.tugasakhir.siruangan.model.RuanganModel;

import java.util.List;
import java.util.Optional;

public interface RuanganService {
    Optional<RuanganModel> getRuanganByIdRuangan(Long idRuangan);
    List<RuanganModel> getRuanganList();

    List<FasilitasRuanganModel> getFasilitasRuanganList(Long idRuangan);
}

