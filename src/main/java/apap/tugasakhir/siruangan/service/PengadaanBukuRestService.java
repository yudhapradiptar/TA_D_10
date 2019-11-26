package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.UserModel;
import apap.tugasakhir.siruangan.rest.PengadaanBukuDetail;
import apap.tugasakhir.siruangan.rest.PengadaanBukuDetailResponse;
import org.json.JSONException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public interface PengadaanBukuRestService {
    Mono<PengadaanBukuDetailResponse> createPengadaanBuku(@AuthenticationPrincipal UserDetails currentUser, PengadaanBukuDetail buku) throws JSONException;

    void generateStatusBuku(PengadaanBukuDetail buku, @AuthenticationPrincipal UserDetails currentUser);
}
