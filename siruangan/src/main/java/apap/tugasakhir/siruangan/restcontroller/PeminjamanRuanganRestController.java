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
import apap.tugasakhir.siruangan.service.PeminjamanRuanganService;
import apap.tugasakhir.siruangan.service.UserService;

@RestController
@RequestMapping(value = "/api/peminjaman-ruangan")
public class PeminjamanRuanganRestController {
    @Autowired
    PeminjamanRuanganRestService peminjamanRuanganRestService;

    @Autowired
    PeminjamanRuanganService peminjamanRuanganService;

    @Autowired
    UserService userService;


    @PostMapping(value = "/pinjam")
    private PeminjamanRuanganModel pinjamRuangan(@Valid @RequestBody PeminjamanRuanganModel peminjaman, BindingResult bindingResult) {    
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        } else {
            if(peminjamanRuanganService.dateTimeValidation(peminjaman)
               && peminjamanRuanganService.capacityValidation(peminjaman)) {
                peminjaman.setUserPeminjam(userService.getUserByUsername("SI-KOPERASI"));
                return peminjamanRuanganRestService.mengajukanPeminjamanRuangan(peminjaman);
            } else {
                throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Input waktu peminjaman tidak valid/peminjaman bentrok/melebihi kapasitas"
                );
            }
            
        }
    }

    @GetMapping(value = "/daftar-peminjaman")
    private List<PeminjamanRuanganModel> retriveListPeminjamanRuangan() {
        return peminjamanRuanganRestService.retriveListPeminjamanRuangan();
    }
}