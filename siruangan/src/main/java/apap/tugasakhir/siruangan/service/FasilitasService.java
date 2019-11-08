package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.FasilitasModel;

import java.util.Optional;

public interface FasilitasService {
    Optional<FasilitasModel> getFasilitasByIdFasilitas(Long idFasilitas);
}
