package apap.tugasakhir.siruangan.service;

import javax.transaction.Transactional;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import apap.tugasakhir.siruangan.model.UserModel;
import apap.tugasakhir.siruangan.rest.GuruDetail;
import apap.tugasakhir.siruangan.rest.Setting;
import apap.tugasakhir.siruangan.rest.SiswaDetail;
import reactor.core.publisher.Mono;



@Service
@Transactional
public class UserRestServiceImpl implements UserRestService {
    private final WebClient webClient;

    //Service Consumer
    public UserRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.userUrl).build();
    }

    @Override
    public Mono<GuruDetail> getGuruDetail(UserModel user) {
        try {
            return this.webClient.get().uri("/api/teachers/"+user.getIdUser())
                .retrieve().bodyToMono(GuruDetail.class);
        } catch (WebClientResponseException.NotFound notFound) {
            return null;
        }
        
    }

    @Override
    public Mono<SiswaDetail> getSiswaDetail(UserModel user) {
        try {
            return this.webClient.get().uri("/api/students/"+user.getIdUser())
                    .retrieve().bodyToMono(SiswaDetail.class);
        } catch (WebClientResponseException.NotFound notFound) {
            return null;
        }
    }

}