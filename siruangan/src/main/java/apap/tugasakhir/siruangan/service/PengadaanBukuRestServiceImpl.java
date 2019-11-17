package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.rest.PengadaanBukuDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class PengadaanBukuRestServiceImpl implements PengadaanBukuRestService{
    private final WebClient webClient;

    @Override
    Mono<PengadaanBukuDetail> createPengadaanBuku(){

    }
}
