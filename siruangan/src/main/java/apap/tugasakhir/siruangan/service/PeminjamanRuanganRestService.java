package apap.tugasakhir.siruangan.service;

import java.util.List;

import apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;

public interface PeminjamanRuanganRestService {
    PeminjamanRuanganModel mengajukanPeminjamanRuangan(PeminjamanRuanganModel peminjaman);
    List<PeminjamanRuanganModel> retriveListPeminjamanRuangan();
}