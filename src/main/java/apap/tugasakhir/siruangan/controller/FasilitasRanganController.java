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
import org.springframework.web.bind.annotation.*;

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
        System.out.println("AAAAAAAAA!!!!!!!!!AAAAAAAAA!!!!!");
        System.out.println("AAAAAAAAA!!!!!!!!!AAAAAAAAA!!!!!");
        System.out.println("AAAAAAAAA!!!!!!!!!AAAAAAAAA!!!!!");
        System.out.println("AAAAAAAAA!!!!!!!!!AAAAAAAAA!!!!!");
        System.out.println("AAAAAAAAA!!!!!!!!!AAAAAAAAA!!!!!");
        System.out.println("AAAAAAAAA!!!!!!!!!AAAAAAAAA!!!!!");
        System.out.println("AAAAAAAAA!!!!!!!!!AAAAAAAAA!!!!!");
        System.out.println("AAAAAAAAA!!!!!!!!!AAAAAAAAA!!!!!");
        System.out.println("AAAAAAAAA!!!!!!!!!AAAAAAAAA!!!!!");
        System.out.println("AAAAAAAAA!!!!!!!!!AAAAAAAAA!!!!!");
        System.out.println("AAAAAAAAA!!!!!!!!!AAAAAAAAA!!!!!");
        return "form-fasilitas-ruang";
    }

    @RequestMapping(value = "/tambah-fasilitas", method = RequestMethod.POST)
    public String addFasilitas(Long idFasilitas, Long idRuangan, Model model, int jumlahFasilitas) {
        FasilitasModel existingFasilitas = fasilitasService.getFasilitasByIdFasilitas(idFasilitas).get();
        RuanganModel exisitingRuangan = ruanganService.getRuanganByIdRuangan(idRuangan).get();
        FasilitasRuanganModel existingFasilitasRuangan = fasilitasRuanganService
                .getFasilitasRuanganByFasilitasAndRuangan(existingFasilitas, exisitingRuangan);
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

    //TODO kalo ga ada, lempar ke 404
    @RequestMapping(value = "/edit")
    public String editFasilitas(@RequestParam("idFasilitasRuang") Long idFasilitasRuang, Model model) {
        FasilitasRuanganModel existingFasilitasRuang = fasilitasRuanganService.getFasilitasRuanganById(idFasilitasRuang);
        if (existingFasilitasRuang != null) {
            model.addAttribute("idFasilitasRuang", existingFasilitasRuang.getIdFasilitasRuangan());
            model.addAttribute("namaFasilitas", existingFasilitasRuang.getFasilitas().getNamaFasilitas());
            model.addAttribute("namaRuangan", existingFasilitasRuang.getRuangan().getNamaRuangan());
            model.addAttribute("jumlahFasilitas", existingFasilitasRuang.getJumlahFasilitas());
            return "form-edit-fasilitas-ruangan";
        }
        return "form-edit-fasilitas-ruangan";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editFasilitasSave(Model model, Long idFasilitasRuang, Integer jumlahFasilitas) {
        FasilitasRuanganModel existingFasilitasRuang = fasilitasRuanganService.getFasilitasRuanganById(idFasilitasRuang);
        RuanganModel exisitingRuangan = ruanganService.getRuanganByIdRuangan(existingFasilitasRuang.getRuangan()
                .getIdRuangan()).get();
        FasilitasModel existingFasilitas = fasilitasService.getFasilitasByIdFasilitas(existingFasilitasRuang
                .getFasilitas().getIdFasilitas()).get();
        exisitingRuangan.getListFasilitasRuangan().remove(existingFasilitasRuang);
        existingFasilitas.getListFasilitasRuangan().remove(existingFasilitasRuang);
        existingFasilitasRuang.setJumlahFasilitas(jumlahFasilitas);
        fasilitasRuanganService.addFasilitasRuangan(existingFasilitasRuang);
        exisitingRuangan.getListFasilitasRuangan().add(existingFasilitasRuang);
        existingFasilitas.getListFasilitasRuangan().add(existingFasilitasRuang);
        model.addAttribute("ruangan", exisitingRuangan);
        model.addAttribute("fasilitas", existingFasilitas);
        return "success-edit-fasilitas-ruang";
    }

    @RequestMapping(value = "/delete")
    public String deleteFasilitasRuang(@RequestParam("idFasilitasRuang") Long idFasilitasRuang, Model model) {
        FasilitasRuanganModel existingFasilitasRuang = fasilitasRuanganService.getFasilitasRuanganById(idFasilitasRuang);
        RuanganModel exisitingRuangan = ruanganService.getRuanganByIdRuangan(existingFasilitasRuang.getRuangan()
                .getIdRuangan()).get();
        FasilitasModel existingFasilitas = fasilitasService.getFasilitasByIdFasilitas(existingFasilitasRuang
                .getFasilitas().getIdFasilitas()).get();
        exisitingRuangan.getListFasilitasRuangan().remove(existingFasilitasRuang);
        existingFasilitas.getListFasilitasRuangan().remove(existingFasilitasRuang);
        fasilitasRuanganService.deleteFasilitasRuang(existingFasilitasRuang);
        model.addAttribute("ruangan", exisitingRuangan);
        model.addAttribute("fasilitas", existingFasilitas);
        return "success-delete-fasilitas";
    }
}
