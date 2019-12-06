package apap.tugasakhir.siruangan.service;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import apap.tugasakhir.siruangan.rest.Setting;
import apap.tugasakhir.siruangan.rest.SuratDetail;
import apap.tugasakhir.siruangan.rest.SuratDetailResponse;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class SuratRestServiceImpl implements SuratRestService {

    private final WebClient webClient;

    public SuratRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.suratUrl).build();
    }
    @Override
    public SuratDetail getSuratPeminjamanRuangan(String nomorSurat) {
        try {
            SuratDetailResponse suratPeminjamanRuanganResponse= this.webClient.get().uri(uriBuilder -> uriBuilder
                .path("api/v1/surat")
                .path("/")
                .path(nomorSurat)
                .build())
                .retrieve().bodyToMono(SuratDetailResponse.class).block();

            SuratDetail suratPeminjamanRuangan = suratPeminjamanRuanganResponse.getResult();
            if(suratPeminjamanRuangan.getJenisSurat().equals("Surat Peminjaman Ruangan")) {
                return suratPeminjamanRuangan;
            } else {
                return null;
            }
        } catch (WebClientResponseException.NotFound notFound) {
            return null;
        } catch (NoSuchElementException noSuchElement) {
            return null;
        }
    }

}