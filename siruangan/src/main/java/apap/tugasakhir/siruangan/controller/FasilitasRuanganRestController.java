package apap.tugasakhir.siruangan.controller;

import apap.tugasakhir.siruangan.model.FasilitasRuanganModel;
import apap.tugasakhir.siruangan.service.FasilitasRuanganRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FasilitasRuanganRestController {
    @Autowired
    private FasilitasRuanganRestService fasilitasRuanganRestService;

    @GetMapping(value = "/fasilitas")
    private List<FasilitasRuanganModel> getAllFasilitasByRuangan(@RequestParam("idRuangan") Long idRuangan) {
        return fasilitasRuanganRestService.getFasilitasByIdRuangan(idRuangan);
    }
}
