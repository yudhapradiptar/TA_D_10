package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.UserModel;
import apap.tugasakhir.siruangan.rest.GuruDetail;
import apap.tugasakhir.siruangan.rest.GuruDetailResponse;
import apap.tugasakhir.siruangan.rest.PegawaiDetail;
import apap.tugasakhir.siruangan.rest.PegawaiDetailResponse;
import apap.tugasakhir.siruangan.rest.SiswaDetail;
import apap.tugasakhir.siruangan.rest.SiswaDetailResponse;
import reactor.core.publisher.Mono;

public interface UserRestService {
    UserModel getUserById(String uuid);
    Mono<GuruDetailResponse> addGuru(UserModel user, GuruDetail guru);
    Mono<SiswaDetailResponse> addSiswa(UserModel user, SiswaDetail siswa);
    GuruDetailResponse getGuru(String uuid);
    SiswaDetailResponse getSiswa(String uuid);
    PegawaiDetailResponse getPegawai (String uuid);
    // Mono<PegawaiDetail> getPegawaiDetail(UserModel user);
    // Mono<SiswaDetail> getSiswaDetail(UserModel user);
    // Mono<GuruDetail> getGuruDetail(UserModel user);

}