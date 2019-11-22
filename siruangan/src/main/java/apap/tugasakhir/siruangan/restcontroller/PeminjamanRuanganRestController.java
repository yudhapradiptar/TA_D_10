package apap.tugasakhir.siruangan.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;
import apap.tugasakhir.siruangan.service.PeminjamanRuanganRestService;

@RestController
@RequestMapping(value = "/api/peminjaman-ruangan")
public class PeminjamanRuanganRestController {
    @Autowired
    PeminjamanRuanganRestService peminjamanRuanganRestService;


    @PostMapping(value = "/pinjam")
    private PeminjamanRuanganModel pinjamRuangan(@Valid @RequestBody PeminjamanRuanganModel peminjaman, BindingResult bindingResult) {    
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        } else {
            return peminjamanRuanganRestService.mengajukanPeminjamanRuangan(peminjaman);
        }
    }

    @GetMapping(value = "/daftar-peminjaman")
    private List<PeminjamanRuanganModel> retriveListPeminjamanRuangan() {
        return peminjamanRuanganRestService.retriveListPeminjamanRuangan();
    }
}