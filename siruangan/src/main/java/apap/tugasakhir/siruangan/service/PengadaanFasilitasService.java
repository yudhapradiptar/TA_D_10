package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.PengadaanFasilitasModel;

import java.util.List;
import java.util.Optional;

public interface PengadaanFasilitasService {
    void addPengadaanFasilitas(PengadaanFasilitasModel pengadaanFasilitas);

    List<PengadaanFasilitasModel> getListPengadaanFasilitas();

    PengadaanFasilitasModel generateStatusPengadaan(PengadaanFasilitasModel pengadaanFasilitas);
}
