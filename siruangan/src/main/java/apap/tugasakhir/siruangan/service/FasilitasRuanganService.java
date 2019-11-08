package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.FasilitasModel;
import apap.tugasakhir.siruangan.model.FasilitasRuanganModel;
import apap.tugasakhir.siruangan.model.RuanganModel;

import java.util.List;

public interface FasilitasRuanganService {
    List<FasilitasModel> getFasilitasList(RuanganModel ruangan);
    List<FasilitasRuanganModel> getFasilitasRuanganByRuangan (RuanganModel ruangan);
}
