package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.UserModel;
import apap.tugasakhir.siruangan.rest.GuruDetail;
import apap.tugasakhir.siruangan.rest.GuruDetailResponse;
import apap.tugasakhir.siruangan.rest.SiswaDetail;
import apap.tugasakhir.siruangan.rest.SiswaDetailResponse;
import reactor.core.publisher.Mono;

public interface UserRestService {
    Mono<GuruDetailResponse> addGuru(UserModel user, GuruDetail guru);
    Mono<SiswaDetailResponse> addSiswa(UserModel user, SiswaDetail siswa);
    Mono<GuruDetailResponse> getGuru(String uuid);
    Mono<SiswaDetailResponse> getSiswa(String uuid);
}