package apap.tugasakhir.siruangan.service;

import java.text.SimpleDateFormat;

import javax.transaction.Transactional;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tugasakhir.siruangan.model.UserModel;
import apap.tugasakhir.siruangan.rest.GuruDetail;
import apap.tugasakhir.siruangan.rest.GuruDetailResponse;
import apap.tugasakhir.siruangan.rest.Setting;
import apap.tugasakhir.siruangan.rest.SiswaDetail;
import apap.tugasakhir.siruangan.rest.SiswaDetailResponse;
import reactor.core.publisher.Mono;

import org.json.JSONObject;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService {

    private final WebClient webClient;

    public UserRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.sivitasUrl).build();
    }

    @Override
    public Mono<GuruDetailResponse> addGuru(UserModel user, GuruDetail guru) {
        JSONObject details = new JSONObject();
        details.put("idUser", user.getIdUser());
        details.put("nig", guru.getNig());
        details.put("nama", guru.getNama());
        details.put("tempatLahir", guru.getTempatLahir());
        details.put("tanggalLahir", new SimpleDateFormat("yyyy-mm-dd").format(guru.getTanggalLahir()));
        details.put("alamat", guru.getAlamat());
        details.put("telepon", guru.getTelepon());
        System.out.println(details);
        return this.webClient.post()
            .uri("api/teachers")
            .contentType(MediaType.APPLICATION_JSON)
            .syncBody(details.toString()).retrieve()
            .bodyToMono(GuruDetailResponse.class);
    }

    @Override
    public Mono<SiswaDetailResponse> addSiswa(UserModel user, SiswaDetail siswa) {
        JSONObject details = new JSONObject();
        details.put("idUser", user.getIdUser());
        details.put("nis", siswa.getNis());
        details.put("nama", siswa.getNama());
        details.put("tempatLahir", siswa.getTempatLahir());
        details.put("tanggalLahir", new SimpleDateFormat("yyyy-mm-dd").format(siswa.getTanggalLahir()));
        details.put("alamat", siswa.getAlamat());
        details.put("telepon", siswa.getTelepon());
        System.out.println(details);
        return this.webClient.post()
            .uri("api/students")
            .contentType(MediaType.APPLICATION_JSON)
            .syncBody(details.toString()).retrieve()
            .bodyToMono(SiswaDetailResponse.class);
    }

    @Override
    public Mono<GuruDetailResponse> getGuru(String uuid) {
        return this.webClient.get().uri("/api/teachers/" + uuid).retrieve().bodyToMono(GuruDetailResponse.class);
    }

    @Override
    public Mono<SiswaDetailResponse> getSiswa(String uuid) {
        return this.webClient.get().uri("/api/students/" + uuid).retrieve().bodyToMono(SiswaDetailResponse.class);
    }
}
