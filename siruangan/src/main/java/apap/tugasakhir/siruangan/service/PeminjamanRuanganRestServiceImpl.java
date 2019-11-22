package apap.tugasakhir.siruangan.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;
import apap.tugasakhir.siruangan.repository.PeminjamanRuanganDB;

@Service
@Transactional
public class PeminjamanRuanganRestServiceImpl implements PeminjamanRuanganRestService {
    @Autowired
    private PeminjamanRuanganDB peminjamanRuanganDB;

    @Override
    public PeminjamanRuanganModel mengajukanPeminjamanRuangan(PeminjamanRuanganModel peminjaman) {
        return peminjamanRuanganDB.save(peminjaman);
    }

    @Override
    public List<PeminjamanRuanganModel> retriveListPeminjamanRuangan() {
        return peminjamanRuanganDB.findAll();
    }
}