package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.RuanganModel;

import java.util.Optional;

public interface RuanganService {
    Optional<RuanganModel> getRuanganByIdRuangan(Long idRuangan);
}
