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
import apap.tugasakhir.siruangan.rest.PegawaiDetail;
import apap.tugasakhir.siruangan.rest.PegawaiDetailResponse;
import apap.tugasakhir.siruangan.rest.Setting;
import apap.tugasakhir.siruangan.rest.SiswaDetail;
import apap.tugasakhir.siruangan.rest.SiswaDetailResponse;
import reactor.core.publisher.Mono;

import org.json.JSONObject;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService {
    private final WebClient webClient;

    @Autowired
    private UserDB userDB;


    //Service Consumer
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
                    .path("api/teachers")
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
                    .path("api/students")
                    .path("/")
                    .path(uuid)
                    .build())
                    .retrieve().bodyToMono(SiswaDetailResponse.class).block();
        } catch (WebClientResponseException.NotFound notFound) {
            return null;
        }
    }

    @Override
    public PegawaiDetailResponse getPegawai(String uuid) {
        try {
            return this.webClient.get().uri(uriBuilder -> uriBuilder
                    .path("api/employees")
                    .path("/")
                    .path(uuid)
                    .build())
                    .retrieve().bodyToMono(PegawaiDetailResponse.class).block();
        } catch (WebClientResponseException.NotFound notFound) {
            return null;
        }
    }

    // @Override
    // public Mono<PegawaiDetail> getPegawaiDetail(UserModel user) {
    //     try {
    //         return this.webClient.get().uri("/api/employees/"+user.getIdUser())
    //             .retrieve().bodyToMono(PegawaiDetail.class);
    //     } catch (Exception notFound) {
    //         return null;
    //     } 

    // }

    // @Override
    // public Mono<SiswaDetail> getSiswaDetail(UserModel user) {
    //     try {
    //         return this.webClient.get().uri("/api/students/"+user.getIdUser())
    //                 .retrieve().bodyToMono(SiswaDetail.class);
    //     } catch (WebClientResponseException.NotFound notFound) {
    //         return null;
    //     }
    // }

    // @Override
    // public Mono<GuruDetail> getGuruDetail(UserModel user) {
    //     try {
    //         return this.webClient.get().uri(uriBuilder -> uriBuilder
    //             .path("/api/teachers/")
    //             .path(user.getIdUser())
    //             .build())
    //             .retrieve().bodyToMono(GuruDetail.class);
    //     } catch (WebClientResponseException.NotFound notFound) {
    //         return null;
    //     }
        
    // }
}
