package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.FasilitasModel;
import apap.tugasakhir.siruangan.repository.FasilitasDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class FasilitasServiceImpl implements FasilitasService {

    @Autowired
    FasilitasDB fasilitasDB;

    public Optional<FasilitasModel> getFasilitasByIdFasilitas(Long idFasilitas) {
        return fasilitasDB.findById(idFasilitas);    }

}
