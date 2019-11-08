package apap.tugasakhir.siruangan.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugasakhir.siruangan.model.RuanganModel;
import apap.tugasakhir.siruangan.repository.RuanganDB;

@Service
public class RuanganServiceImpl implements RuanganService {
    @Autowired
    RuanganDB ruanganDB;

    @Override
    public Optional<RuanganModel> getRuanganByIdRuangan(Long idRuangan) {
        return ruanganDB.findByIdRuangan(idRuangan);
    }

}