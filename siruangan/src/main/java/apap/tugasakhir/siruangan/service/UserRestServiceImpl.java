package apap.tugasakhir.siruangan.service;

import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import apap.tugasakhir.siruangan.model.UserModel;
import apap.tugasakhir.siruangan.repository.UserDB;
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
    @Autowired
    private UserDB userDB;
    private final WebClient webClient;

    public UserRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.sivitasUrl).build();
    }

    @Override
    public UserModel getUserById(String uuid) {
        Optional<UserModel> user = userDB.findByIdUser(uuid);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NoSuchElementException();
        }
    } 

    @Override
    public Mono<GuruDetailResponse> addGuru(UserModel user, GuruDetail guru) {
        JSONObject data = new JSONObject();
        data.put("idUser", user.getIdUser());
        data.put("nig", guru.getNig());
        data.put("nama", guru.getNama());
        data.put("tempatLahir", guru.getTempatLahir());
        data.put("tanggalLahir", guru.getTanggalLahir());
        data.put("alamat", guru.getAlamat());
        data.put("telepon", guru.getTelepon());
        System.out.println(data);
        return this.webClient.post().uri("api/teachers").contentType(MediaType.APPLICATION_JSON)
                .syncBody(data.toString()).retrieve().bodyToMono(GuruDetailResponse.class);
    }

    @Override
    public Mono<SiswaDetailResponse> addSiswa(UserModel user, SiswaDetail siswa) {
        JSONObject data= new JSONObject();
        data.put("idUser", user.getIdUser());
        data.put("nis", siswa.getNis());
        data.put("nama",siswa.getNama());
        data.put("tempatLahir", siswa.getTempatLahir());
        data.put("tanggalLahir", siswa.getTanggalLahir().toString());
        data.put("alamat",siswa.getAlamat());
        data.put("telepon",siswa.getTelepon());
        System.out.println(data);
        return this.webClient.post().uri("api/students").contentType(MediaType.APPLICATION_JSON)
                .syncBody(data.toString()).retrieve().bodyToMono(SiswaDetailResponse.class);
    }

    @Override
    public GuruDetailResponse getGuru(String uuid) {
        try {
            return this.webClient.get().uri(uriBuilder -> uriBuilder
                    .path("/teachers")
                    .path("/")
                    .path(uuid)
                    .build())
                    .retrieve().bodyToMono(GuruDetailResponse.class).block();
        } catch (WebClientResponseException.NotFound notFound) {
            return null;
        }
    }

    @Override
    public SiswaDetailResponse getSiswa(String uuid) {
        try {
            return this.webClient.get().uri(uriBuilder -> uriBuilder
                    .path("/students")
                    .path("/")
                    .path(uuid)
                    .build())
                    .retrieve().bodyToMono(SiswaDetailResponse.class).block();
        } catch (WebClientResponseException.NotFound notFound) {
            return null;
        }
    }
}
