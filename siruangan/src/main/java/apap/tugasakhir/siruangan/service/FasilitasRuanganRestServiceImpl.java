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
    public List<FasilitasRuanganModel> getFasilitasByIdRuangan(Long idRuangan) {
        List<FasilitasRuanganModel> fasilitas = new ArrayList<>();
        for (FasilitasRuanganModel fasilitasRuanganModel : fasilitasRuanganDB.findAll()) {
            if (fasilitasRuanganModel.getRuangan().getIdRuangan().equals(idRuangan)) {
                System.out.println(fasilitasRuanganModel.getFasilitas().getNamaFasilitas());
                fasilitas.add(fasilitasRuanganModel);
            }
        }
        return fasilitas;
    }

}
