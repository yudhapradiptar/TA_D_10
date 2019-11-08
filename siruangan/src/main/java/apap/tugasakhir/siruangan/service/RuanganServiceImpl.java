package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.RuanganModel;
import apap.tugasakhir.siruangan.repository.RuanganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RuanganServiceImpl implements RuanganService {

    @Autowired
    private RuanganDB ruanganDB;

    @Override
    public Optional<RuanganModel> getRuanganByIdRuangan(Long idRuangan) {
        return ruanganDB.findById(idRuangan);
    }

    @Override
    public List<RuanganModel> getRuanganList() {
        return ruanganDB.findAll();
    }

}
