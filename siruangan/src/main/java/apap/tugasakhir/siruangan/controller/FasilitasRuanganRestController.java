package apap.tugasakhir.siruangan.controller;

import apap.tugasakhir.siruangan.model.FasilitasRuanganDetail;
import apap.tugasakhir.siruangan.model.FasilitasRuanganModel;
import apap.tugasakhir.siruangan.service.FasilitasRuanganRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FasilitasRuanganRestController {
    @Autowired
    private FasilitasRuanganRestService fasilitasRuanganRestService;

    @GetMapping(value = "/fasilitas")
    private List<FasilitasRuanganDetail> getAllFasilitasByRuangan(@RequestParam("namaRuangan") String namaRuangan) {
        List<FasilitasRuanganModel> fasilitas = fasilitasRuanganRestService.findByNamaRuangan(namaRuangan);
        List<FasilitasRuanganDetail> cleanFasilitas = new ArrayList<>();
        for (FasilitasRuanganModel fasilitasRuanganModel : fasilitas) {
            FasilitasRuanganDetail newCleanFasilitas = new FasilitasRuanganDetail();
            newCleanFasilitas.setIdFasilitas(fasilitasRuanganModel.getFasilitas().getIdFasilitas());
            newCleanFasilitas.setNamaFasilitas(fasilitasRuanganModel.getFasilitas().getNamaFasilitas());
            newCleanFasilitas.setJumlahFasilitas(fasilitasRuanganModel.getJumlahFasilitas());
            cleanFasilitas.add(newCleanFasilitas);
        }
        return cleanFasilitas;
    }
}
