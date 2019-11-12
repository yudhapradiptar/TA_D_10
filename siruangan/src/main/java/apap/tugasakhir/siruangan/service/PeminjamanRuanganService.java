package apap.tugasakhir.siruangan.service;

import java.util.List;

import apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;

public interface PeminjamanRuanganService {
    List<PeminjamanRuanganModel> getPeminjamanRuanganList();
    PeminjamanRuanganModel mengajukanPeminjamanRuangan(PeminjamanRuanganModel peminjaman);
    boolean dateTimeValidation(PeminjamanRuanganModel peminjaman);
    boolean capacityValidation(PeminjamanRuanganModel peminjaman);
}