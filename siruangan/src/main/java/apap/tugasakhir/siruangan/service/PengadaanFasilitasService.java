package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.PengadaanFasilitasModel;
import apap.tugasakhir.siruangan.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface PengadaanFasilitasService {
    void addPengadaanFasilitas(PengadaanFasilitasModel pengadaanFasilitas);

    List<PengadaanFasilitasModel> getListPengadaanFasilitas();

    PengadaanFasilitasModel generateStatusPengadaan(PengadaanFasilitasModel pengadaanFasilitas);

    void deletePengadaan(PengadaanFasilitasModel pengadaan);

    PengadaanFasilitasModel getPengadaanByIdPengadaan(Long idPengadaan);

    PengadaanFasilitasModel generateStatusPengadaanAndIdUser(PengadaanFasilitasModel pengadaanFasilitas, UserModel userLoggedIn);
}
