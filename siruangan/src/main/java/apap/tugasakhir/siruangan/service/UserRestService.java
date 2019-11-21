package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.UserModel;
import apap.tugasakhir.siruangan.rest.GuruDetail;
import apap.tugasakhir.siruangan.rest.SiswaDetail;
import reactor.core.publisher.Mono;

public interface UserRestService {
    Mono<GuruDetail> getGuruDetail(UserModel user);
    Mono<SiswaDetail> getSiswaDetail(UserModel user);
}