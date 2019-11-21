package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.UserModel;
import apap.tugasakhir.siruangan.repository.UserDB;
import apap.tugasakhir.siruangan.rest.PengadaanBukuDetail;
import apap.tugasakhir.siruangan.rest.PengadaanBukuDetailResponse;
import apap.tugasakhir.siruangan.rest.Setting;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.json.JSONObject;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;

@Service
public class PengadaanBukuRestServiceImpl implements PengadaanBukuRestService{
    private final WebClient webClient;

    @Autowired
    private UserDB userDb;

    public PengadaanBukuRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.bukuUrl).build();
    }

    @Override
    public Mono<PengadaanBukuDetailResponse> createPengadaanBuku(@AuthenticationPrincipal UserDetails currentUser, PengadaanBukuDetail buku) throws JSONException {
        //MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        JSONObject data = new JSONObject();
        data.put("idUser", userDb.findByUsername(currentUser.getUsername()).getIdUser());
        data.put("judul", buku.getJudul());
        data.put("pengarang",buku.getPengarang());
        data.put("penerbit", buku.getPenerbit());
        data.put("jumlah", buku.getJumlah());
        data.put("harga",buku.getHarga());
        data.put("status",buku.getStatus());
        System.out.println(data);
        return this.webClient.post()
                .uri("v2/5dd67d97320000874a888ae1")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(data.toString())
                .retrieve()
                .bodyToMono(PengadaanBukuDetailResponse.class);
    }

    @Override
    public void generateStatusBuku(PengadaanBukuDetail buku, @AuthenticationPrincipal UserDetails currentUser){
        if(userDb.findByUsername(currentUser.getUsername()).getRole().getIdRole()==2){
            buku.setStatus(Integer.toString(1));
        }
    }
}
