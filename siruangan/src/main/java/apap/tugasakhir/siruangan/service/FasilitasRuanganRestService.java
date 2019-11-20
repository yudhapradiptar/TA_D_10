package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.FasilitasRuanganModel;

import java.util.List;

public interface FasilitasRuanganRestService {
    List<FasilitasRuanganModel> getFasilitasByIdRuangan(Long idRuangan);
}
