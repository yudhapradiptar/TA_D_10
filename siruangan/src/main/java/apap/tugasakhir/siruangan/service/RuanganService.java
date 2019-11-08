package apap.tugasakhir.siruangan.service;

import java.util.Optional;

import apap.tugasakhir.siruangan.model.RuanganModel;

public interface RuanganService {
    Optional<RuanganModel> getRuanganByIdRuangan(Long idRuangan);
}