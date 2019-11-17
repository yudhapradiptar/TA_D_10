package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.rest.PengadaanBukuDetail;
import reactor.core.publisher.Mono;

public interface PengadaanBukuRestService {
    Mono<PengadaanBukuDetail> createPengadaanBuku();
}
