package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.UserModel;
import apap.tugasakhir.siruangan.repository.UserDB;
import apap.tugasakhir.siruangan.rest.PengadaanBukuDetail;
import apap.tugasakhir.siruangan.rest.PengadaanBukuDetailResponse;
import apap.tugasakhir.siruangan.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;

@Service
@Transactional
public class PengadaanBukuRestServiceImpl implements PengadaanBukuRestService{
    private final WebClient webClient;

    @Autowired
    private UserDB userDb;

    public PengadaanBukuRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.bukuUrl).build();
    }

    @Override
    public Mono<PengadaanBukuDetailResponse> createPengadaanBuku(@AuthenticationPrincipal UserDetails currentUser, PengadaanBukuDetail buku) {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("idUser", userDb.findByUsername(currentUser.getUsername()).getIdUser());
        data.add("judul", buku.getJudul());
        data.add("pengarang",buku.getPengarang());
        data.add("penerbit", buku.getPenerbit());
        data.add("jumlah", buku.getJumlah());
        data.add("harga",buku.getHarga());
        data.add("status",buku.getStatus());
        System.out.println(data);
        return this.webClient.post()
                .uri("api/pengadaan-buku")
                .syncBody(data)
                .retrieve()
                .bodyToMono(PengadaanBukuDetailResponse.class);
    }

    @Override
    public void generateStatusBuku(PengadaanBukuDetail buku, @AuthenticationPrincipal UserDetails currentUser){
        if(userDb.findByUsername(currentUser.getUsername()).getRole().getIdRole()==5){
            buku.setStatus(Integer.toString(1));
        } else if(userDb.findByUsername(currentUser.getUsername()).getRole().getIdRole()==3){
            buku.setStatus(Integer.toString(0));
        }
    }
}
