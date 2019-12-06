package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.FasilitasRuanganModel;
import apap.tugasakhir.siruangan.repository.FasilitasRuanganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FasilitasRuanganRestServiceImpl implements FasilitasRuanganRestService {
    @Autowired
    FasilitasRuanganDB fasilitasRuanganDB;

    @Override
    public List<FasilitasRuanganModel> findByNamaRuangan(String namaRuangan) {
        List<FasilitasRuanganModel> fasilitasRuanganModels = new ArrayList<>();
        for (FasilitasRuanganModel fasilitasRuanganModel : fasilitasRuanganDB.findAll()) {
            if (fasilitasRuanganModel.getRuangan().getNamaRuangan().equals(namaRuangan)) {
                fasilitasRuanganModels.add(fasilitasRuanganModel);
            }
        }
        return fasilitasRuanganModels;
    }

    @Override
    public List<FasilitasRuanganModel> findById(Long idRuangan) {
        List<FasilitasRuanganModel> fasilitasRuanganModels = new ArrayList<>();
        for (FasilitasRuanganModel fasilitasRuanganModel : fasilitasRuanganDB.findAll()) {
            if (fasilitasRuanganModel.getRuangan().getNamaRuangan() == (namaRuangan)) {
                fasilitasRuanganModels.add(fasilitasRuanganModel);
            }
        }
        return fasilitasRuanganModels;
    }
}
