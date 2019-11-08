package apap.tugasakhir.siruangan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugasakhir.siruangan.model.*;
import apap.tugasakhir.siruangan.service.*;

import java.util.List;

@Controller
public class PengadaanFasilitasController {
    @Qualifier("pengadaanFasilitasServiceImpl")
    @Autowired
    private PengadaanFasilitasService pengadaanFasilitasService;

    @RequestMapping(value="/pengadaan-fasilitas/add", method = RequestMethod.GET)
    public String addPengadaanFormPage(Model model){
        PengadaanFasilitasModel newPengadaan = new PengadaanFasilitasModel();
        String title = "Form Pengajuan Pengadaan Fasilitas";
        model.addAttribute("pengadaan", newPengadaan);
        model.addAttribute("title", title);


        return "form-pengadaan";
    }

    @RequestMapping(value="/pengadaan-fasilitas", method = RequestMethod.POST)
    public String addPengadaanSubmit(@ModelAttribute PengadaanFasilitasModel pengadaanFasilitas, Model model){
        try{
            pengadaanFasilitasService.generateStatusPengadaan(pengadaanFasilitas);
            pengadaanFasilitasService.addPengadaanFasilitas(pengadaanFasilitas);
            List<PengadaanFasilitasModel> listPengadaan = pengadaanFasilitasService.getListPengadaanFasilitas();
            String title = "Pengadaan Fasilitas";
            model.addAttribute("listPengadaan", listPengadaan);
            model.addAttribute("title", title);
            return "viewall-pengadaan";
        } catch (NullPointerException e){
            return "error-add-pengadaan";
        }
    }
}
