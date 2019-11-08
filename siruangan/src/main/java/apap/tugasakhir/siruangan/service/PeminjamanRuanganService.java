package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;

public interface PeminjamanRuanganService {
    PeminjamanRuanganModel mengajukanPeminjamanRuangan(PeminjamanRuanganModel peminjaman);
    boolean dateTimeValidation(PeminjamanRuanganModel peminjaman);
    boolean capacityValidation(PeminjamanRuanganModel peminjaman);
}