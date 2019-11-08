package apap.tugasakhir.siruangan.controller;

import apap.tugasakhir.siruangan.model.FasilitasModel;
import apap.tugasakhir.siruangan.model.FasilitasRuanganModel;
import apap.tugasakhir.siruangan.model.RuanganModel;
import apap.tugasakhir.siruangan.service.FasilitasRuanganService;
import apap.tugasakhir.siruangan.service.FasilitasService;
import apap.tugasakhir.siruangan.service.RuanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping(value = "/fasilitas")
public class FasilitasRanganController {
    @Autowired
    private FasilitasService fasilitasService;

    @Autowired
    private RuanganService ruanganService;

    @Autowired
    private FasilitasRuanganService fasilitasRuanganService;

    @RequestMapping(value = "/tambah-fasilitas")
    public String addFasilitasForm(Model model) {
        model.addAttribute("listFasilitas", fasilitasService.getListFasilitas());
        model.addAttribute("listRuangan", ruanganService.getRuanganList());
        return "form-fasilitas-ruang";
    }

    @RequestMapping(value = "/tambah-fasilitas", method = RequestMethod.POST)
    public String addFasilitas(Long idFasilitas, Long idRuangan, Model model, int jumlahFasilitas) {
        FasilitasModel existingFasilitas = fasilitasService.getFasilitasByIdFasilitas(idFasilitas).get();
        RuanganModel exisitingRuangan = ruanganService.getRuanganByIdRuangan(idRuangan).get();
        FasilitasRuanganModel existingFasilitasRuangan = fasilitasRuanganService.getFasilitasRuanganByFasilitas(existingFasilitas);
        if (existingFasilitasRuangan == null){
            FasilitasRuanganModel baru = new FasilitasRuanganModel();
            baru.setFasilitas(existingFasilitas);
            baru.setRuangan(exisitingRuangan);
            baru.setJumlahFasilitas(jumlahFasilitas);
            fasilitasRuanganService.addFasilitasRuangan(baru);
            existingFasilitas.getListFasilitasRuangan().add(baru);
            exisitingRuangan.getListFasilitasRuangan().add(baru);
        } else {
            FasilitasRuanganModel fasilitasRuanganModel = existingFasilitasRuangan;
            fasilitasRuanganModel.setJumlahFasilitas(fasilitasRuanganModel.getJumlahFasilitas() + jumlahFasilitas);
            fasilitasRuanganService.addFasilitasRuangan(fasilitasRuanganModel);
        }
        model.addAttribute("ruangan", exisitingRuangan);
        model.addAttribute("fasilitas", existingFasilitas);
        return "success-fasilitas-ruangan";
    }
}
